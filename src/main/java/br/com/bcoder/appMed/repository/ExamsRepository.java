package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.ExamsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamsRepository extends JpaRepository<ExamsModel, Long> {

  List<ExamsModel> findExamsModelByConsultationUserId(Long userId);
  ExamsModel findExamsModelByIdAndConsultationUserId(Long consultationId, Long userID);
}
