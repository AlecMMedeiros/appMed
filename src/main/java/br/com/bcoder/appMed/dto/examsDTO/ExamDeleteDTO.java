package br.com.bcoder.appMed.dto.examsDTO;

import lombok.Getter;

@Getter
public class ExamDeleteDTO {
    private String id;

    public ExamDeleteDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }
}
