package br.com.bcoder.appMed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "consultations")
public class ConsultationModel {
  @Id
  private String id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserModel user;
  @NotNull
  @Column(nullable = false)
  private String name;
  @NotNull
  @Column(nullable = false)
  private String description;
  @ManyToOne
  @JoinColumn(name = "medic_id")
  private MedicModel medic;
  @NotNull
  @Column(nullable = false)
  private String specialty;
  @OneToMany(mappedBy = "consultation", cascade = CascadeType.REMOVE , fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<ExamsModel> exams;
  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  public LocalDateTime getCreatedAt () {
    return createdAt;
  }

  public void setCreatedAt ( LocalDateTime createdAt ) {
    this.createdAt = createdAt;
  }

  public ConsultationModel ( ) {

  }

  public String getId () {
    return id;
  }

  public void setId ( String id ) {
    this.id = id;
  }

  public UserModel getUser () {
    return user;
  }

  public void setUser ( UserModel user ) {
    this.user = user;
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

  public String getSpecialty () {
    return specialty;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }

  public Set<ExamsModel> getExams () {
    return exams;
  }

  public void setExams ( ExamsModel exam ) {
    this.exams.add(exam);
  }

  public MedicModel getMedic () {
    return medic;
  }

  public void setMedic ( MedicModel medic ) {
    this.medic = medic;
  }

}
