package library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Book {

	// id_book isbn book_name publish_year book_price description pages
	// id_author id_topic id_publisher
	// How to handle FK in ORM/JPA api springboot??

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String isbn;
	private String bookName;
	private int publishYear;
	private float price;
	private String description;
	private int pages;
	// the non-owning side must use the mapped By element of the OneToOne annotation
	// to specify the relationship field or property of the owning side.
	/*
	 * @OneToOne(optional=false)
	 * 
	 * @JoinColumn( name="CUSTREC_ID", unique=true, nullable=false, updatable=false)
	 * public CustomerRecord getCustomerRecord() { return customerRecord; }
	 */

	// Declaration of relationships
	@OneToOne(optional = false)
	@JoinColumn(name = "id_author", unique = true, nullable = false)
	private Author author;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_publisher", unique = true, nullable = false)
	private Publisher publisher;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_topic", unique = true, nullable = false)
	private Topic topic;
	
	// how to make a foreign key primary key in another table
	// or use a primary key 
	
	public Book() {
	}

	public Book(String isbn, String bookName, int publishYear, float price, String description, int pages) {
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

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
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
