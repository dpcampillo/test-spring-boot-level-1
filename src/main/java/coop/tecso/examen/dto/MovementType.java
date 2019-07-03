package coop.tecso.examen.dto;

public enum MovementType {
	
	CREDIT("C"), DEBIT("D");

	MovementType(String code) {
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}
}
