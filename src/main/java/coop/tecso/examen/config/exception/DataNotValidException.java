package coop.tecso.examen.config.exception;

public class DataNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotValidException(String message) {
		super(message);
	}
}
