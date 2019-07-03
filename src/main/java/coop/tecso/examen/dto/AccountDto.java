package coop.tecso.examen.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	@Size(max = 50)
	private String number;
	@NotBlank
	@Size(max = 3)
	private String currency;
	@NotNull
	private Double balance;
	private AccountHolderDto accountHolder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountHolderDto getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolderDto accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	


}
