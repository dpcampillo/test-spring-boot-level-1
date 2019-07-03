package coop.tecso.examen.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LegalPersonDto extends AccountHolderDto {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 100)
	private String businessname;

	@NotNull
	private Integer founded;

	public LegalPersonDto() {
		this.setType(AccountHolderType.LEGAL_PERSON.getCode());
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public Integer getFounded() {
		return founded;
	}

	public void setFounded(Integer founded) {
		this.founded = founded;
	}

}
