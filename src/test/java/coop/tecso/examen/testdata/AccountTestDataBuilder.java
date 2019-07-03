package coop.tecso.examen.testdata;

import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.dto.CurrencyCode;
import coop.tecso.examen.model.Account;

public class AccountTestDataBuilder {

	private Long id;

	public AccountTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public AccountDto buildDto() {
		AccountDto account = new AccountDto();
		account.setId(id);
		account.setCurrency(CurrencyCode.USD.getCode());
		account.setBalance(5000.0);
		account.setNumber("1051458999");
		return account;
	}

	public Account buildModel() {
		Account account = new Account();
		account.setId(id);
		account.setCurrency(CurrencyCode.USD.getCode());
		account.setBalance(5000.0);
		account.setNumber("1051458999");
		return account;
	}

}
