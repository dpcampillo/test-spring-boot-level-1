package coop.tecso.examen.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PhysicalPersonDto extends AccountHolderDto {

	private static final long serialVersionUID = 1L;

	@Size(max = 80)
	@NotBlank
	private String firstname;

	@Size(max = 250)
	@NotBlank
	private String lastname;

	@NotBlank
	private String identification;
	
	public PhysicalPersonDto() {
		this.setType(AccountHolderType.PHYSICAL_PERSON.getCode());
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

}
