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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.jayway.jsonpath.Option;

import javassist.NotFoundException;
import library.model.Book;
import library.model.Publisher;
import library.model.Stock;
import library.model.Topic;
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
	
	// Start of 'Book' entity controller methods
	
	@GetMapping(path = "/book", produces = "application/json")
	public  void getBookById(@NonNull @RequestParam("id") Integer idBook){
		//Optional<Book>
		return;
	}
	
	@GetMapping(path = "/book", produces = "application/json")
	public void getBooksByName(@NonNull @RequestParam("name-book") String nameBook) {
		
		return ;
		//return ArrayList;
	}
	
	@GetMapping(path = "/book/category", produces = "application/json")
	public void getBooksByCategory(@NonNull @RequestParam("book-category") String bookCategory ) {
		return; //ArrayList
	}
	
	@GetMapping(path = "/book/author", produces = "application/json")
	public void getBooksByAuthor(@NonNull @RequestParam("name-author") String nameAuthor) {
		return;
	}

	@GetMapping(path = "/book/author", produces = "application/json")
	public void getBooksByAuthor(@NonNull @RequestParam("author-id") Integer idAuthor) {
		return;
	}
	
	@PostMapping(path = "/book/add-book", consumes = "application/json", produces = "application/json")
	public void addNewBook(@Valid @NonNull @RequestBody Book book) {
		return;
	}
	
	// End of 'Book' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Topic' entity controller methods
	
	@GetMapping(path = "/topic/all", produces = "application/json" )
	public ResponseEntity<String> getAllTopics() {
		
		ArrayList<Topic> allTopics = libraryService.getAllTopics();
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PostMapping(path = "/topic/add-topic")
	public void addNewTopic(@Valid @NonNull @RequestBody Topic topic) {
		
		//call service method for new topic, make validation
		return;
	}
	
	// End of 'Topic' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Stock' entity controller methods
	
	@PutMapping(path = "stock/update", consumes = "application/json", produces = "application/json")
	public void updateStockOfBook(@NonNull @RequestParam("id-book") Integer idBook, @RequestParam("stock") Integer stock ) {
		return;
	}
	
	
	@GetMapping(path = "book/stock-by-id", produces = "application/json")
	public void getBookStockById (@NonNull @RequestParam("id") Integer idBook) {
		// implement getStockByBookId
		return;
	}
	
	// End of 'Stock' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Publisher' entity controller methods	
	
	// This method returns a publisher by id (QueryParam)
	// If the requested publisher doesn't exists: HTTP 404
	@GetMapping(path = "/publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Optional<Publisher>> getPublisherById (@NonNull @RequestParam("id") Integer idPublisher) {
		
		if ( libraryService.publisherExists(idPublisher) )  {
			Optional<Publisher> publisherFound = libraryService.getPublisherById(idPublisher);
			return ResponseEntity.status(HttpStatus.OK).body(publisherFound);
		} else {
			throw new library.exception.NotFoundException();
		}

	}
	
	// return all publishers
	@GetMapping(path = "/publisher/all", produces = "application/json")
	public ArrayList<Publisher> getAllPublisher() {
		ArrayList<Publisher> publishersRetrieved = libraryService.getAllPublishers();
		System.out.println(publishersRetrieved);
		return publishersRetrieved;
	}
	
	// Add new Publisher, receives a JSON
	@PostMapping(path = "/publisher/add-publisher", consumes = "application/json", produces = "application/json")
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

	@PutMapping(path = "/publisher/update-publisher", consumes = "application/json", produces = "application/json")
	public void updatePublisher (@Valid @NonNull @RequestBody Publisher dataNewPublisher) {
		return;
	}
	
	// End of 'Publisher' entity controller methods

	
	
	//@ExceptionHandler(NullPointerException.class)
	/*
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
	*/
	
	
	
}
