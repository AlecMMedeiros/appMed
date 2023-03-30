package br.com.bcoder.appMed.dto;

public class MedicPostDTO {
  private String name;
  private String local;
  private Long crm;
  private String specialty;

  public MedicPostDTO ( String name, String local, Long crm, String specialty ) {
    this.name = name;
    this.local = local;
    this.crm = crm;
    this.specialty = specialty;
  }

  public String getName () {
    return name;
  }

  public void setName ( String name ) {
    this.name = name;
  }

  public String getLocal () {
    return local;
  }

  public void setLocal ( String local ) {
    this.local = local;
  }

  public Long getCrm () {
    return crm;
  }

  public void setCrm ( Long crm ) {
    this.crm = crm;
  }

  public String getSpecialty () {
    return specialty;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }
}
