package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.ExamPostDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.repository.ExamsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExamsService {
  private final ExamsRepository examsRepository;
  private final ConsultationService consultationService;
  private final MedicService medicService;

  public ExamsService ( ExamsRepository examsRepository, ConsultationService consultationService, MedicService medicService ) {
    this.examsRepository = examsRepository;
    this.consultationService = consultationService;
    this.medicService = medicService;
  }
  public ResponseEntity registerExam ( ExamPostDTO exam ){
    MedicModel refMedic = medicService.simpleFindMedicById(exam.getMedicId());
    ConsultationModel refConsultation = consultationService.simpleFindById(exam.getConsultationId());
    if ( refConsultation.getId() > 0 && refMedic.getId() > 0){
      try {
        ExamsModel newExam = new ExamsModel();
        newExam.setName(exam.getName());
        newExam.setDescription(exam.getName());
        newExam.setConsultation(refConsultation);
        newExam.setRequestedBy(refMedic);
        newExam.setScheduled(exam.getScheduled());
        examsRepository.save(newExam);
        refConsultation.setExams(newExam);
        consultationService.simpleSave(refConsultation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exam successfully created.");
      } catch (Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultation not found at Database");
    }
  }
}
