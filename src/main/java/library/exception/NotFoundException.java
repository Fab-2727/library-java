package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity requested not found.")
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5604704006553947234L;
	
}
