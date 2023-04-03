package br.com.bcoder.appMed.service.interfaces;

import br.com.bcoder.appMed.dto.medicDTO.MedicResumedDTO;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMedicService {
  MedicModel simpleFindMedicById ( Long id);
  List<MedicModel> simpleFindMedicsById( String email);
  List<MedicResumedDTO> listCurrentUserMedics( String email);
  Boolean verifyIfMedicExistForUser(Long crm, UserModel userModel);
  void populateMedicFromDTO (
      MedicModel medicModel,
      MedicResumedDTO medicResumedDTO,
      UserModel userModel
  );
  ResponseEntity<String> registerMedic ( MedicResumedDTO medicModelDTO, String email);
}
