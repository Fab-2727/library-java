package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.model.Author;
import library.model.Book;
import library.model.Publisher;
import library.model.Stock;
import library.model.Topic;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.PublisherRepository;
import library.repository.StockRepository;
import library.repository.TopicRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepo;
	@Autowired
	private TopicRepository topicRepo;
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private AuthorRepository authorRepo;

	// BOOK methods IMPLEMENTATION
	@Override
	public Book getBookById(Integer id) {
		Optional<Book> bookRetrieved = bookRepository.findOneBookById(id);
		if (bookRetrieved.isPresent()) {
			return bookRetrieved.get();
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<Book> getAllBooks(){
		List<Book> booksRetrieved = bookRepository.findAll();
		if (! booksRetrieved.isEmpty()) {
			return booksRetrieved;
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByName(String bookName) {
		List<Book> booksByName = bookRepository.findByBookName(bookName);
		if (! booksByName.isEmpty()) {
			return booksByName;
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		Optional<Topic> catFound = topicRepo.findByTopicName(category); //each topic name should be unique in the database

		if (catFound.isPresent()) {
			List<Book> booksByCat = bookRepository.findByIdTopic(catFound.get().getId());
			return booksByCat;
		} else {
			return null; // Controller should interpret 'null' as topic doesn't exists or that none book has been retrieved
		}

	}

	@Override
	public List<Book> getBooksByAuthorName(String authorName) {
		Integer idAuthor = 0;
		List<Book> booksByAuthorName = new ArrayList<>();
		List<Author> authorsFound = authorRepo.findByAuthorName(authorName);
		
		if (! authorsFound.isEmpty()) {
			for (Author author : authorsFound) {
				idAuthor =  author.getId();
				booksByAuthorName.addAll(bookRepository.findByIdAuthor(idAuthor));
			}
			
			return booksByAuthorName;
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByAuthorId(Integer authorId) {
		List<Book> booksFound = bookRepository.findByIdAuthor(authorId);
		if (!booksFound.isEmpty()) {
			return booksFound;
		} else {
			return null; // Controller should interpret 'null' as author don't exists
		}
	}

	@Transactional
	@Override
	public boolean addNewBook (JSONObject dataNewBook) throws JSONException {

		// 'description' and 'pages' are the only properties to validate here.
		String isbn = dataNewBook.getString("isbn");
		String bookName = dataNewBook.getString("bookName");
		Integer publishYear = dataNewBook.getInt("publishYear");
		System.out.println("Bool" +  dataNewBook.has("price"));
		Double bookPrice = dataNewBook.getDouble("price");
		float bookPriceFl = dataNewBook.getFloat("price");
		Float bookPriceFloatTwo = dataNewBook.getFloat("price");
		
		
		// Validations over 'description' and 'pages'.
		String description = dataNewBook.has("description") ? dataNewBook.getString("description") : "";
		Integer pages = dataNewBook.has("pages") ? dataNewBook.getInt("pages") : 0;
		
		Integer idAuthor = dataNewBook.getInt("idAuthor");
		Integer idTopic = dataNewBook.getInt("idTopic");
		Integer idPublisher = dataNewBook.getInt("idPublisher");
		
		// Get entities:
		Optional<Author> authorRetrieve = authorRepo.findOneAuthorById(idAuthor);
		Optional<Topic> topicRetrieve = topicRepo.findOneTopicById(idTopic);
		Optional<Publisher> publisherRetrieve = publisherRepo.findOnePublisherById(idPublisher);
		
		if (authorRetrieve.isEmpty() ) {
			throw new JSONException("None Author found by id: " + idAuthor);
		}
		if (topicRetrieve.isEmpty()) {
			throw new JSONException("None Topic found by id: " + idTopic);
		}
		if (publisherRetrieve.isEmpty()) {
			throw  new JSONException("None Publisher found by id: " + idPublisher);
		}
		
		Book bookToPersist = new Book(isbn, bookName, publishYear, bookPrice, description, pages, authorRetrieve.get(), publisherRetrieve.get(), topicRetrieve.get());
		
		bookRepository.saveAndFlush(bookToPersist);
		
		return true;
	}
	
	
	@Transactional
	@Override
	public boolean updateBookInfo(Integer bookId, JSONObject jsonDataBook) throws JSONException {
		Optional<Book> bookRetrieved = bookRepository.findOneBookById(bookId);
		if ( bookRetrieved.isPresent() ) {
			Book currentBook = bookRetrieved.get();
			
			refreshBookInfo(currentBook, jsonDataBook);
			
			bookRepository.saveAndFlush(currentBook);
			
			return true;
		} else {
			return false;
		}
	}
	
	
	private void refreshBookInfo (Book currentBook, JSONObject infoBookUpdate) throws JSONException {
		
		currentBook.setIsbn(infoBookUpdate.getString("isbn"));
		currentBook.setBookName(infoBookUpdate.getString("book_name"));
		currentBook.setPublishYear(infoBookUpdate.getInt("publish_year"));
		currentBook.setPrice(  (Float) infoBookUpdate.get("book_price")  );
		currentBook.setDescription(infoBookUpdate.getString("description"));
		currentBook.setPages(infoBookUpdate.getInt("pages"));
		
		Integer idAuthor = infoBookUpdate.getInt("id_author");
		Integer idTopic = infoBookUpdate.getInt("id_topic");
		Integer idPublisher = infoBookUpdate.getInt("id_publisher");
		
		// Get entities:
		Optional<Author> authorRetrieve = authorRepo.findOneAuthorById(idAuthor);
		Optional<Topic> topicRetrieve = topicRepo.findOneTopicById(idTopic);
		Optional<Publisher> publisherRetrieve = publisherRepo.findOnePublisherById(idPublisher);
		
		if (authorRetrieve.isEmpty() ) {
			throw new JSONException("None Author found by id: " + idAuthor);
		}
		if (topicRetrieve.isEmpty()) {
			throw new JSONException("None Topic found by id: " + idTopic);
		}
		if (publisherRetrieve.isEmpty()) {
			throw  new JSONException("None Publisher found by id: " + idPublisher);
		}
		currentBook.setAuthor(authorRetrieve.get());
		currentBook.setTopic(topicRetrieve.get());
		currentBook.setPublisher(publisherRetrieve.get());
		
	}
	

	// TOPIC methods IMPLEMENTATION
	@Override
	public List<Topic> getAllTopics() {
		List<Topic> allTopics = topicRepo.findAll();
		if (!allTopics.isEmpty()) {
			return allTopics;
		} else {
			return null;
		}
	}
	
	@Override
	public Topic getTopicById(Integer topicId) {
		Optional<Topic> topicRetrieve = topicRepo.findOneTopicById(topicId);
		if (topicRetrieve.isPresent()) {
			return topicRetrieve.get();
		} else {
			return null;
		}
	}

	@Override
	public Topic getTopicByName(String topicName) {
		Optional<Topic> topicFound = topicRepo.findByTopicName(topicName);
		if (topicFound.isPresent()) {
			return topicFound.get();
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public Topic addNewTopic(Topic topicNew) {
		return topicRepo.save(topicNew);
	}

	// STOCK methods IMPLEMENTATION
	
	@Override
	public List<Stock> getAllStocks(){
		List<Stock> stocksRetrieved = stockRepo.findAll();
		
		if (! stocksRetrieved.isEmpty()) {
			return stocksRetrieved;
		} else {
			return null;
		}
	}
	
	@Transactional
	@Override
	public Stock updateStock(Integer bookId, Integer stockBook) {
		// check for possibles ERRORS and Exceptions
		Optional<Stock> stockFound = stockRepo.findByIdBook(bookId);
		if (stockFound.isPresent()) {
			Stock stockToUpdate = stockFound.get();
			stockToUpdate.setStock_book(stockBook);
			
			return stockRepo.save(stockFound.get());
		} else {
			return null; // stock not found
		}
	}

	@Override
	public Integer getStockByBookId(Integer bookId) {
		Optional<Stock> stockFound = stockRepo.findByIdBook(bookId);

		if (stockFound.isPresent()) {
			return stockFound.get().getStock_book();
		} else {
			return null;
		}
	}

	// PUBLISHER methods IMPLEMENTATION
	@Override
	public Publisher getPublisherById(Integer publisherId) {

		Optional<Publisher> publisherFound = publisherRepo.findOnePublisherById(publisherId);
		if (publisherFound.isPresent()) {
			return publisherFound.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Publisher> getAllPublishers() {
		List<Publisher> allPublishers = publisherRepo.findAll();
		if (!allPublishers.isEmpty()) {
			return allPublishers;
		} else {
			return null;
		}

	}
	
	@Transactional
	@Override
	public void addNewPublisher(Publisher publisherNew) {
		publisherRepo.save(publisherNew);
	}

	@Transactional
	@Override
	public Boolean deletePublisherById(Integer publisherId) {
		if (publisherRepo.existsById(publisherId)) {
			// About to delete publisher
			publisherRepo.deleteById(publisherId);
			//Publisher deleted
			return true;
		} else {
			return false;		// Controller should interpret 'false' as the entity with the given ID doesn't exists.
		}
	}
	
	//@Commit
	@Transactional
	@Override
	public boolean updatePublisherData(Integer publisherId, JSONObject jsonData) throws JSONException {
		if (publisherRepo.existsById(publisherId)) {
			Publisher pubToUpdate = publisherRepo.findOnePublisherById(publisherId).get();
			//System.out.println("Before new values: "+ pubToUpdate);
			if ( jsonData.has("publisher_name") && !(jsonData.getString("publisher_name") == "") ) 
				pubToUpdate.setPublisherName(jsonData.getString("publisher_name"));
			if ( jsonData.has("address") && !(jsonData.getString("address") == "") ) 
				pubToUpdate.setAddress(jsonData.getString("address"));
			if ( jsonData.has("country") && !(jsonData.getString("country") == "") ) 
				pubToUpdate.setCountry(jsonData.getString("country"));
			//System.out.println("With new values: "+ pubToUpdate.toString());
			publisherRepo.save(pubToUpdate);
			return true;
		} else {
			return false;
		}
		
	}
	

	public Boolean publisherExists(Integer idPublisher) {
		return publisherRepo.existsById(idPublisher);
	}

	// AUTHOR methods IMPLEMENTATION

	@Override
	public Author getAuthorById(Integer authorId) {
		Optional<Author> authorFound = authorRepo.findOneAuthorById(authorId);
		if (authorFound.isPresent()) {
			return authorFound.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Author> getAuthorsByName(String authorName) {
		List<Author> authorsRetrieved = authorRepo.findByAuthorName(authorName);
		if (!authorsRetrieved.isEmpty()) {
			return authorsRetrieved;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public Author addNewAuthor(Author authorNew) {
		return authorRepo.save(authorNew);
	}

	@Transactional
	@Override
	public boolean updateAuthor(Integer authorId, JSONObject authorData) throws JSONException {
		Optional<Author> authorRetrieved = authorRepo.findOneAuthorById(authorId);
		
		if (authorRetrieved.isPresent()) {
			Author authorToUpdate = authorRetrieved.get();
			if ( authorData.has("author_name") && !(authorData.getString("author_name") == "") ) 
				authorToUpdate.setAuthorName(authorData.getString("author_name"));
				
			authorRepo.save(authorToUpdate);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Author> getAllAuthors(){
		List<Author> authorsRetrieve =  authorRepo.findAll();
		if (! authorsRetrieve.isEmpty()) {
			return authorsRetrieve;
		} else {
			return null;
		}
	}

}
