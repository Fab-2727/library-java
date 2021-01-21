package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Optional<Book> getBookByID(Integer id);
	// This method can return 1 or n books, because of the use of LIKE in the query
	public List<Book> getBooksByName(String bookName);
	public ArrayList<Book> getBooksByCategory(String category);
	public ArrayList<Book> getBooksByAuthorName(String authorName);
	public ArrayList<Book> getBooksByAuthorId(Integer authorId);
	public Book addNewBook(Book bookNew);
	public Book updateBookInfo(Integer bookId, Book bookData);
	
	// Topic table
	public List<Topic> getAllTopics();
	public Topic getTopicById(Integer topicId);
	public Topic getTopicByName(String topicName);
	public Topic addNewTopic(Topic topicNew);

	// Stock table
	public List<Stock> getAllStocks();
	public Boolean updateStock(Integer bookId, Integer stockBook);
	public Integer getStockByBookId(Integer bookId);

	// Publisher table
	public List<Publisher> getAllPublishers();
	public Publisher getPublisherById(Integer publisherId);
	public Boolean publisherExists(Integer publisherId);
	public void addNewPublisher(Publisher publisherNew);
	public Publisher updatePublisherData(Integer publisherId, Publisher publisherData);
	
	// Author table
	public List<Author> getAllAuthors();
	public Author getAuthorById(Integer authorId);
	public List<Author> getAuthorsByName(String authorName);
	public Author addNewAuthor(Author authorNew);
	public Author updateAuthor(Integer authorId, Author authorData);
	
	// test methods
	
	public boolean updatePubTest(Integer publisherId, JSONObject json) throws JSONException;
	
}
