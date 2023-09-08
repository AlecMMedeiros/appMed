package br.com.bcoder.appMed.dto.consultationDTO;

import lombok.Getter;

@Getter
public class ConsultationPostDTO {
    private String name;
    private String description;
    private Long medic;
    private String specialty;

    public ConsultationPostDTO(String name, String description, Long medic, String specialty) {
        this.name = name;
        this.description = description;
        this.medic = medic;
        this.specialty = specialty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMedic(Long medic) {
        this.medic = medic;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
