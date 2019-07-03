package coop.tecso.examen.config.advice;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import coop.tecso.examen.config.exception.DataNotValidException;
import coop.tecso.examen.config.exception.DependencyRecordException;
import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.config.exception.RecordNotFoundException;

@ControllerAdvice
public class CustomizedExceptionHandler {

	@ExceptionHandler(DataNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(DataNotValidException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), "Data not valid",
				ex.getMessage());
		return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), "Validation Failed",
				ex.getMessage());
		return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<ErrorResponse> handleException(DuplicateRecordException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), "Duplicate Key", ex.getMessage());
		return new ResponseEntity<>(excepcion, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DependencyRecordException.class)
	public ResponseEntity<ErrorResponse> handleException(DependencyRecordException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), "Dependency record",
				ex.getMessage());
		return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(RecordNotFoundException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), "Record not found",
				ex.getMessage());
		return new ResponseEntity<>(excepcion, HttpStatus.NOT_FOUND);
	}

}
