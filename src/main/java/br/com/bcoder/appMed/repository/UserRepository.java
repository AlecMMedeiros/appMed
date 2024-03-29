package br.com.bcoder.appMed.repository;

import br.com.bcoder.appMed.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.consultations WHERE u.email = :email")
    Optional<UserModel> findByEmailWithConsultations(String email);
}
