package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.consultationDTO.ConsultationPostDTO;
import br.com.bcoder.appMed.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

  private final ConsultationService consultationService;

  public ConsultationController ( ConsultationService consultationService ) {
    this.consultationService = consultationService;
  }

  @GetMapping("/list")
  public ResponseEntity listCurrentUserConsultations (Principal principal) {
    return  consultationService.listCurrentUserConsultations(principal.getName());
  }

  @PostMapping("/register")
  public ResponseEntity registerConsultation( @RequestBody ConsultationPostDTO newConsultation, Principal principal ) {
    return consultationService.registerConsultation(newConsultation, principal.getName());
  }
}
