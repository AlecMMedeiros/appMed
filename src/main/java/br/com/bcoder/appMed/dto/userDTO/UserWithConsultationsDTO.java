package br.com.bcoder.appMed.dto.userDTO;

import br.com.bcoder.appMed.dto.consultationDTO.ConsultationDTO;
import br.com.bcoder.appMed.model.UserModel;

import java.util.List;

public class UserWithConsultationsDTO {
  private Long id;
  private String email;
  private String displayName;
  private List<ConsultationDTO> consultations;

  public UserWithConsultationsDTO( UserModel user, List<ConsultationDTO> consultations) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.displayName = user.getDisplayName();
    this.consultations = consultations;
  }

  public Long getId () {
    return id;
  }

  public void setId ( Long id ) {
    this.id = id;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail ( String email ) {
    this.email = email;
  }

  public String getDisplayName () {
    return displayName;
  }

  public void setDisplayName ( String displayName ) {
    this.displayName = displayName;
  }

  public List<ConsultationDTO> getConsultations () {
    return consultations;
  }

  public void setConsultations ( List<ConsultationDTO> consultations ) {
    this.consultations = consultations;
  }
}
