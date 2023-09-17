package br.com.bcoder.appMed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
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
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ExamsModel> exams;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ConsultationModel() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setExams(ExamsModel exam) {
        this.exams.add(exam);
    }

    public void setMedic(MedicModel medic) {
        this.medic = medic;
    }

}
