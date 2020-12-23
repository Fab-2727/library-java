package library.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// id_book isbn book_name publish_year book_price description pages
	// id_author id_topic id_publisher

	// How to handle FK in ORM/JPA api springboot??

	private String isbn;
	private String bookName;
	@Temporal(TemporalType.DATE)
	private Calendar publishYear;
	private float price;
	private String description;
	private int pages;

	public Book() {
	}

	public Book(String isbn, String bookName, Calendar publishYear, float price, String description, int pages) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.publishYear = publishYear;
		this.price = price;
		this.description = description;
		this.pages = pages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Calendar getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Calendar publishYear) {
		this.publishYear = publishYear;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
