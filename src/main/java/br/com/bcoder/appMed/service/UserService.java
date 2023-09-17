package br.com.bcoder.appMed.service;

import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDTO;
import br.com.bcoder.appMed.dto.userDTO.UserAuthRequestDTO;
import br.com.bcoder.appMed.dto.userDTO.UserWithConsultationsDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.UserRepository;
import br.com.bcoder.appMed.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    public void simpleSave(UserModel user) {
        userRepository.save(user);
    }

    public UserModel findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserWithConsultationsDTO> findUserByEmailWithConsultations(String email) {
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

    public ResponseEntity<?> currentUserInfo(String email) {
        Optional<UserWithConsultationsDTO> refUser;
        try {
            refUser = this.findUserByEmailWithConsultations(email);
            if (refUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(refUser);
            } else {
                UsernameNotFoundException exception = new UsernameNotFoundException("User Not Found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
        }
    }

    public ResponseEntity<?> registerUser(UserModel newUser) {
        try {
            UserModel refUser = userRepository.findByEmail(newUser.getEmail());
            if (refUser != null) {
                return ResponseEntity.status(HttpStatus.OK).body("User already exist in our database");
            }
            String hashPwd = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(hashPwd);
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User " + newUser.getDisplayName() + " created.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
        }
    }

    public ResponseEntity<?> login(UserAuthRequestDTO userToVerify) {
        try {
            UserModel refUser = userRepository.findByEmail(userToVerify.getEmail());
            if (refUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User " + userToVerify.getEmail() + " do not exists on our database");
            }
            if (!passwordEncoder.matches(userToVerify.getPassword(), refUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User " + userToVerify.getEmail() + " exists on our database, but the password doesn't match.");
            }

            String token = jwtUtil.generateJwtToken(userToVerify.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("Token", token);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your request cannot be processed due to an error caused by " + exception.getMessage());
        }
    }

}
