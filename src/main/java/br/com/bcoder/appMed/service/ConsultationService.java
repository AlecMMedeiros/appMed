package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.ConsultationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
  private final ConsultationRepository consultationRepository;
  private final UserService userService;

  public ConsultationService ( ConsultationRepository consultationRepository, UserService userService ) {
    this.consultationRepository = consultationRepository;
    this.userService = userService;
  }

  public ConsultationModel simpleFindById( Long id){
    return consultationRepository.findConsultationModelById(id);
  }

  public void simpleSave( ConsultationModel consultation){
    consultationRepository.save(consultation);
  }

  public ResponseEntity registerConsultation ( ConsultationModel newConsultation, String email ) {
    UserModel refUser = userService.findUserByEmail(email) ;
    ConsultationModel transactionConsultation;
    try {
      newConsultation.setUser(refUser);
      transactionConsultation = consultationRepository.save(newConsultation);
      refUser.setConsultations(transactionConsultation);
      userService.simpleSave(refUser);
      return ResponseEntity.status(HttpStatus.CREATED).body("Consultation successfully created");
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }
  }

}
