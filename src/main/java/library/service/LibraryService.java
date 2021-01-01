package library.service;

import java.util.ArrayList;

import library.model.Book;
import library.model.Publisher;
import library.model.Topic;

public interface LibraryService {

	// BOOK table
	public Book getBookByID(int id);
	// This method can return 1 or n books, because of the use of LIKE in the query
	public ArrayList<Book> getBooksByName(String bookName);
	public ArrayList<Book> getBooksByCategory(String category);
	public ArrayList<Book> getBooksByAuthorName(String authorName);
	public ArrayList<Book> getBooksByAuthorId(int authorId);
	public void addNewBook(Book bookNew);

	// Topic table
	public ArrayList<Topic> getAllTopics();
	public void addNewTopic(Topic topicNew);

	// Stock table
	public void updateStock(int bookId, int stockBook);
	public int getStockByBookId(int bookId);

	// Publisher table
	public Publisher getPublisherById(int publisherId);
	public ArrayList<Publisher> getAllPublishers();
	public void addNewPublisher(Publisher publisherNew);
	public void updatePublisherData(int publisherId, Publisher publisherData);
	
	
	
}
