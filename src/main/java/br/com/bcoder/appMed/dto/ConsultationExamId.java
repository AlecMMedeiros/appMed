package br.com.bcoder.appMed.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.data.relational.core.mapping.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConsultationExamId implements Serializable {

  @Column(name = "consultation_id")
  private Long consultationId;
  @Column(name = "exam_id")
  private Long examId;

  public ConsultationExamId ( Long consultationId, Long examId ) {
    this.consultationId = consultationId;
    this.examId = examId;
  }

  public ConsultationExamId () {

  }

  public Long getConsultationId () {
    return consultationId;
  }

  public void setConsultationId ( Long consultationId ) {
    this.consultationId = consultationId;
  }

  public Long getExamId () {
    return examId;
  }

  public void setExamId ( Long examId ) {
    this.examId = examId;
  }

  @Override
  public boolean equals ( Object o ) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConsultationExamId that = (ConsultationExamId) o;
    return consultationId.equals(that.consultationId) && examId.equals(that.examId);
  }

  @Override
  public int hashCode () {
    return Objects.hash(consultationId, examId);
  }
}
