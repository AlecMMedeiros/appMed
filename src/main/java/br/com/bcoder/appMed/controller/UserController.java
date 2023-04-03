package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

  private final IUserService userService;

  public UserController ( IUserService userService ) {
    this.userService = userService;
  }

  @GetMapping("/info")
  public ResponseEntity<?> currentUserInfo( Principal principal ){
    return userService.currentUserInfo(principal.getName());
  }
}
