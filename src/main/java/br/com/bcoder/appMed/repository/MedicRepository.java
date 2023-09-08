package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.MedicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicRepository extends JpaRepository<MedicModel, Long> {
    MedicModel findMedicModelById(Long id);

    List<MedicModel> findMedicModelsByUserId(Long userId);

    List<MedicModel> findMedicModelsByCrmAndUserId(Long crm, Long userId);

}
