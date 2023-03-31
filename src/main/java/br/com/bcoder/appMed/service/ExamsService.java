package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.examsDTO.ExamDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamDeleteDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamPostDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamUpdateDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.ExamsRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ExamsService {
  private final ExamsRepository examsRepository;
  private final ConsultationService consultationService;
  private final UserService userService;
  private final MedicService medicService;

  public ExamsService ( ExamsRepository examsRepository, ConsultationService consultationService, UserService userService, MedicService medicService ) {
    this.examsRepository = examsRepository;
    this.consultationService = consultationService;
    this.userService = userService;
    this.medicService = medicService;
  }

  public ResponseEntity<String> registerExam ( ExamPostDTO exam, String email ){
    UserModel refUser = userService.findUserByEmail(email);
    MedicModel refMedic = medicService.simpleFindMedicById(exam.getMedicId());
    ConsultationModel refConsultation = consultationService.simpleFindById(exam.getConsultationId());
    if(refConsultation == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultation not found.");
    }
    if (!Objects.equals(refUser.getId(), refConsultation.getUser().getId())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have access to this consultation");
    }

    if ( refConsultation.getId().length() > 0 && refMedic.getId() > 0){
      try {
        ExamsModel newExam = this.generateModelFromDTO(exam, refConsultation, refMedic);
        newExam.setId(generateExamID(refConsultation.getId() ));
        examsRepository.save(newExam);
        refConsultation.setExams(newExam);
        consultationService.simpleSave(refConsultation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exam successfully created " + newExam.getId());
      } catch (Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultation or Medic not found at Database");
    }
  }

  public ResponseEntity<List<ExamDTO>> listCurrentUserExams(String email){
    UserModel refUser = userService.findUserByEmail(email);
    List<ExamDTO> refExams = new ArrayList<>();
    for (ExamsModel examsModel : examsRepository.findExamsModelByConsultationUserId(refUser.getId())) {
      refExams.add(new ExamDTO(examsModel));
    }
    return ResponseEntity.status(HttpStatus.OK).body(refExams);
  }

  private ExamsModel generateModelFromDTO(ExamPostDTO exam, ConsultationModel refConsultation, MedicModel refMedic) {
    ExamsModel newExam = new ExamsModel();
    newExam.setName(exam.getName());
    newExam.setDescription(exam.getDescription());
    newExam.setConsultation(refConsultation);
    newExam.setRequestedBy(refMedic);
    newExam.setScheduled(exam.getScheduled());
    return newExam;
  }

  public ResponseEntity<String> updateExam (String email, ExamUpdateDTO examUpdateDTO) {
    UserModel refUser = userService.findUserByEmail(email);
    ExamsModel refExam = examsRepository.findExamsModelByIdAndConsultationUserId(examUpdateDTO.getId(), refUser.getId());
    MedicModel refMedic = medicService.simpleFindMedicById(examUpdateDTO.getMedicId());
    if( !medicService.simpleFindMedicsById(email).contains(refMedic)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No medic with this data: " + refMedic + " associated with " + refUser.getDisplayName());
    }
    if( refExam.getConsultation().getUser().getId().equals(refUser.getId())){
      refExam.setName(examUpdateDTO.getName());
      refExam.setDescription(examUpdateDTO.getDescription());
      refExam.setRequestedBy(refMedic);
      refExam.setScheduled(examUpdateDTO.getScheduled());
      examsRepository.save(refExam);
      return ResponseEntity.status(HttpStatus.OK).body("Exam successfully updated");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have access to this exam");
    }
  }

  @Transactional
  public ResponseEntity<String> deleteExam( ExamDeleteDTO examDeleteDTO, String email){
    UserModel refUser = userService.findUserByEmail(email);
    ExamsModel refExam = examsRepository.findExamsModelById(examDeleteDTO.getId());
    if( refExam.getConsultation().getUser().getId().equals(refUser.getId())){
      examsRepository.deleteById(refExam.getId());
      return ResponseEntity.status(HttpStatus.OK).body("Exam " + examDeleteDTO.getId() + " successfully deleted");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have access to this exam");
    }
  }

  public String generateExamID(String consultation) {
    Random baseExamID = new Random();
    String examId;
    do {
      int ranNum = baseExamID.nextInt(999999999 - 9999) + 9999;
      examId = "Exame -"+ consultation.substring(10)+ " - " + ranNum;
    } while (examsRepository.findExamsModelById(examId) != null);
    return examId;
  }

}
