package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {

    @Query("select c from ConsultationModel c where c.id = :id")
    ConsultationModel findConsultationModelById(String id);

    List<ConsultationModel> findConsultationModelByUserId(Long userID);

    void deleteById(String id);

}
