package br.com.bcoder.appMed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Email
  @NotNull
  @Column(nullable = false, unique = true)
  private String email;
  @NotNull
  @Column(nullable = false)
  private String displayName;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(nullable = false)
  private String password;
  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  @JsonIgnore
  private List<MedicModel> medics = new ArrayList<>();
  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE , fetch = FetchType.EAGER)
  @JsonIgnore
  private List<ConsultationModel> consultations = new ArrayList<>(); //Review code: Why List and noy Set ?
  public UserModel () {
  }

  public Long getId () {
    return id;
  }

  public void setId ( Long id ) {
    this.id = id;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail ( String email ) {
    this.email = email;
  }

  public String getDisplayName () {
    return displayName;
  }

  public void setDisplayName ( String displayName ) {
    this.displayName = displayName;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword ( String password ) {
    this.password = password;
  }

  public List<ConsultationModel> getConsultations () {
    return consultations;
  }

  public void setConsultations ( ConsultationModel consultation ) {
    this.consultations.add(consultation);
  }

  public List<MedicModel> getMedics () {
    return medics;
  }

  public void setMedics ( MedicModel medic ) {
    this.medics.add(medic);
  }

}
