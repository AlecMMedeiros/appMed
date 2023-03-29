package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.ExamPostDTO;
import br.com.bcoder.appMed.service.ExamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/exams")
public class ExamsController {

  private final ExamsService examsService;

  public ExamsController ( ExamsService examsService ) {
    this.examsService = examsService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerExam ( @RequestBody ExamPostDTO newExam, Principal principal ){
    return examsService.registerExam(newExam, principal.getName());
  }

}
