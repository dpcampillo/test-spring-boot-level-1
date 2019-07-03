package coop.tecso.examen.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AccountHolderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	private String rut;
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
