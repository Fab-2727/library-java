package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	//					BOOK methods IMPLEMENTATION
	@Override
	public Optional<Book> getBookByID(Integer id) {
		return bookRepository.findOneBookById(id);
	}
	
	@Override
	public List<Book> getBooksByName(String bookName) {
		// test if it works
		List<Book> similarBookByName = bookRepository.findBooksByName(bookName);
		return similarBookByName;
	}

	@Override
	public ArrayList<Book> getBooksByCategory(String category) {
		// Probably 3 joins
		Optional<Topic> catFound = this.getTopicByName(category);
		
		if ( catFound.isPresent() ) {
			List<Book> booksByCat = bookRepository.findBooksByIdTopic(catFound.get().getId());
			return (ArrayList<Book>) booksByCat;
		} else {
			return null; //Controller should interpret 'null' as topic doesn't exists
		}

	}

	@Override
	public ArrayList<Book> getBooksByAuthorName(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBooksByAuthorId(Integer authorId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addNewBook(Book bookNew) {
		// TODO Auto-generated method stub
	}
	
	//					TOPIC methods IMPLEMENTATION
	@Override
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> allTopics = (ArrayList<Topic>) topicRepo.findAll();
		return allTopics;
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
	
	@Override
	public Topic addNewTopic(Topic topicNew) {
		return topicRepo.save(topicNew);
	}
	
	//					STOCK methods IMPLEMENTATION
	@Override
	public Boolean updateStock(Integer bookId, Integer stockBook) {
		// check for possibles ERRORS and Exceptions
		Optional<Stock> stockFound = stockRepo.findByIdBook(bookId);
		if (stockFound.isPresent()) {
			stockFound.get().setStock_book(stockBook);
			stockRepo.save(stockFound.get());
			return true;
		} else {
			return false; // book or stock not found
		}

	}

	@Override
	public Integer getStockByBookId(Integer bookId) {
		return stockRepo.findByIdBook(bookId).get().getId();
	}

	//					PUBLISHER methods IMPLEMENTATION
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
		// if( rsp == null ) throw NotFound
		return (ArrayList<Publisher>) publisherRepo.findAll();
	}

	@Override
	public void addNewPublisher(Publisher publisherNew) {
		publisherRepo.save(publisherNew);

	}

	@Override
	public void updatePublisherData(Integer publisherId, Publisher publisherData) {
		// TODO Auto-generated method stub
	}

	public Boolean publisherExists(Integer idPublisher) {
		return publisherRepo.existsById(idPublisher);
	}

	//				AUTHOR methods IMPLEMENTATION
	
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
	public Author getAuthorsByName(String authorName) {
		Optional<Author> authorFound = authorRepo.findByAuthorName(authorName);
		if (authorFound.isPresent()) {
			return authorFound.get();
		} else {
			return null;
		}
	}
	
	
	
}
