package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class LoginController {

  private final UserService userService;

  public LoginController ( UserService userService ) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<String> registerUser( @RequestBody UserModel newUser ){
    return userService.registerUser(newUser);
  }

}
