package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.service.interfaces.IConsultationService;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDeleteDTO;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationPostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

  private final IConsultationService consultationService;


  public ConsultationController ( IConsultationService consultationService ) {
    this.consultationService = consultationService;
  }

  @GetMapping("/list")
  public ResponseEntity<?> listCurrentUserConsultations (Principal principal) {
    return  consultationService.listCurrentUserConsultations(principal.getName());
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerConsultation( @RequestBody ConsultationPostDTO newConsultation, Principal principal ) {
    return consultationService.registerConsultation(newConsultation, principal.getName());
  }

  @DeleteMapping("/remove")
  public ResponseEntity<?> deleteConsultation ( @RequestBody ConsultationDeleteDTO consultationDeleteDTO, Principal principal){
    return consultationService.deleteConsultation(consultationDeleteDTO , principal.getName() );
  }

}
