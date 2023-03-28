package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.ExamsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsRepository extends JpaRepository<ExamsModel, Long> {
}
