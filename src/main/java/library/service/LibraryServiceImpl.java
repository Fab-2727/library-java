package library.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.model.Book;
import library.model.Publisher;
import library.model.Topic;
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

	//					BOOK methods IMPLEMENTATION
	@Override
	public Book getBookByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Book> getBooksByName(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBooksByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
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
	public Topic addNewTopic(Topic topicNew) {
		return topicRepo.save(topicNew);
	}
	
	//					STOCK methods IMPLEMENTATION
	@Override
	public void updateStock(Integer bookId, Integer stockBook) {
		// check for possibles ERRORS and Exceptions
		/*
		Integer idFalse = 1;
		topicRepo.deleteById(idFalse);
		stockRepo.save(entity)
		*/
		return;
	}

	@Override
	public Integer getStockByBookId(Integer bookId) {
		// TODO
		return null;
	}

	//					PUBLISHER methods IMPLEMENTATION
	@Override
	public Optional<Publisher> getPublisherById(Integer publisherId) {
		return publisherRepo.findOnePublisherById(publisherId);
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
	
	
	
}
