package coop.tecso.examen.model;

import javax.persistence.Entity;

@Entity(name = "physicalperson")
public class PhysicalPerson extends AccountHolder {

	private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	private String identification;

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
