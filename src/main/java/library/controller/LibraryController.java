package library.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.exception.NotFoundException;
import library.model.Author;
import library.model.Book;
import library.model.Publisher;
import library.model.Stock;
import library.model.Topic;
import library.repository.TopicRepository;
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
	public ArrayList<Topic> getAllTopics() {
		
		// making request to DB
		ArrayList<Topic> allTopics = libraryService.getAllTopics();
		if (  allTopics != null && ! allTopics.isEmpty()) {
			return allTopics;
		} else {
			throw new NotFoundException();
		}
	}
	
	@GetMapping(path = "/topic", produces = "application/json")
	public Topic getTopicByName(@NonNull @RequestParam("name") String nameTopic){
		
		Topic topicFound = libraryService.getTopicByName(nameTopic);
		if (topicFound != null) {
			return topicFound;
		} else {
			throw new NotFoundException();
		}
	}
	
	@PostMapping(path = "/topic/add-topic")
	public ResponseEntity<String> addNewTopic(@Valid @NonNull @RequestBody Topic topic) {
		libraryService.addNewTopic(topic);
		return ResponseEntity.status(HttpStatus.CREATED).body("{\"response\":\"Topic created\"}");
	}
	
	// End of 'Topic' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Stock' entity controller methods
	
	@PutMapping(path = "stock/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateStockOfBook(@NonNull @RequestParam("id-book") Integer idBook, @RequestParam("stock") Integer stock ) {
		Boolean respService = libraryService.updateStock(idBook, stock);
		if (respService) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"Stock updated\"}");
		} else {
			throw new NotFoundException();
		}
	}
	
	@GetMapping(path = "book/stock/id-book", produces = "application/json")
	public ResponseEntity<String> getBookStockById (@NonNull @RequestParam("id") Integer idBook) {
		Integer actualStock = libraryService.getStockByBookId(idBook);
		if (actualStock != null) {
			return ResponseEntity.status(HttpStatus.OK).body("{ \"response\":\"200\", \"stock\":\""+actualStock+"\"}");
		} else {
			throw new NotFoundException();
		}
		
	}
	
	// End of 'Stock' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Publisher' entity controller methods	
	
	// This method returns a publisher by id (QueryParam)
	// If the requested publisher doesn't exists: HTTP 404
	@GetMapping(path = "/publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Publisher> getPublisherById (@NonNull @RequestParam("id") Integer idPublisher) {
		if ( libraryService.publisherExists(idPublisher) )  {
			Publisher publisherFound = libraryService.getPublisherById(idPublisher);
			return ResponseEntity.status(HttpStatus.OK).body(publisherFound);
		} else {
			throw new library.exception.NotFoundException();
		}

	}
	
	// return all publishers
	@GetMapping(path = "/publisher/all", produces = "application/json")
	public ArrayList<Publisher> getAllPublisher() {
		ArrayList<Publisher> publishersRetrieved = libraryService.getAllPublishers();
		
		if (publishersRetrieved != null && ! publishersRetrieved.isEmpty() ) {
			return publishersRetrieved;
		} else {
			throw new NotFoundException();
		}
	}
	
	// Add new Publisher, receives a JSON
	@PostMapping(path = "/publisher/add-publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNewPublisher(@Valid @NonNull @RequestBody Publisher publisher) {
		try {
			System.out.println("Payload received" + publisher.toString());
			libraryService.addNewPublisher(publisher);
			responseSuccesful = ResponseEntity.status(HttpStatus.CREATED).body("{\"response\":\"Publisher Created\"}");
			return responseSuccesful;
		} catch (Exception e) {
			System.out.println(e);
		}
		return responseError = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"ERROR\":\"500 - SERVER ERROR\"}");
		
	}

	@PutMapping(path = "/publisher/update", consumes = "application/json", produces = "application/json")
	public void updatePublisher (@Valid @NonNull @RequestBody Publisher dataNewPublisher) {
		return;
	}
	
	// End of 'Publisher' entity controller methods
	
	// ------------------------------------------------- //
	
	// Start of 'Author' entity controller methods	
	
	@GetMapping(path = "author", produces = "application/json")
	public Author getAuthorById(@NonNull @RequestParam("id") Integer idAuthor){
		Author authorFound = libraryService.getAuthorById(idAuthor);
		if (authorFound != null) {
			return authorFound;
		} else {
			throw new NotFoundException();
		}
	}
	
	@GetMapping(path = "author", produces = "application/json")
	public Author getAuthorByName(@NonNull @RequestParam("name") String nameAuthor){
		Author authorFound = libraryService.getAuthorsByName(nameAuthor);
		if (authorFound != null) {
			return authorFound;
		} else {
			throw new NotFoundException();
		}
	}
	
	@PostMapping(path = "author/add-author", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNewAuthor(@Valid @NonNull @RequestBody Author authorNew){
		try {
			libraryService.addNewAuthor(authorNew);
			responseSuccesful = ResponseEntity.status(HttpStatus.CREATED).body("{\"response\":\"Author Created\"}");
			return responseSuccesful;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		// This response is for testing purposes only, it should be a NullPointerException
		return responseError = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"ERROR\":\"500 - SERVER ERROR\"}");
		
	}
	
	
	
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
