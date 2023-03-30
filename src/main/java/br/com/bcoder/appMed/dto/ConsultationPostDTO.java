package br.com.bcoder.appMed.dto;

public class ConsultationPostDTO {
  private String name;
  private String description;
  private Long medic;
  private String specialty;

  public ConsultationPostDTO ( String name, String description, Long medic, String specialty ) {
    this.name = name;
    this.description = description;
    this.medic = medic;
    this.specialty = specialty;
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

  public Long getMedic () {
    return medic;
  }

  public void setMedic ( Long medic ) {
    this.medic = medic;
  }

  public String getSpecialty () {
    return specialty;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }
}
