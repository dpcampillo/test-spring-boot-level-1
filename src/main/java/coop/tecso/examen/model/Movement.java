package coop.tecso.examen.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movement extends AbstractPersistentObject {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idaccount")
	private Account account;

	private LocalDateTime eventdate;

	private String typemov;

	private String description;

	private Double amount;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
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
