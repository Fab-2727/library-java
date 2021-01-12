package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Optional<Book> getBookByID(Integer id) {
		return bookRepository.findOneBookById(id);
	}

	@Override
	public List<Book> getBooksByName(String bookName) {
		// test if it works
		List<Book> similarBookByName = bookRepository.findByBookName(bookName);
		return similarBookByName;
	}

	@Override
	public ArrayList<Book> getBooksByCategory(String category) {
		Optional<Topic> catFound = topicRepo.findByTopicName(category);

		if (catFound.isPresent()) {
			List<Book> booksByCat = bookRepository.findByIdTopic(catFound.get().getId());
			return (ArrayList<Book>) booksByCat;
		} else {
			return null; // Controller should interpret 'null' as topic doesn't exists
		}

	}

	@Override
	public ArrayList<Book> getBooksByAuthorName(String authorName) {

		List<Author> authorsFound = authorRepo.findByAuthorName(authorName);

		if (! authorsFound.isEmpty()) {
			// for testing purposes, only the first author
			List<Book> booksByAuthor = bookRepository.findByIdAuthor(authorsFound.get(0).getId());
			return (ArrayList<Book>) booksByAuthor;
		} else {
			return null; // Controller should interpret 'null' as author don't exists
		}
	}

	@Override
	public ArrayList<Book> getBooksByAuthorId(Integer authorId) {
		List<Book> booksFound = bookRepository.findByIdAuthor(authorId);
		if (!booksFound.isEmpty()) {
			return (ArrayList<Book>) booksFound;
		} else {
			return null; // Controller should interpret 'null' as author don't exists
		}
	}

	@Override
	public Book addNewBook(Book bookNew) {
		return bookRepository.save(bookNew);
	}

	// TOPIC methods IMPLEMENTATION
	@Override
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> allTopics = (ArrayList<Topic>) topicRepo.findAll();
		if (!allTopics.isEmpty()) {
			return allTopics;
		} else {
			return null; // Controller should interpret as NullPOinter
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
	@Transactional
	@Override
	public Boolean updateStock(Integer bookId, Integer stockBook) {
		// check for possibles ERRORS and Exceptions
		Optional<Stock> stockFound = stockRepo.findByIdBook(bookId);
		if (stockFound.isPresent()) {
			stockFound.get().setStock_book(stockBook);
			stockRepo.save(stockFound.get());
			return true;
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
	public ArrayList<Publisher> getAllPublishers() {
		ArrayList<Publisher> allPublishers = (ArrayList<Publisher>) publisherRepo.findAll();
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
	public Publisher updatePublisherData(Integer publisherId, Publisher publisherData) {
		if (publisherExists(publisherId)) {
			Publisher publisherToUpdate = publisherRepo.findOnePublisherById(publisherId).get();
			publisherToUpdate.setPublisherName(publisherData.getPublisherName());
			publisherToUpdate.setAddress(publisherData.getAddress());
			publisherToUpdate.setCountry(publisherData.getCountry());
			return publisherRepo.save(publisherToUpdate);
		} else {
			return null;
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

	public Author addNewAuthor(Author authorNew) {
		return authorRepo.save(authorNew);
	}

	@Override
	public Author updateAuthor(Integer authorId, Author authorData) {
		Optional<Author> authorRetrieved = authorRepo.findOneAuthorById(authorId);
		if (authorRetrieved.isPresent()) {
			Author authorFound = authorRetrieved.get();
			authorFound.setAuthorName(authorData.getAuthorName());
			authorFound.setAuthorLastName(authorData.getAuthorLastName());
			return authorRepo.save(authorFound);
		} else {
			return null;
		}
	}

}
