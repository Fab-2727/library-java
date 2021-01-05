package library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Stock")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = 2415805516173525009L;

	@Id
	@Column(name = "id_stock")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private int stock_book;

	// declaration of relationships
	@OneToOne(optional = false)
	@JoinColumn(name = "id_book", unique = true, nullable = false)
	private Book book;

	public Stock() {
	}

	public Stock(int stock_book) {
		super();
		this.stock_book = stock_book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStock_book() {
		return stock_book;
	}

	public void setStock_book(int stock_book) {
		this.stock_book = stock_book;
	}

}
