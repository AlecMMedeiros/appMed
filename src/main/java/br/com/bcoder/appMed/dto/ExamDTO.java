package br.com.bcoder.appMed.dto;

import br.com.bcoder.appMed.model.ExamsModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExamDTO {
  private Long id;
  private String name;
  private String description;
  private String requestedBy;
  private LocalDateTime scheduled;


  public ExamDTO ( ExamsModel exam ) {
    this.id = exam.getId();
    this.name = exam.getName();
    this.description = exam.getDescription();
    this.requestedBy = exam.getRequestedBy().getName();
    this.scheduled = exam.getScheduled();
  }

  public Long getId () {
    return id;
  }

  public void setId ( Long id ) {
    this.id = id;
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

  public void setDescription ( String description ) {
    this.description = description;
  }

  public String getRequestedBy () {
    return requestedBy;
  }

  public void setRequestedBy ( String requestedBy ) {
    this.requestedBy = requestedBy;
  }

  public LocalDateTime getScheduled () {
    return scheduled;
  }

  public void setScheduled ( LocalDateTime scheduled ) {
    this.scheduled = scheduled;
  }
}
