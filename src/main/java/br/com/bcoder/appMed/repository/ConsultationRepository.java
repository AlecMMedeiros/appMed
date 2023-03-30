package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {
  ConsultationModel findConsultationModelById(Long id);
}
