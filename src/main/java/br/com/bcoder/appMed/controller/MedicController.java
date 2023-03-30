package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.MedicPostDTO;
import br.com.bcoder.appMed.model.MedicModel;
import br.com.bcoder.appMed.service.MedicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {
  private final MedicService medicService;

  public MedicController ( MedicService medicService ) {
    this.medicService = medicService;
  }

  @GetMapping("/list")
  public List<MedicModel> listCurrentUserMedics (Principal principal) {
    return medicService.listCurrentUserMedics(principal.getName());
  }
  @PostMapping("/register")
  public ResponseEntity<String> registerMedic( @RequestBody MedicPostDTO newMedic, Principal principal ) throws Exception {
    return medicService.registerMedic(newMedic, principal.getName());
  }
}
