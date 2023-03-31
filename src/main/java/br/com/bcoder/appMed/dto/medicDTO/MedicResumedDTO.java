package br.com.bcoder.appMed.dto.medicDTO;

import br.com.bcoder.appMed.model.MedicModel;

public class MedicResumedDTO {
  private String name;
  private String local;
  private Long crm;
  private String specialty;

  public MedicResumedDTO () {
  }

  public MedicResumedDTO ( MedicModel medicModel ) {
    this.name = medicModel.getName();
    this.local = medicModel.getLocal();
    this.crm = medicModel.getCRM();
    this.specialty = medicModel.getSpecialty();
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
