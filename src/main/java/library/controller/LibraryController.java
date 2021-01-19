package library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.server.ResponseStatusException;

import library.exception.ApiError;
import library.exception.NotFoundException;
import library.model.Author;
import library.model.Book;
import library.model.Publisher;
import library.model.Stock;
import library.model.Topic;
import library.repository.PublisherRepository;
import library.repository.TopicRepository;
import library.service.LibraryServiceImpl;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryServiceImpl libraryService;

	@Value("${server.success.httpstatus}")
	private int successfulHttpCode;
	@Value("${server.created.httpstatus}")
	private int createdHttpCode;
	@Value("${server.badrequest.httpstatus}")
	private int badRequestHttpCode;
	@Value("${server.not.found.httpstatus}")
	private int notFoundHttpCode;
	@Value("${server.error.httpstatus}")
	private int serverErrorHttpCode;

	@GetMapping(path = "/index")
	public String index() {
		return "Welcome, this is the index page";
	}

	// Start of 'Book' entity controller methods

	@GetMapping(path = "/book", produces = "application/json")
	public void getBookById(@NonNull @RequestParam("id") Integer idBook) {
		// Optional<Book>
		return;
	}

	@GetMapping(path = " /book/name", produces = "application/json")
	public void getBooksByName(@NonNull @RequestParam("name-book") String nameBook) {
		System.out.println("Name received: " + nameBook);
		return;
		// return ArrayList;
	}

	@GetMapping(path = "/book/all", produces = "application/json")
	public ResponseEntity<Object> getAllBooks() {
		List<Book> AllBooks = libraryService.getAllBooks();
		if (AllBooks != null && !AllBooks.isEmpty() ) {
			return ResponseEntity.status(successfulHttpCode).body(AllBooks);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode)
							, notFoundHttpCode
							, "Couldn't retrieve any Book from the database"),
					HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	@GetMapping(path = "/book/category", produces = "application/json")
	public void getBooksByCategory(@NonNull @RequestParam("book-category") String bookCategory) {
		return; // ArrayList
	}

	@GetMapping(path = "/book/author", produces = "application/json")
	public void getBooksByAuthorName(@NonNull @RequestParam("name") String nameAuthor) {
		System.out.println("Name received: " + nameAuthor);
		return;
	}

	@GetMapping(path = "/book/author/id", produces = "application/json")
	public void getBooksByIdAuthor(@NonNull @RequestParam("author-id") Integer idAuthor) {
		return;
	}

	@PostMapping(path = "/book/add-book", consumes = "application/json", produces = "application/json")
	public void addNewBook(@Valid @NonNull @RequestBody Book book) {
		return;
	}

	// End of 'Book' entity controller methods

	// ------------------------------------------------- //

	// Start of 'Topic' entity controller methods

	// Retrieves all topics
	@GetMapping(path = "/topic/all", produces = "application/json")
	public ResponseEntity<Object> getAllTopics()  {
		List<Topic> allTopics = libraryService.getAllTopics();
		if (allTopics != null && !allTopics.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(allTopics);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode), notFoundHttpCode,
							"Couldn't retrieve any Topic from the database")
					,HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// Get one topic by Id
	@GetMapping(path = "/topic/by-id", produces = "application/json")
	public ResponseEntity<Object> getTopicById(@NonNull @RequestParam("id") Integer idTopic) {
		Topic topicFound = libraryService.getTopicById(idTopic);
		if (topicFound != null) {
			return ResponseEntity.status(successfulHttpCode).body(topicFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't find any topic by the id: " + idTopic)
					, HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// Get one topic by Name
	@GetMapping(path = "/topic", produces = "application/json")
	public ResponseEntity<Object> getTopicByName(@NonNull @RequestParam("name") String nameTopic) {
		Topic topicFound = libraryService.getTopicByName(nameTopic);
		if (topicFound != null) {
			return ResponseEntity.status(successfulHttpCode).body(topicFound);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode), notFoundHttpCode,
							"Couldn't find any topic by the name of: " + nameTopic),
					HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// Add one topic
	@PostMapping(path = "/topic/add-topic")
	public ResponseEntity<String> addNewTopic(@Valid @NonNull @RequestBody Topic topic) {

		try {
			libraryService.addNewTopic(topic);
			return ResponseEntity.status(createdHttpCode).body("{\"response\":\"Topic created\"}");
		} catch (IllegalArgumentException e) {
			// get Exception for logging.
			System.out.println(e);
			return ResponseEntity.status(badRequestHttpCode).body("{\"error\":\"Invalid payload\"}");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"response\":\"COULD NOT CREATE TOPIC\", \"error\":\""+e+"\" }");
		}

	}

	// End of 'Topic' entity controller methods

	// ------------------------------------------------- //

	// Start of 'Stock' entity controller methods

	@GetMapping(path = "stock/all", produces = "application/json")
	public ResponseEntity<Object> getAllStocks() {
		List<Stock> stocksFound = libraryService.getAllStocks();
		if (stocksFound != null && !stocksFound.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(stocksFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode),
					HttpStatus.NOT_FOUND.value(), "Couldn't retrieve any stock from the database"), HttpStatus.valueOf(notFoundHttpCode));

		}
	}

	@PutMapping(path = "stock/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateStockOfBook(@NonNull @RequestParam("id-book") Integer idBook,
			@RequestParam("stock") Integer stock)  {
		Boolean respService = libraryService.updateStock(idBook, stock);
		if (respService) {
			return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Stock updated\"}");
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(badRequestHttpCode)
							, badRequestHttpCode
							, "Couldn't update the entity requested", "None stock of book ID: "+idBook+" found to update"),
					HttpStatus.valueOf(badRequestHttpCode));

		}
	}

	@GetMapping(path = "book/stock/id-book", produces = "application/json")
	public ResponseEntity<Object> getBookStockById(@NonNull @RequestParam("id") Integer idBook) {
		Integer actualStock = libraryService.getStockByBookId(idBook);
		if (actualStock != null) {
			return ResponseEntity.status(successfulHttpCode).body("{\"stock\":\"" + actualStock + "\"}");
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, HttpStatus.NOT_FOUND.value()
					, "Couldn't find any stock of the book with id: " + idBook)
					, HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// End of 'Stock' entity controller methods

	// ------------------------------------------------- //

	// Start of 'Publisher' entity controller methods

	@GetMapping(path = "/publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getPublisherById(@NonNull @RequestParam("id") Integer idPublisher) {
		Publisher publisherRetrieved = libraryService.getPublisherById(idPublisher);
		if (publisherRetrieved != null) {
			return ResponseEntity.status(successfulHttpCode).body(publisherRetrieved);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode), notFoundHttpCode,
					"Couldn't find any publisher by the id: " + idPublisher), HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// return all publishers
	@GetMapping(path = "/publisher/all", produces = "application/json")
	public ResponseEntity<Object> getAllPublisher() {
		List<Publisher> publishersRetrieved = libraryService.getAllPublishers();

		if (publishersRetrieved != null && !publishersRetrieved.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(publishersRetrieved);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Coudn't retrieve any Publisher from the database"), HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	// Add new Publisher, receives a JSON
	@PostMapping(path = "/publisher/add-publisher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNewPublisher(@Valid @NonNull @RequestBody Publisher publisher) {
		try {
			libraryService.addNewPublisher(publisher);
			return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Publisher created\"}");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(serverErrorHttpCode)
					.body(" {\"Response\":\"Couldn't add the publisher\",{\"Cause\":\"" + e.getMessage() + "\"\"}}");
		}
	}

	@PutMapping(path = "/publisher/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updatePublisher(@Valid @NonNull @RequestBody Publisher dataNewPublisher) {
		// change logic in try-catch
		try {
			libraryService.updatePublisherData(dataNewPublisher.getId(), dataNewPublisher);
			return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Publisher Created\"}");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(serverErrorHttpCode)
					.body(" {\"response\":\"Couldn't update the publisher of ID: "+dataNewPublisher.getId()+" and name "+dataNewPublisher.getPublisherName()+"\""
							+ ",{\"Cause\":\"" + e + "\"\"}}");
		}
	}

	// End of 'Publisher' entity controller methods

	// ------------------------------------------------- //

	// Start of 'Author' entity controller methods

	@GetMapping(path = "author", produces = "application/json")
	public ResponseEntity<Object> getAuthorById(@NonNull @RequestParam("id") Integer idAuthor) {
		Author authorFound = libraryService.getAuthorById(idAuthor);
		if (authorFound != null) {
			return ResponseEntity.status(successfulHttpCode).body(authorFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode), notFoundHttpCode,
					"Couldn't find any author by the id: " + idAuthor), HttpStatus.valueOf(notFoundHttpCode));
		}

	}

	@GetMapping(path = "author/by-name", produces = "application/json")
	public ResponseEntity<Object> getAuthorByName(@NonNull @RequestParam("name") String nameAuthor) {
		List<Author> authorsFound = libraryService.getAuthorsByName(nameAuthor);
		if (authorsFound != null && !authorsFound.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(authorsFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode),
					HttpStatus.NOT_FOUND.value(), "Couldn't find any author found by the name of: " + nameAuthor),
					HttpStatus.valueOf(notFoundHttpCode));

		}
	}

	@PostMapping(path = "author/add-author", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addNewAuthor(@Valid @NonNull @RequestBody Author authorNew) {
		try {
			libraryService.addNewAuthor(authorNew);
			return ResponseEntity.status(createdHttpCode).body("{\"response\":\"Author Created\"}");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return ResponseEntity.status(badRequestHttpCode).body("{\"error\":\"wrong payload\"}");
		} catch (Exception e) {
			// get exception for logging
			System.out.println(e);
			return ResponseEntity.status(badRequestHttpCode).body("{\"error\":\"" + e + "\"}");
		}
	}

	@PostMapping(path = "author/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateAuthor(@Valid @NonNull @RequestBody Author authorData) {
		try {
			libraryService.updateAuthor(authorData.getId(), authorData);
			return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Author updated\"}");
		} catch (Exception e) {
			return ResponseEntity.status(serverErrorHttpCode).body("{\"ERROR\":\" " + e + " \"}");
		}

	}

	@GetMapping(path = "author/all", produces = "application/json")
	public ResponseEntity<Object> getAllAuthors() {
		List<Author> authorsRetrieve = libraryService.getAllAuthors();
		if (authorsRetrieve != null && !authorsRetrieve.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(authorsRetrieve);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't retrieve any Author from the database")
					, HttpStatus.valueOf(notFoundHttpCode));
		}

	}

}
