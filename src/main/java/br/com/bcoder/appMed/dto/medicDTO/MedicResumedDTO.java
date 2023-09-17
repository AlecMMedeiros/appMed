package br.com.bcoder.appMed.dto.medicDTO;

import br.com.bcoder.appMed.model.MedicModel;
import lombok.Getter;

import java.util.Objects;

@Getter
public class MedicResumedDTO {
    private Long id;
    private String name;
    private String local;
    private Long crm;
    private String specialty;

    public MedicResumedDTO() {
    }

    public MedicResumedDTO(MedicModel medicModel) {
        this.id = medicModel.getId();
        this.name = medicModel.getName();
        this.local = medicModel.getLocal();
        this.crm = medicModel.getCRM();
        this.specialty = medicModel.getSpecialty();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicResumedDTO that = (MedicResumedDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(local, that.local) && Objects.equals(crm, that.crm) && Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, local, crm, specialty);
    }
}
