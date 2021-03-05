package library.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
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

import library.exception.ApiError;
import library.model.Author;
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
	public ResponseEntity<Object> getBookById(@NonNull @RequestParam("id") Integer idBook) {
		Book bookFound = libraryService.getBookById(idBook);
		if (bookFound != null) {
			return ResponseEntity.status(successfulHttpCode).body(bookFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't find any book by the id: " + idBook)
					, HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	@GetMapping(path = "/book/name", produces = "application/json")
	public ResponseEntity<Object> getBooksByName(@NonNull @RequestParam("name-book") String nameBook) {
		List<Book> booksFound = libraryService.getBooksByName(nameBook);
		
		if (booksFound != null && !booksFound.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(booksFound);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode)
							, notFoundHttpCode
							, "Couldn't retrieve any Book from the database by the name: " +nameBook),
					HttpStatus.valueOf(notFoundHttpCode));
		}
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
	public ResponseEntity<Object> getBooksByCategory(@NonNull @RequestParam("book-category") String bookCategory) {
		List<Book> booksFoundByCat = libraryService.getBooksByCategory(bookCategory);
		
		if (booksFoundByCat != null && !booksFoundByCat.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(booksFoundByCat);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode)
							, notFoundHttpCode
							, "Couldn't find any book that has the category: " +bookCategory),
					HttpStatus.valueOf(notFoundHttpCode));
		}
		
	}
	
	// cannot be implemented yet
	@GetMapping(path = "/book/author", produces = "application/json")
	public ResponseEntity<Object> getBooksByAuthorName(@NonNull @RequestParam("name") String nameAuthor) {
		
		List<Book> booksFoundByAuthorName =  libraryService.getBooksByAuthorName(nameAuthor);
		
		if (booksFoundByAuthorName != null && !booksFoundByAuthorName.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(booksFoundByAuthorName);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode)
							, notFoundHttpCode
							, "Couldn't find any book whose author is '"+ nameAuthor+"'"),
					HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	@GetMapping(path = "/book/author/id", produces = "application/json")
	public ResponseEntity<Object> getBooksByIdAuthor(@NonNull @RequestParam("author-id") Integer idAuthor) {
		List<Book> booksFoundByAuthorId = libraryService.getBooksByAuthorId(idAuthor);
		
		if (booksFoundByAuthorId != null && !booksFoundByAuthorId.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(booksFoundByAuthorId);
		} else {
			return new ResponseEntity<Object>(
					new ApiError(HttpStatus.valueOf(notFoundHttpCode)
							, notFoundHttpCode
							, "Couldn't find any book which has an author id of: "+ idAuthor),
					HttpStatus.valueOf(notFoundHttpCode));
		}
		
	}

	/**
	 * Método no implementado.
	 * @param book
	 * @return
	 */
	@PostMapping(path = "/deprecated-method", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addNewBook(@Valid @NonNull @RequestBody Book book) {
		return ResponseEntity.status(successfulHttpCode).body(book);
	}

	// Implementation of add-book with String as JSONObject
	@PostMapping(path = "/book/add-book", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addNewBookFeature(@NonNull @RequestBody String JsonMessageBook) {
		try {
			
			JSONObject jsonNewBook = new JSONObject(JsonMessageBook);
			
			if ( jsonNewBook == null || jsonNewBook.toString().equals("") ) {
				System.out.println("Invalid payload, cannot be empty");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, cannot be empty");
			}
			
			if ( ! jsonNewBook.has("isbn") || jsonNewBook.isNull("isbn") || jsonNewBook.getString("isbn").isEmpty() ) {
				System.out.println("Invalid payload, missing 'isbn' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'isbn' key");
			}
			
			if ( ! jsonNewBook.has("bookName") || jsonNewBook.isNull("bookName") || jsonNewBook.getString("bookName").isEmpty() ) {
				System.out.println("Invalid payload, missing 'bookName' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'bookName' key");
			}
			
			if ( ! jsonNewBook.has("publishYear") || jsonNewBook.isNull("publishYear") ) {
				System.out.println("Invalid payload, missing 'publishYear' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'publishYear' key");
			}
			
			if ( ! jsonNewBook.has("price") || jsonNewBook.isNull("price") ) {
				System.out.println("Invalid payload, missing 'price' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'price' key");
			}
			
			/*
			 * Description and Pages can be absent, be null or be empty. That's why there is no validations for those fields.
			 * 
			 * The JSON must have the ID's of the relationships entities.
			 * If the entity with the given ID isn't found, then an exception should be trigger.
			 * 
			 */
			
			if ( ! jsonNewBook.has("idAuthor") || jsonNewBook.isNull("idAuthor") ) {
				System.out.println("Invalid payload, missing 'idAuthor' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'idAuthor' key");
			}
			
			if ( ! jsonNewBook.has("idPublisher") || jsonNewBook.isNull("idPublisher") ) {
				System.out.println("Invalid payload, missing 'idPublisher' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'idPublisher' key");
			}
			
			if ( ! jsonNewBook.has("idTopic") || jsonNewBook.isNull("idTopic") ) {
				System.out.println("Invalid payload, missing 'idTopic' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'idTopic' key");
			}
			
			// Trying to persist book to the database
			try {
				libraryService.addNewBook(jsonNewBook);
				return ResponseEntity.status(createdHttpCode).body("{\"response\":\"Book created\"}");
			} catch (JSONException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(
						new ApiError(HttpStatus.valueOf(badRequestHttpCode), badRequestHttpCode,
						e.getMessage()),
						HttpStatus.valueOf(badRequestHttpCode));
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@PutMapping(path = "/book/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateBook(@NonNull @RequestBody String jsonMessageBook,  @RequestParam("id") Integer idBook) {
		try {
			
			JSONObject jsonBookUpdate = new JSONObject(jsonMessageBook);
			
			if ( jsonBookUpdate == null || jsonBookUpdate.toString().equals("") ) {
				System.out.println("Invalid payload, cannot be empty");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, cannot be empty");
			}
			
			// None validation over fields (except FKs). The validations should be address in the GUI.
			// Plus, one possibility is that the user wants to make all the changes he/she wants.
			
			if ( ! jsonBookUpdate.has("id_author") || jsonBookUpdate.isNull("id_author") ) {
				System.out.println("Invalid payload, missing 'id_author' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'id_author' key");
			}
			
			if ( ! jsonBookUpdate.has("id_publisher") || jsonBookUpdate.isNull("id_publisher") ) {
				System.out.println("Invalid payload, missing 'id_publisher' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'id_publisher' key");
			}
			
			if ( ! jsonBookUpdate.has("id_topic") || jsonBookUpdate.isNull("id_topic") ) {
				System.out.println("Invalid payload, missing 'id_topic' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'id_topic' key");
			}
			
			try {
				boolean rspService = libraryService.updateBookInfo(idBook, jsonBookUpdate);
				if (rspService) {
					return ResponseEntity.status(createdHttpCode).body("{\"response\":\"Book created\"}");
				}
				
				return new ResponseEntity<Object>(
						new ApiError(HttpStatus.valueOf(badRequestHttpCode),
						badRequestHttpCode,
						"None book found by the ID: " + idBook),
						HttpStatus.valueOf(badRequestHttpCode));
				
			} catch (JSONException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(
						new ApiError(HttpStatus.valueOf(badRequestHttpCode), badRequestHttpCode,
						e.getMessage()),
						HttpStatus.valueOf(badRequestHttpCode));
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		
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
			return ResponseEntity.status(serverErrorHttpCode)
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
		try {
			
			Stock stockFromResp = libraryService.updateStock(idBook, stock);
			
			if (stockFromResp != null) {
			// create a json response
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("response", "Stock updated");
				jsonResponse.put("stock", stockFromResp.getStock_book());
				
				return ResponseEntity.status(successfulHttpCode).body(jsonResponse.toString());
			} else {
				return new ResponseEntity<Object>(
						new ApiError(HttpStatus.valueOf(badRequestHttpCode)
								, badRequestHttpCode
								, "Couldn't update the entity requested", "None stock of book ID: "+idBook+" found to update"),
						HttpStatus.valueOf(badRequestHttpCode));
			}
		
		} catch (JSONException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(serverErrorHttpCode)
					, serverErrorHttpCode
					, "An unexpected error has occurred")
					, HttpStatus.valueOf(serverErrorHttpCode));
		}
	}
	
	// Stock by book ID
	@GetMapping(path = "book/stock/id-book", produces = "application/json")
	public ResponseEntity<Object> getBookStockById(@NonNull @RequestParam("id") Integer idBook) {
		Integer actualStock = libraryService.getStockByBookId(idBook);
		if (actualStock != null) {
			return ResponseEntity.status(successfulHttpCode).body("{\"stock\":\"" + actualStock + "\"}");
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
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
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't find any publisher by the id: " + idPublisher)
					, HttpStatus.valueOf(notFoundHttpCode));
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
	public ResponseEntity<Object> addNewPublisher(@Valid @NonNull @RequestBody Publisher publisher) {
		try {
			libraryService.addNewPublisher(publisher);
			return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Publisher created\"}");
		
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(badRequestHttpCode)
					, badRequestHttpCode
					, "Invalid usage: wrong payload"), 
					HttpStatus.valueOf(badRequestHttpCode));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(serverErrorHttpCode)
					, serverErrorHttpCode
					, "An unexpected error has occurred"), 
					HttpStatus.valueOf(serverErrorHttpCode));
		}
	}

	@PutMapping(path = "/publisher/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updatePublisher(@NonNull @RequestBody String pubToUpdateData ) throws IllegalArgumentException {
		try {
			
			JSONObject publisherToUpdateData = new JSONObject(pubToUpdateData);
			
			if ( publisherToUpdateData == null || publisherToUpdateData.toString() == "" ) {
				System.out.println("Invalid payload, cannot be empty");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, cannot be empty");
			} 

			// Common validations
			int publisherId = 0;
			if ( ! publisherToUpdateData.has("id_publisher") || publisherToUpdateData.isNull("id_publisher") || publisherToUpdateData.getInt("id_publisher") == 0 ) {
				System.out.println("Invalid payload, missing 'id_publisher' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'id_publisher' key");
			}
			publisherId = publisherToUpdateData.getInt("id_publisher");
			
			if ( ! publisherToUpdateData.has("publisher_name") || publisherToUpdateData.isNull("publisher_name") ) {
				System.out.println("Invalid payload, missing 'publisher_name' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'publisher_name' key");
			}
			
			if ( ! publisherToUpdateData.has("address") || publisherToUpdateData.isNull("address") ) {
				System.out.println("Invalid payload, missing 'address' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'address' key");
			}

			if ( ! publisherToUpdateData.has("country") || publisherToUpdateData.isNull("country") ) {
				System.out.println("Invalid payload, missing 'country' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'country' key");
			}
			
			boolean rspService = libraryService.updatePublisherData(publisherId, publisherToUpdateData);
			
			if (rspService) {
				return ResponseEntity.status(createdHttpCode).body("Created");
			} else {
				return ResponseEntity.status(serverErrorHttpCode)
						.body(" {\"response\":\"Couldn't update the publisher of ID: "+publisherToUpdateData.getInt("id_publisher")+"\"}");
			}
			
		} catch (JSONException ex) {
			System.out.println("Invalid message format, it must be JSON");
			ex.printStackTrace();
			return null;
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
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't find any author by the id: " + idAuthor), HttpStatus.valueOf(notFoundHttpCode));
		}

	}

	@GetMapping(path = "author/by-name", produces = "application/json")
	public ResponseEntity<Object> getAuthorByName(@NonNull @RequestParam("name") String nameAuthor) {
		List<Author> authorsFound = libraryService.getAuthorsByName(nameAuthor);
		if (authorsFound != null && !authorsFound.isEmpty()) {
			return ResponseEntity.status(successfulHttpCode).body(authorsFound);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't find any author found by the name of: " + nameAuthor),
					HttpStatus.valueOf(notFoundHttpCode));
		}
	}

	@PostMapping(path = "author/add-author", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addNewAuthor(@Valid @NonNull @RequestBody Author authorNew) {
		try {
			libraryService.addNewAuthor(authorNew);
			return ResponseEntity.status(createdHttpCode).body("{\"response\":\"Author Created\"}");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(badRequestHttpCode)
					, badRequestHttpCode
					, "Invalid usage: wrong payload")
					, HttpStatus.valueOf(badRequestHttpCode));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(serverErrorHttpCode)
					, serverErrorHttpCode
					, "An unexpected error has occurred")
					, HttpStatus.valueOf(serverErrorHttpCode));
		}
	}

	@PutMapping(path = "author/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateAuthor(@Valid @NonNull @RequestBody String authorData) {
		try {
			
			JSONObject authorDataJson =  new JSONObject(authorData);
			
			if ( authorDataJson == null || authorDataJson.toString() == "" ) {
				System.out.println("Invalid payload, cannot be empty");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, cannot be empty");
			} 

			// Common validations
			int idAuthor = 0;
			if ( ! authorDataJson.has("id_author") || authorDataJson.isNull("id_author") || authorDataJson.getInt("id_author") == 0 ) {
				System.out.println("Invalid payload, missing 'id_author' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'id_author' key");
			}
			idAuthor = authorDataJson.getInt("id_author");
			
			if ( ! authorDataJson.has("author_name") || authorDataJson.isNull("author_name") ) {
				System.out.println("Invalid payload, missing 'author_name' key");
				return ResponseEntity.status(badRequestHttpCode).body("Invalid payload, missing 'author_name' key");				
			}
			
			boolean rspService = libraryService.updateAuthor(idAuthor, authorDataJson);
			
			if (rspService) {
				return ResponseEntity.status(successfulHttpCode).body("{\"response\":\"Author updated\"}");
			}
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(notFoundHttpCode)
					, notFoundHttpCode
					, "Couldn't update author of ID: "+ idAuthor)
					, HttpStatus.valueOf(notFoundHttpCode));

		} catch (JSONException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(badRequestHttpCode)
					, badRequestHttpCode
					, "Invalid usage, body must be JSON")
					, HttpStatus.valueOf(badRequestHttpCode));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ApiError(HttpStatus.valueOf(serverErrorHttpCode)
					, serverErrorHttpCode
					, "An unexpected error has occurred")
					, HttpStatus.valueOf(serverErrorHttpCode));
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
