package br.com.bcoder.appMed.dto.examsDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ExamPostDTO {
    private String name;
    private String description;
    private Long medicId;
    private String consultationId;
    private LocalDateTime scheduled;

    public ExamPostDTO(String name, String description, Long medicId, String consultationId, LocalDateTime scheduled) {
        this.name = name;
        this.description = description;
        this.medicId = medicId;
        this.consultationId = consultationId;
        this.scheduled = scheduled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }
}
