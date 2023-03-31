package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {
  ConsultationModel findConsultationModelById(Long id);

  List<ConsultationModel> findConsultationModelByUserId(Long userID);

}
