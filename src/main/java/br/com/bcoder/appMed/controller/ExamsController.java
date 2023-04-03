package br.com.bcoder.appMed.controller;

import br.com.bcoder.appMed.dto.examsDTO.ExamDeleteDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamPostDTO;
import br.com.bcoder.appMed.dto.examsDTO.ExamUpdateDTO;
import br.com.bcoder.appMed.service.interfaces.IExameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/exams")
public class ExamsController {

  private final IExameService examsService;

  public ExamsController ( IExameService examsService ) {
    this.examsService = examsService;
  }

  @GetMapping("/list")
  public ResponseEntity<?> listCurrentUserExams (Principal principal){
    return examsService.listCurrentUserExams(principal.getName());
  }
  @PostMapping("/register")
  public ResponseEntity<String> registerExam ( @RequestBody ExamPostDTO newExam, Principal principal ){
    return examsService.registerExam(newExam, principal.getName());
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateExam ( @RequestBody ExamUpdateDTO examUpdateDTO, Principal principal ) {
    return examsService.updateExam(principal.getName(), examUpdateDTO);
  }

  @DeleteMapping("/remove")
  public  ResponseEntity<String> deleteExam ( @RequestBody ExamDeleteDTO examDeleteDTO, Principal principal) {
    return  examsService.deleteExam(examDeleteDTO, principal.getName());
  }

}
