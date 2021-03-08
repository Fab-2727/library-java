package library.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import library.model.Author;
import library.model.Book;
import library.model.Publisher;
import library.model.Stock;
import library.model.Topic;

public interface LibraryService {

	// 13/01/2021	=> none DELETE method, implement later for all entities
	// BOOK table
	public List<Book> getAllBooks();
	public Book getBookById(Integer id);
	// This method can return 1 or n books, because of the use of LIKE in the query
	public List<Book> getBooksByName(String bookName);
	public List<Book> getBooksByCategory(String category);
	public List<Book> getBooksByAuthorName(String authorName);
	public List<Book> getBooksByAuthorId(Integer authorId);
	public boolean addNewBook(JSONObject dataNewBook) throws JSONException;
	public String updateBookInfo(Integer bookId, JSONObject dataUpdateBook) throws Exception;
	
	// Topic table
	public List<Topic> getAllTopics();
	public Topic getTopicById(Integer topicId);
	public Topic getTopicByName(String topicName);
	public Topic addNewTopic(Topic topicNew);

	// Stock table
	public List<Stock> getAllStocks();
	public Integer getStockByBookId(Integer bookId);
	public boolean addNewStock(JSONObject dataNewBook);
	public Stock updateStock(Integer bookId, Integer stockBook);

	// Publisher table
	public List<Publisher> getAllPublishers();
	public Publisher getPublisherById(Integer publisherId);
	public Boolean publisherExists(Integer publisherId);
	public void addNewPublisher(Publisher publisherNew);
	public boolean updatePublisherData(Integer publisherId, JSONObject publisherData) throws JSONException;
	public Boolean deletePublisherById(Integer publisherId);
	
	// Author table
	public List<Author> getAllAuthors();
	public Author getAuthorById(Integer authorId);
	public List<Author> getAuthorsByName(String authorName);
	public Author addNewAuthor(Author authorNew);
	public boolean updateAuthor(Integer authorId, JSONObject authorData) throws JSONException;
	
	// test methods
	
}
