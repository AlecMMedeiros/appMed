package br.com.bcoder.appMed.dto.userDTO;

import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDTO;
import br.com.bcoder.appMed.model.UserModel;
import lombok.Getter;

import java.util.List;

@Getter
public class UserWithConsultationsDTO {
    private Long id;
    private String email;
    private String displayName;
    private List<ConsultationDTO> consultations;

    public UserWithConsultationsDTO(UserModel user, List<ConsultationDTO> consultations) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.consultations = consultations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setConsultations(List<ConsultationDTO> consultations) {
        this.consultations = consultations;
    }
}
