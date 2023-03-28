package br.com.bcoder.appMed.dto;

import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;

import java.time.LocalDateTime;
import java.util.Set;

public class ConsultationDTO {
  private String name;
  private String description;
  private String specialty;
  private LocalDateTime createdAt;
  private Set<ExamsModel> exams;

  public ConsultationDTO ( ConsultationModel consultationModel ) {
    this.name = consultationModel.getName();
    this.description = consultationModel.getDescription();
    this.specialty = consultationModel.getSpecialty();
    this.createdAt = consultationModel.getCreatedAt();
    this.exams = consultationModel.getExams();
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

  public Set<ExamsModel> getExams () {
    return exams;
  }

  public void setExams ( Set<ExamsModel> exams ) {
    this.exams = exams;
  }
}
