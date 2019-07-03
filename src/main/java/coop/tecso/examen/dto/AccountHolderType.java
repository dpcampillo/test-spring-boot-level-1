package coop.tecso.examen.dto;

public enum AccountHolderType {
	
	PHYSICAL_PERSON("P"), LEGAL_PERSON("L");

	AccountHolderType(String code) {
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}
}
