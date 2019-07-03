package coop.tecso.examen.dto;

public enum CurrencyCode {

	USD("USD"), ARS("COP"), EUR("EUR");

	CurrencyCode(String code) {
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}
}
