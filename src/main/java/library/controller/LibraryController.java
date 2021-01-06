package library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import library.model.Publisher;
import library.service.LibraryServiceImpl;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private LibraryServiceImpl libraryService;
	private ResponseEntity<String> responseSuccesful;
	private ResponseEntity<String> responseError;
	
	@GetMapping(path = "/index")
	public String index() {
		return "Welcome, this is the index page";
	}
	
	@PostMapping(path = "/add-publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNewPublisher(@Valid @NonNull @RequestBody Publisher publisher) {
		/*
		if (null == jsonMessage) {
			throw new IllegalArgumentException("Bad request: a JSON message must be provided as payload");
		}
		*/
		
		responseSuccesful = ResponseEntity.status(HttpStatus.CREATED).body("{\"response\":\"Publisher Created\"}");
		System.out.println("Payload received" + publisher.toString());
		return responseSuccesful;
		
	}
	
	// Method for getting publisher by ID
	@GetMapping(path = "/publisher", consumes = "application/json", produces = "application/json")
	public Optional<Publisher> getPublisherById (@NonNull @RequestParam("id") Integer idPublisher) {

		System.out.println("Id to find: "+idPublisher+" -");
		Optional<Publisher> publisherFound = libraryService.getPublisherById(idPublisher);
		System.out.println(publisherFound);
		
		Optional<Publisher> publisherFoundTwo = libraryService.getPublisherById(1);
		System.out.println("With hard-code value: " +publisherFoundTwo);
		System.out.println("With hard-code value: " + libraryService.getPublisherById(0));
		System.out.println("With hard-code value: " + libraryService.getPublisherById(2));
		return publisherFound;
	}
	
	@GetMapping(path = "/all", consumes = "application/json", produces = "application/json")
	public ArrayList<Publisher> getPublisherById () {
		
		ArrayList<Publisher> publishersRetrieved = libraryService.getAllPublishers();
		System.out.println(publishersRetrieved);
		return publishersRetrieved;
	}
	
	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity<String> handleException(  MethodArgumentNotValidException ex, WebRequest request){
		System.out.println("We are in the MethodArgumentNotValidException exception handler");
		System.out.println(ex);
		return new ResponseEntity<String>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		System.out.println("We are in the IllegalArgumentException handler");
		System.out.println(ex);
		return new ResponseEntity<String>("INVALID_USE_BODY_MUST_BE_JSON",  HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex, WebRequest request){
		System.out.println("We are in the BASIC exception");
		System.out.println(ex);
		return new ResponseEntity<String>("UNKOWN ERROR",  HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
