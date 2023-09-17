package br.com.bcoder.appMed.dto.examsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ExamUpdateDTO {
    private String id;
    private String name;
    private String description;
    private Long medicId;
    private LocalDateTime scheduled;

    public ExamUpdateDTO(String id, String name, String description, Long medicId, LocalDateTime scheduled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.medicId = medicId;
        this.scheduled = scheduled;
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

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }
}
