package br.com.bcoder.appMed.dto.examsDTO;

import java.time.LocalDateTime;

public class ExamUpdateDTO {
  private String id;
  private String name;
  private String description;
  private Long medicId;
  private LocalDateTime scheduled;

  public ExamUpdateDTO ( String id, String name, String description, Long medicId, LocalDateTime scheduled ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.medicId = medicId;
    this.scheduled = scheduled;
  }

  public String getId () {
    return id;
  }

  public void setId ( String id ) {
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

  public Long getMedicId () {
    return medicId;
  }

  public void setMedicId ( Long medicId ) {
    this.medicId = medicId;
  }

  public LocalDateTime getScheduled () {
    return scheduled;
  }

  public void setScheduled ( LocalDateTime scheduled ) {
    this.scheduled = scheduled;
  }
}
