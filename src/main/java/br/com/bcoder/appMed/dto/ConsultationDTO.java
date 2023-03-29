package br.com.bcoder.appMed.dto;

import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ConsultationDTO {
  private String name;
  private String description;
  private String specialty;
  private LocalDateTime createdAt;
  private Set<ExamDTO> exams;

  public ConsultationDTO ( ConsultationModel consultationModel ) {
    this.name = consultationModel.getName();
    this.description = consultationModel.getDescription();
    this.specialty = consultationModel.getSpecialty();
    this.createdAt = consultationModel.getCreatedAt();
    this.exams = this.generateDTOSet(consultationModel);
  }

  public Set<ExamDTO> generateDTOSet( ConsultationModel consultationModel) {
    Set<ExamDTO> examDTOS = new HashSet<>();
    for (ExamsModel exam : consultationModel.getExams()) {
      examDTOS.add(new ExamDTO(exam));
    }
    return examDTOS;
  }
  public String getName () {
    return name;
  }

  public void setName ( String name ) {
    this.name = name;
  }

  public String getDescription () {
    return description;
  }

  public LocalDateTime getCreatedAt () {
    return createdAt;
  }

  public void setCreatedAt ( LocalDateTime createdAt ) {
    this.createdAt = createdAt;
  }

  public void setDescription ( String description ) {
    this.description = description;
  }

  public String getSpecialty () {
    return specialty;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }

  public Set<ExamDTO> getExams () {
    return exams;
  }

  public void setExams ( Set<ExamDTO> exams ) {
    this.exams = exams;
  }
}
