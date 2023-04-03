package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.medicDTO.MedicResumedDTO;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.MedicRepository;
import br.com.bcoder.appMed.service.interfaces.IMedicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicService implements IMedicService {
  private final MedicRepository medicRepository;
  private final UserService userService;

  public MedicService ( MedicRepository medicRepository, UserService userService ) {
    this.medicRepository = medicRepository;
    this.userService = userService;
  }

  public MedicModel simpleFindMedicById (Long id){
    return medicRepository.findMedicModelById(id);
  }

  public List<MedicModel> simpleFindMedicsById( String email) {
    UserModel refUser = userService.findUserByEmail(email);
    return new ArrayList<>(medicRepository.findMedicModelsByUserId(refUser.getId()));
  }

  public List<MedicResumedDTO> listCurrentUserMedics( String email) {
    UserModel refUser = userService.findUserByEmail(email);
    List<MedicResumedDTO> refMedicList = new ArrayList<>();
    for (MedicModel medicModel : medicRepository.findMedicModelsByUserId(refUser.getId())) {
      refMedicList.add(new MedicResumedDTO(medicModel));
    }
    return refMedicList;
  }

  public Boolean verifyIfMedicExistForUser(Long crm, UserModel userModel) {
    return medicRepository.findMedicModelsByCrmAndUserId(crm, userModel.getId()).size() > 0;
  }

  public void populateMedicFromDTO (
      MedicModel medicModel,
      MedicResumedDTO medicResumedDTO,
      UserModel userModel
  ){
    medicModel.setName(medicResumedDTO.getName());
    medicModel.setLocal(medicResumedDTO.getLocal());
    medicModel.setCRM(medicResumedDTO.getCrm());
    medicModel.setSpecialty(medicResumedDTO.getSpecialty());
    medicModel.setUser(userModel);
  }


  public ResponseEntity<String> registerMedic ( MedicResumedDTO medicModelDTO, String email) {
    UserModel refUser = userService.findUserByEmail(email);
    if (this.verifyIfMedicExistForUser(medicModelDTO.getCrm(), refUser)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Medic with CRM " + medicModelDTO.getCrm() + " already exists at database" );
    }
    MedicModel transactionalMedic = new MedicModel();
    try{
      this.populateMedicFromDTO(transactionalMedic, medicModelDTO, refUser);
      refUser.setMedics(medicRepository.save(transactionalMedic));
      return ResponseEntity.status(HttpStatus.CREATED).body("Medic " + transactionalMedic.getName() + " successfully created" );
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }

  }

}
