package coop.tecso.examen.config.advice;

import java.util.Date;

public class ErrorResponse {
	private Date date;
	private String message;
	private String description;

	public ErrorResponse() {
	}

	public ErrorResponse(Date date, String message, String description) {
		this.date = date;
		this.message = message;
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
