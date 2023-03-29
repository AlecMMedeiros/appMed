package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.repository.MedicRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicService {
  private final MedicRepository medicRepository;

  public MedicService ( MedicRepository medicRepository ) {
    this.medicRepository = medicRepository;
  }

  public MedicModel simpleFindMedicById (Long id){
    return medicRepository.findMedicModelById(id);
  }

}
