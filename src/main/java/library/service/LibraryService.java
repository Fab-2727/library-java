package library.service;

import java.util.ArrayList;
import java.util.Optional;

import library.model.Book;
import library.model.Publisher;
import library.model.Topic;

public interface LibraryService {

	// BOOK table
	public Book getBookByID(Integer id);
	// This method can return 1 or n books, because of the use of LIKE in the query
	public ArrayList<Book> getBooksByName(String bookName);
	public ArrayList<Book> getBooksByCategory(String category);
	public ArrayList<Book> getBooksByAuthorName(String authorName);
	public ArrayList<Book> getBooksByAuthorId(Integer authorId);
	public void addNewBook(Book bookNew);

	// Topic table
	public ArrayList<Topic> getAllTopics();
	public Topic addNewTopic(Topic topicNew);

	// Stock table
	public void updateStock(Integer bookId, Integer stockBook);
	public Integer getStockByBookId(Integer bookId);

	// Publisher table
	public Optional<Publisher> getPublisherById(Integer publisherId);
	public ArrayList<Publisher> getAllPublishers();
	public void addNewPublisher(Publisher publisherNew);
	public void updatePublisherData(Integer publisherId, Publisher publisherData);
	public Boolean publisherExists(Integer publisherId);
	
	
}
