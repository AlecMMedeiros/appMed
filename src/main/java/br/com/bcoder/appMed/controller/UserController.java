package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/info")
  public ResponseEntity<?> currentUserInfo( Principal principal ){
    return userService.currentUserInfo(principal.getName());
  }
}
