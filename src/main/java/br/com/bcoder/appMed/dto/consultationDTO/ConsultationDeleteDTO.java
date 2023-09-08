package br.com.bcoder.appMed.dto.consultationDTO;

import lombok.Getter;

@Getter
public class ConsultationDeleteDTO {
    private String id;

    public ConsultationDeleteDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }
}
