package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.service.ConsultationService;
import br.com.bcoder.appMed.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

  private final ConsultationService consultationService;

  public ConsultationController ( ConsultationService consultationService ) {
    this.consultationService = consultationService;
  }

  @PostMapping("/register")
  public ResponseEntity registerConsultation( @RequestBody ConsultationModel newConsultation, Principal principal ) {
    return consultationService.registerConsultation(newConsultation, principal.getName());
  }
}
