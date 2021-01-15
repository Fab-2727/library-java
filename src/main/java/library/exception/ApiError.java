package library.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	private int statusCode;
	private String message;
	private List<String> errors;
	private Throwable cause;
	
	public ApiError(HttpStatus status, int statusCode, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public ApiError(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ApiError(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public ApiError(HttpStatus status, String message, String error) {
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}
	
	public ApiError(HttpStatus status, String message, List<String> errors, Throwable cause) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.cause = cause;
	}

	// Getters and setters
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public Throwable getCause() {
		return cause;
	}
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
