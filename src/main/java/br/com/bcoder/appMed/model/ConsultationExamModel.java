package br.com.bcoder.appMed.model;

import br.com.bcoder.appMed.dto.ConsultationExamId;
import jakarta.persistence.*;

@Entity
@Table(name = "consultations_exams")
public class ConsultationExamModel {

  @EmbeddedId
  private ConsultationExamId id;
  @ManyToOne
  @MapsId("consultationId")
  @JoinColumn(name = "consultation_id", nullable = false)
  private ConsultationModel consultation;
  @ManyToOne
  @MapsId("examId")
  @JoinColumn(name = "exam_id", nullable = false)
  private ExamsModel exam;

  public ConsultationExamModel () {
  }

  public ConsultationExamModel ( ConsultationExamId id, ConsultationModel consultation, ExamsModel exam ) {
    this.id = id;
    this.consultation = consultation;
    this.exam = exam;
  }

  public ConsultationExamId getId () {
    return id;
  }

  public void setId ( ConsultationExamId id ) {
    this.id = id;
  }

  public ConsultationModel getConsultation () {
    return consultation;
  }

  public void setConsultation ( ConsultationModel consultation ) {
    this.consultation = consultation;
  }

  public ExamsModel getExam () {
    return exam;
  }

  public void setExam ( ExamsModel exam ) {
    this.exam = exam;
  }
}
