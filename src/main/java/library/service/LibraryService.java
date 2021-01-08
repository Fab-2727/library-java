package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import library.model.Author;
import library.model.Book;
import library.model.Publisher;
import library.model.Topic;

public interface LibraryService {

	// BOOK table
	public Optional<Book> getBookByID(Integer id);
	// This method can return 1 or n books, because of the use of LIKE in the query
	public List<Book> getBooksByName(String bookName);
	public ArrayList<Book> getBooksByCategory(String category);
	public ArrayList<Book> getBooksByAuthorName(String authorName);
	public ArrayList<Book> getBooksByAuthorId(Integer authorId);
	public void addNewBook(Book bookNew);

	// Topic table
	public ArrayList<Topic> getAllTopics();
	public Topic getTopicByName(String topicName);
	public Topic addNewTopic(Topic topicNew);

	// Stock table
	public Boolean updateStock(Integer bookId, Integer stockBook);
	public Integer getStockByBookId(Integer bookId);

	// Publisher table
	public Publisher getPublisherById(Integer publisherId);
	public ArrayList<Publisher> getAllPublishers();
	public void addNewPublisher(Publisher publisherNew);
	public void updatePublisherData(Integer publisherId, Publisher publisherData);
	public Boolean publisherExists(Integer publisherId);
	
	// Author table
	public Author getAuthorById(Integer authorId);
	public Author getAuthorsByName(String authorName);
	
	
}
