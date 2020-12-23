package library.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import library.model.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookByName(String nameBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBookStockById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Book> booksByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> booksByAuthor(String nameAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

}
