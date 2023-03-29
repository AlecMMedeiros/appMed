package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.MedicModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<MedicModel, Long> {
  MedicModel findMedicModelById (Long id);

}
