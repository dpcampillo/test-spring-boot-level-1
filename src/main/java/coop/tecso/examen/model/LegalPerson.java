package coop.tecso.examen.model;

import javax.persistence.Entity;

@Entity(name = "legalperson")
public class LegalPerson extends AccountHolder {

	private static final long serialVersionUID = 1L;

	private String businessname;

	private Integer founded;

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
