package br.com.bcoder.appMed.service.interfaces;

import br.com.bcoder.appMed.dto.userDTO.UserWithConsultationsDTO;
import br.com.bcoder.appMed.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUserService {
  void simpleSave ( UserModel user);
  UserModel findUserByEmail (String email);
  Optional<UserWithConsultationsDTO> findUserByEmailWithConsultations( String email);
  ResponseEntity<?> currentUserInfo ( String email);
  ResponseEntity<String> registerUser (UserModel newUser);
}
