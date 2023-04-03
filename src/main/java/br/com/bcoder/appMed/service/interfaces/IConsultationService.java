package br.com.bcoder.appMed.service.interfaces;

import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDeleteDTO;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationPostDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import org.springframework.http.ResponseEntity;

public interface IConsultationService {
  ConsultationModel simpleFindById( String id);
  void simpleSave(ConsultationModel consultation);
  ResponseEntity<String> registerConsultation( ConsultationPostDTO newConsultation, String email);
  ResponseEntity<String> deleteConsultation( ConsultationDeleteDTO consultationDeleteDTO, String email);
  ResponseEntity<?> listCurrentUserConsultations(String email);
  void populateConsultationFromDTO( ConsultationPostDTO consultationPostDTO, ConsultationModel consultationModel, UserModel userModel, MedicModel medicModel);
}
