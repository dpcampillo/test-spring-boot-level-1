package coop.tecso.examen.testdata;

import coop.tecso.examen.dto.LegalPersonDto;
import coop.tecso.examen.dto.PhysicalPersonDto;
import coop.tecso.examen.model.AccountHolder;

public class AccountHolderTestDataBuilder {

	private Long id = null;
	private String rut = "TEST_RUT";
	private String firstname = "TEST_FIRSTNAME";
	private String lastname = "TEST_LASTNAME";
	private String identification = "TEST_IDENT";

	private String businessname = "TEST_BUSINESSNAME";
	private Integer founded = 1990;

	public AccountHolderTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public AccountHolderTestDataBuilder withRut(String rut) {
		this.rut = rut;
		return this;
	}

	public AccountHolder build() {
		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setRut(rut);
		return accountHolder;
	}

	public PhysicalPersonDto buildPhysicalPersonDto() {
		PhysicalPersonDto physicalPersonDto = new PhysicalPersonDto();
		physicalPersonDto.setId(id);
		physicalPersonDto.setRut(rut);
		physicalPersonDto.setFirstname(firstname);
		physicalPersonDto.setLastname(lastname);
		physicalPersonDto.setIdentification(identification);
		return physicalPersonDto;
	}

	public LegalPersonDto buildLegalPersonDto() {
		LegalPersonDto legalPersonDto = new LegalPersonDto();
		legalPersonDto.setId(id);
		legalPersonDto.setRut(rut);
		legalPersonDto.setBusinessname(businessname);
		legalPersonDto.setFounded(founded);
		return legalPersonDto;
	}

}
