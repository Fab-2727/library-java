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
	public List<Book> getAllBooks();
	// This method can return 1 or n books, because of the use of LIKE in the query
	public List<Book> getBooksByName(String bookName);
	public ArrayList<Book> getBooksByCategory(String category);
	public ArrayList<Book> getBooksByAuthorName(String authorName);
	public ArrayList<Book> getBooksByAuthorId(Integer authorId);
	public Book addNewBook(Book bookNew);
	public Book updateBookInfo(Integer bookId, Book bookData);
	
	// Topic table
	public List<Topic> getAllTopics();
	public Topic getTopicByName(String topicName);
	public Topic addNewTopic(Topic topicNew);

	// Stock table
	public Boolean updateStock(Integer bookId, Integer stockBook);
	public Integer getStockByBookId(Integer bookId);

	// Publisher table
	public Publisher getPublisherById(Integer publisherId);
	public List<Publisher> getAllPublishers();
	public void addNewPublisher(Publisher publisherNew);
	public Publisher updatePublisherData(Integer publisherId, Publisher publisherData);
	public Boolean publisherExists(Integer publisherId);
	
	// Author table
	public Author getAuthorById(Integer authorId);
	public List<Author> getAuthorsByName(String authorName);
	public Author addNewAuthor(Author authorNew);
	public Author updateAuthor(Integer authorId, Author authorData);
	public List<Author> getAllAuthors();
	
}
