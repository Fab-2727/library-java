package library.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;


/**
 * {@code ApiError} is a class that represents a simple structure.
 * The main function of this class is to return a comprehensible error
 * to the consumer so it can know with certainty what went wrong.
 * 
 * @author Fabrizio Sosa
 */
public class ApiError {

	private HttpStatus status;
	private int statusCode;
	private String message;
	private List<String> errors;
	private Throwable cause;
	
	/**
	 * 
	 * @param status must not be null {@link HttpStatus} 
	 * @param message should be coherent and meaningful {@link String}
	 */
	public ApiError(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	/**
	 * Use as default.
	 * @param status must not be null {@link HttpStatus} 
	 * @param statusCode {@link Integer} example: 404, 302, 307, etc..
	 * @param message should be coherent and meaningful {@link String}
	 */
	public ApiError( @NonNull HttpStatus status, @NonNull int statusCode, @NonNull String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}
	
	/**
	 * One extra param: String error.
	 * 
	 * @param status must not be null {@link HttpStatus} 
	 * @param statusCode {@link Integer} example: 404, 302, 307, etc..
	 * @param message should be coherent and meaningful {@link String}
	 */
	public ApiError( @NonNull HttpStatus status, @NonNull int statusCode, @NonNull String message, @NonNull String error) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.errors =  Arrays.asList(error);
	}
	
	/**
	 * 
	 * @param status must not be null {@link HttpStatus} 
	 * @param message should be coherent and meaningful {@link String}
	 * @param errors list of errors {@link List}
	 */
	public ApiError(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	/**
	 * 
	 * @param status must not be null {@link HttpStatus} 
	 * @param message should be coherent and meaningful {@link String}
	 * @param errors list of errors {@link String}
	 */
	public ApiError(HttpStatus status, String message, String error) {
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}
	
	/**
	 * 
	 * @param status must not be null {@link HttpStatus} 
	 * @param message should be coherent and meaningful {@link String}
	 * @param errors list of errors {@link List}
	 * @param cause exception {@link Throwable}
	 */
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

	@Override
	public String toString() {
		return "ApiError [status=" + status + ", statusCode=" + statusCode + ", message=" + message + ", errors="
				+ errors + ", cause=" + cause + "]";
	}
	
	
}
