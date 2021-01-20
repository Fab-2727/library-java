package library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Book")
public class Book implements Serializable {

	private static final long serialVersionUID = 3734501213315682975L;
	// id_book isbn book_name publish_year book_price description pages
	// id_author id_topic id_publisher
	// How to handle FK in ORM/JPA api springboot??

	@Id
	@Column(name = "id_book")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String isbn;

	@NotNull
	@Column(name = "book_name")
	private String bookName;

	@NotNull
	@Column(name = "publish_year")
	private int publishYear;

	@NotNull
	@Column(name = "book_price")
	private float price;

	@NotNull
	private String description;

	@NotNull
	private int pages;

	// Declaration of relationships

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_author", nullable = false, updatable = true)
	private Author author;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_publisher", nullable = false, updatable = true)
	private Publisher publisher;

	// ManyToOne relationship, many book -> one topic
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_topic", nullable = false, updatable = true)
	private Topic topic;

	@OneToOne(optional = false, mappedBy = "book")
	private Stock stock;

	// how to make a foreign key primary key in another table
	// or use a primary key

	// It's missing the relationshipt between book and the STOCK of it

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
