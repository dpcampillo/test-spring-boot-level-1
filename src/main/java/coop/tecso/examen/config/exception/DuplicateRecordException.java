package coop.tecso.examen.config.exception;

public class DuplicateRecordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateRecordException(String message) {
		super(message);
	}

}
