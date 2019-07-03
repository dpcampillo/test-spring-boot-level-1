package coop.tecso.examen.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MovementDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private AccountDto account;
	private LocalDateTime eventdate;
	private String typemov;
	@NotBlank
	@Size(max = 200)
	private String description;
	private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public LocalDateTime getEventdate() {
		return eventdate;
	}

	public void setEventdate(LocalDateTime eventdate) {
		this.eventdate = eventdate;
	}

	public String getTypemov() {
		return typemov;
	}

	public void setTypemov(String typemov) {
		this.typemov = typemov;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
