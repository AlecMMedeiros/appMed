package br.com.bcoder.appMed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "exams")
public class ExamsModel {
    @Id
    private String id;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private String description;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "medic_id")
    private MedicModel requestedBy;
    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private ConsultationModel consultation;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime scheduled;

    public void setConsultation(ConsultationModel consultation) {
        this.consultation = consultation;
    }

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ExamsModel() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequestedBy(MedicModel requestedBy) {
        this.requestedBy = requestedBy;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
