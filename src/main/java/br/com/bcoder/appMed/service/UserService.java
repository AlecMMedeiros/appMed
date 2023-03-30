package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.ConsultationDTO;
import br.com.bcoder.appMed.dto.UserWithConsultationsDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService ( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void simpleSave ( UserModel user) {
    userRepository.save(user);
  }
  public UserModel findUserByEmail (String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<UserWithConsultationsDTO> findUserByEmailWithConsultations( String email) {
    Optional<UserModel> user = userRepository.findByEmailWithConsultations(email);
    List<ConsultationDTO> consultationDTO = new ArrayList<>();
    if (user.isPresent()) {
      for (ConsultationModel consultation : user.get().getConsultations()) {
        consultationDTO.add(new ConsultationDTO(consultation));
      }
      UserWithConsultationsDTO userWithConsultationsDTO = new UserWithConsultationsDTO(user.get(), consultationDTO);
      return Optional.of(userWithConsultationsDTO);
    }
    return Optional.empty();
  }

  public ResponseEntity currentUserInfo (String email) {
    Optional<UserWithConsultationsDTO> refUser;
    try {
      refUser = this.findUserByEmailWithConsultations(email);
      if( refUser.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(refUser);
      } else {
        UsernameNotFoundException exception = new UsernameNotFoundException("User Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
      }
    } catch ( Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }
  }

  public ResponseEntity<String> registerUser (UserModel newUser){
    try{
      String hashPwd = passwordEncoder.encode ( newUser.getPassword () );
      newUser.setPassword(hashPwd);
      userRepository.save(newUser);
      return ResponseEntity.status(HttpStatus.CREATED).body("User " + newUser.getDisplayName() + " created");
    } catch (Exception exception){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
    }
  }

}
