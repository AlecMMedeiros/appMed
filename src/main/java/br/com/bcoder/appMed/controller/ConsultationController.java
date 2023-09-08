package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.service.ConsultationService;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDeleteDTO;
import br.com.bcoder.appMed.dto.consultationDTO.ConsultationPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> listCurrentUserConsultations(Principal principal) {
        System.out.println(principal.getName());
        return consultationService.listCurrentUserConsultations(principal.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerConsultation(@RequestBody ConsultationPostDTO newConsultation, Principal principal) {
        return consultationService.registerConsultation(newConsultation, principal.getName());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> deleteConsultation(@RequestBody ConsultationDeleteDTO consultationDeleteDTO, Principal principal) {
        return consultationService.deleteConsultation(consultationDeleteDTO, principal.getName());
    }

}
