package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.userDTO.UserAuthRequestDTO;
import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.service.UserService;
import br.com.bcoder.appMed.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserModel newUser) {
        return userService.registerUser(newUser);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody UserAuthRequestDTO authRequest) {
        return userService.login(authRequest);
    }

}
