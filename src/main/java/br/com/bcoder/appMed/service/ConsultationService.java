package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.service.interfaces.IConsultationService;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDTO;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDeleteDTO;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationPostDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ConsultationService implements IConsultationService {
  private final ConsultationRepository consultationRepository;
  private final UserService userService;
  private final MedicService medicService;

  public ConsultationService ( ConsultationRepository consultationRepository, UserService userService, MedicService medicService ) {
    this.consultationRepository = consultationRepository;
    this.userService = userService;
    this.medicService = medicService;
  }

  public ConsultationModel simpleFindById( String id){
    return consultationRepository.findConsultationModelById(id);
  }

  public void simpleSave( ConsultationModel consultation){
    consultationRepository.save(consultation);
  }

  public void  populateConsultationFromDTO(
      ConsultationPostDTO consultationPostDTO,
      ConsultationModel consultationModel,
      UserModel userModel,
      MedicModel medicModel
  ) {
    consultationModel.setUser(userModel);
    consultationModel.setName(consultationPostDTO.getName());
    consultationModel.setDescription(consultationPostDTO.getDescription());
    consultationModel.setMedic(medicModel);
    consultationModel.setSpecialty(medicModel.getSpecialty());
  }

  public ResponseEntity<?> listCurrentUserConsultations(String email) {
    UserModel refUser = userService.findUserByEmail(email);
    List<ConsultationDTO> refConsultation = new ArrayList<>();
    try {
      for (ConsultationModel consultationModel : consultationRepository.findConsultationModelByUserId(refUser.getId())) {
        refConsultation.add(new ConsultationDTO(consultationModel));
      }
      return ResponseEntity.status(HttpStatus.OK).body(refConsultation);
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }
  }


  public ResponseEntity<String> registerConsultation ( ConsultationPostDTO newConsultation, String email ) {
    UserModel refUser = userService.findUserByEmail(email) ;
    MedicModel refMedic = medicService.simpleFindMedicById(newConsultation.getMedic());
    if( !medicService.simpleFindMedicsById(email).contains(refMedic)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No medic with this data: " + refMedic + " associated with " + refUser.getDisplayName());
    }
    ConsultationModel transactionConsultation = new ConsultationModel();
    try {
      this.populateConsultationFromDTO(newConsultation, transactionConsultation, refUser, refMedic);
      transactionConsultation.setId(getServiceReqNumber(refUser.getId()));
      consultationRepository.save(transactionConsultation);
      refUser.setConsultations(transactionConsultation);
      userService.simpleSave(refUser);
      return ResponseEntity.status(HttpStatus.CREATED).body("Consultation successfully created " + transactionConsultation.getId() );
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }
  }

  @Transactional
  public ResponseEntity<String> deleteConsultation( ConsultationDeleteDTO consultationDeleteDTO, String email){
    UserModel refUser = userService.findUserByEmail(email);
    ConsultationModel refConsultation = consultationRepository.findConsultationModelById(consultationDeleteDTO.getId());
    if( refConsultation.getUser().getId().equals(refUser.getId())){
      consultationRepository.deleteById(consultationDeleteDTO.getId());
      return ResponseEntity.status(HttpStatus.OK).body("Consultation successfully deleted");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have access to this consultation");
    }
  }
  public String getServiceReqNumber(Long user) {
    Random baseConsultationID = new Random();
    String consultationId;
    do {
      int ranNum = baseConsultationID.nextInt(999999999 - 9999) + 9999;
      consultationId = "Consulta - "+ user + " - " + ranNum;
    } while (consultationRepository.findConsultationModelById(consultationId) != null);
    return consultationId;
  }

}
