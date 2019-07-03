package coop.tecso.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "accountholder")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccountHolder extends AbstractPersistentObject {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String rut;
	private String type;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
