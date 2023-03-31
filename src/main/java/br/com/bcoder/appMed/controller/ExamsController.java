package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.examsDTO.ExamPostDTO;
import br.com.bcoder.appMed.service.ExamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/exams")
public class ExamsController {

  private final ExamsService examsService;

  public ExamsController ( ExamsService examsService ) {
    this.examsService = examsService;
  }

  @GetMapping("/list")
  public ResponseEntity listCurrentUserExams (Principal principal){
    return examsService.listCurrentUserExams(principal.getName());
  }
  @PostMapping("/register")
  public ResponseEntity<String> registerExam ( @RequestBody ExamPostDTO newExam, Principal principal ){
    return examsService.registerExam(newExam, principal.getName());
  }

  @DeleteMapping("/remove")
  public  ResponseEntity<String> deleteExam (@RequestBody Long id, Principal principal) {
    return  examsService.deleteExam(principal.getName(), id);
  }

}
