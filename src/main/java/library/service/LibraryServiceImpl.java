package library.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.model.Book;
import library.model.Publisher;
import library.repository.PublisherRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private PublisherRepository publisherRepo;

	@Override
	public Book getBookByID(int id) {
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
	public ArrayList<Book> getBooksByAuthorId(int authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewBook(Book bookNew) {
		// TODO Auto-generated method stub
	}

	// Publisher implementation
	@Override
	public ArrayList<Publisher> getAllPublishers() {
		return (ArrayList<Publisher>) publisherRepo.findAll();
	}

	@Override
	public void addNewPublisher(Publisher publisherNew) {
		publisherRepo.save(publisherNew);

	}

	@Override
	public void updatePublisherData(int publisherId, Publisher publisherData) {
		// TODO Auto-generated method stub

	}

}
