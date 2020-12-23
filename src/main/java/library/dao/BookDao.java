package library.dao;

import java.util.ArrayList;

import library.model.Book;

public interface BookDao {
	Book getBookById(int id);
	Book getBookByName(String nameBook);
	int getBookStockById(int id);
	ArrayList<Book> booksByCategory(String category);
	ArrayList<Book> booksByAuthor(String nameAuthor);
	
	
}
