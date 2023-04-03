package br.com.bcoder.appMed.service.interfaces;

import br.com.bcoder.appMed.dto.examsDTO.ExamDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamDeleteDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamPostDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamUpdateDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;
import br.com.bcoder.appMed.model.MedicModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IExameService {
 ResponseEntity<String> registerExam ( ExamPostDTO exam, String email );
 ResponseEntity<List<ExamDTO>> listCurrentUserExams( String email);

  private ExamsModel generateModelFromDTO ( ExamPostDTO exam, ConsultationModel refConsultation, MedicModel refMedic ) {
    return null;
  }

 ResponseEntity<String> updateExam (String email, ExamUpdateDTO examUpdateDTO);
 ResponseEntity<String> deleteExam( ExamDeleteDTO examDeleteDTO, String email);

  private String generateExamID ( String consultation ) {
    return null;
  }
}
