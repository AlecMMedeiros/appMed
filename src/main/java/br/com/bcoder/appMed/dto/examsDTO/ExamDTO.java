package br.com.bcoder.appMed.dto.examsDTO;

import br.com.bcoder.appMed.model.ExamsModel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class ExamDTO {
    private String id;
    private String name;
    private String description;
    private String requestedBy;
    private Long crm;
    private LocalDateTime scheduled;


    public ExamDTO(ExamsModel exam) {
        this.id = exam.getId();
        this.name = exam.getName();
        this.description = exam.getDescription();
        this.requestedBy = exam.getRequestedBy().getName();
        this.crm = exam.getRequestedBy().getCRM();
        this.scheduled = exam.getScheduled();
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

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDTO examDTO = (ExamDTO) o;
        return Objects.equals(id, examDTO.id) && Objects.equals(name, examDTO.name) && Objects.equals(description, examDTO.description) && Objects.equals(requestedBy, examDTO.requestedBy) && Objects.equals(scheduled, examDTO.scheduled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, requestedBy, scheduled);
    }
}
