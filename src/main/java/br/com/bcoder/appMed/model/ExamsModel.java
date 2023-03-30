package br.com.bcoder.appMed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class ExamsModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String name;
  @NotNull
  @Column(nullable = false)
  private String description;
  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "medic_id")
  private MedicModel requestedBy;
  @ManyToOne
  @JoinColumn(name = "consultation_id")
  private ConsultationModel consultation;
  @NotNull
  @Column(nullable = false)
  private LocalDateTime scheduled;

  public ConsultationModel getConsultation () {
    return consultation;
  }

  public void setConsultation ( ConsultationModel consultation ) {
    this.consultation = consultation;
  }

  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public ExamsModel () {
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

  public String getDescription () {
    return description;
  }

  public void setDescription ( String description ) {
    this.description = description;
  }

  public MedicModel getRequestedBy () {
    return requestedBy;
  }

  public void setRequestedBy ( MedicModel requestedBy ) {
    this.requestedBy = requestedBy;
  }

  public LocalDateTime getScheduled () {
    return scheduled;
  }

  public void setScheduled ( LocalDateTime scheduled ) {
    this.scheduled = scheduled;
  }

  public LocalDateTime getCreatedAt () {
    return createdAt;
  }

  public void setCreatedAt ( LocalDateTime createdAt ) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt () {
    return updatedAt;
  }

  public void setUpdatedAt ( LocalDateTime updatedAt ) {
    this.updatedAt = updatedAt;
  }
}
