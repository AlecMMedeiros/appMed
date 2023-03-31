package br.com.bcoder.appMed.dto.examsDTO;


import java.time.LocalDateTime;

public class ExamPostDTO {
  private String name;
  private String description;
  private Long medicId;
  private Long consultationId;
  private LocalDateTime scheduled;

  public ExamPostDTO ( String name, String description, Long medicId, Long consultationId, LocalDateTime scheduled ) {
    this.name = name;
    this.description = description;
    this.medicId = medicId;
    this.consultationId = consultationId;
    this.scheduled = scheduled;
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

  public Long getConsultationId () {
    return consultationId;
  }

  public void setConsultationId ( Long consultationId ) {
    this.consultationId = consultationId;
  }

  public LocalDateTime getScheduled () {
    return scheduled;
  }

  public void setScheduled ( LocalDateTime scheduled ) {
    this.scheduled = scheduled;
  }
}
