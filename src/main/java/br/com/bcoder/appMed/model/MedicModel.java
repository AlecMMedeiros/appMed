package br.com.bcoder.appMed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "medic")
public class MedicModel {
  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String name;
  @NotNull
  @Column(nullable = false)
  private String local;
  @NotNull
  @Column(nullable = false, unique = true)
  private Long CRM;
  @NotNull
  @Column(nullable = false)
  private String Specialty;

  public MedicModel () {
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

  public String getLocal () {
    return local;
  }

  public void setLocal ( String local ) {
    this.local = local;
  }

  public Long getCRM () {
    return CRM;
  }

  public void setCRM ( Long CRM ) {
    this.CRM = CRM;
  }

  public String getSpecialty () {
    return Specialty;
  }

  public void setSpecialty ( String specialty ) {
    Specialty = specialty;
  }
}
