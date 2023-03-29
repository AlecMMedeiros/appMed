package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.ExamPostDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.ExamsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

  private ExamsModel generateModelFromDTO(ExamPostDTO exam, ConsultationModel refConsultation, MedicModel refMedic) {
    ExamsModel newExam = new ExamsModel();
    newExam.setName(exam.getName());
    newExam.setDescription(exam.getDescription());
    newExam.setConsultation(refConsultation);
    newExam.setRequestedBy(refMedic);
    newExam.setScheduled(exam.getScheduled());
    return newExam;
  }

  public ResponseEntity<String> registerExam ( ExamPostDTO exam, String email ){
    UserModel refUser = userService.findUserByEmail(email);
    MedicModel refMedic = medicService.simpleFindMedicById(exam.getMedicId());
    ConsultationModel refConsultation = consultationService.simpleFindById(exam.getConsultationId());
    if (!Objects.equals(refUser.getId(), refConsultation.getUser().getId())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have access to this consultation");
    }

    if ( refConsultation.getId() > 0 && refMedic.getId() > 0){
      try {
        ExamsModel newExam = this.generateModelFromDTO(exam, refConsultation, refMedic);
        examsRepository.save(newExam);
        refConsultation.setExams(newExam);
        consultationService.simpleSave(refConsultation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exam successfully created.");
      } catch (Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultation or Medic not found at Database");
    }
  }
}
