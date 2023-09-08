package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.medicDTO.MedicResumedDTO;
import br.com.bcoder.appMed.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicService medicService;

    @GetMapping("/list")
    public List<MedicResumedDTO> listCurrentUserMedics(Principal principal) {
        return medicService.listCurrentUserMedics(principal.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerMedic(@RequestBody MedicResumedDTO newMedic, Principal principal) throws Exception {
        return medicService.registerMedic(newMedic, principal.getName());
    }
}
