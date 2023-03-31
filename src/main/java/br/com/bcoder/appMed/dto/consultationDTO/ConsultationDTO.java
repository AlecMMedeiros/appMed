package br.com.bcoder.appMed.dto.consultationDTO;

import br.com.bcoder.appMed.dto.examsDTO.ExamDTO;
import br.com.bcoder.appMed.model.ConsultationModel;
import br.com.bcoder.appMed.model.ExamsModel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ConsultationDTO {
  private String id;
  private String name;
  private String description;
  private String specialty;
  private String medic;
  private LocalDateTime createdAt;
  private Set<ExamDTO> exams;

  public ConsultationDTO ( ConsultationModel consultationModel ) {
    this.id = consultationModel.getId();
    this.name = consultationModel.getName();
    this.description = consultationModel.getDescription();
    this.specialty = consultationModel.getSpecialty();
    this.medic = consultationModel.getMedic().getName();
    this.createdAt = consultationModel.getCreatedAt();
    this.exams = this.generateDTOSet(consultationModel);
  }

  public String getId () {
    return id;
  }

  public void setId ( String id ) {
    this.id = id;
  }

  public Set<ExamDTO> generateDTOSet( ConsultationModel consultationModel) {
    Set<ExamDTO> examDTOS = new HashSet<>();
    for (ExamsModel exam : consultationModel.getExams()) {
      examDTOS.add(new ExamDTO(exam));
    }
    return examDTOS;
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

  public LocalDateTime getCreatedAt () {
    return createdAt;
  }

  public void setCreatedAt ( LocalDateTime createdAt ) {
    this.createdAt = createdAt;
  }

  public void setDescription ( String description ) {
    this.description = description;
  }

  public String getMedic () {
    return medic;
  }

  public void setMedic ( String medic ) {
    this.medic = medic;
  }

  public String getSpecialty () {
    return specialty;
  }

  public void setSpecialty ( String specialty ) {
    this.specialty = specialty;
  }

  public Set<ExamDTO> getExams () {
    return exams;
  }

  public void setExams ( Set<ExamDTO> exams ) {
    this.exams = exams;
  }

  @Override
  public boolean equals ( Object o ) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConsultationDTO that = (ConsultationDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(specialty, that.specialty) && Objects.equals(createdAt, that.createdAt) && Objects.equals(exams, that.exams);
  }

  @Override
  public int hashCode () {
    return Objects.hash(name, description, specialty, createdAt, exams);
  }
}
