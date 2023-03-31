package br.com.bcoder.appMed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medic")
public class MedicModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String name;
  @NotNull
  @Column(nullable = false)
  private String local;
  @NotNull
  @Column(nullable = false)
  private Long crm;
  @NotNull
  @Column(nullable = false)
  private String specialty;
  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserModel user;
  @JsonIgnore
  @OneToMany(mappedBy = "medic", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private List<ConsultationModel> consultations = new ArrayList<>();
  @JsonIgnore
  @OneToMany(mappedBy = "requestedBy", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private List<ExamsModel> exams = new ArrayList<>();


  public MedicModel () {
  }


  public UserModel getUser () {
    return user;
  }

  public void setUser ( UserModel user ) {
    this.user = user;
  }

  public List<ConsultationModel> getConsultations () {
    return consultations;
  }

  public void setConsultations ( ConsultationModel consultation ) {
    this.consultations.add(consultation);
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
    return crm;
  }

  public void setCRM ( Long crm ) {
    this.crm = crm;
  }

  public String getSpecialty () {
    return specialty;
  }

  public void setConsultations ( List<ConsultationModel> consultations ) {
    this.consultations = consultations;
  }

  public List<ExamsModel> getExams () {
    return exams;
  }

  public void setExams ( List<ExamsModel> exams ) {
    this.exams = exams;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }

  @Override
  public String toString () {
    return "Medic Data{" +
        "name='" + name + '\'' +
        ", local='" + local + '\'' +
        ", crm=" + crm +
        ", specialty='" + specialty + '\'' +
        '}';
  }
}
