package br.com.bcoder.appMed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "consultations")
public class ConsultationModel {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserModel user;
  @NotNull
  @Column(nullable = false)
  private String name;
  @NotNull
  @Column(nullable = false)
  private String description;
  @NotNull
  @Column(nullable = false)
  private String specialty;
  @ManyToMany
  @JoinTable(
      name = "consultations_exams",
      joinColumns = @JoinColumn(name = "consultation_id"),
      inverseJoinColumns = @JoinColumn(name = "exam_id")
  )
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

  public Long getId () {
    return id;
  }

  public void setId ( Long id ) {
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

  public void setExams ( Set<ExamsModel> exams ) {
    this.exams = exams;
  }

}
