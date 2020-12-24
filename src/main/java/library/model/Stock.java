package library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Stock {
	
	@Id
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "id_book", unique = true, nullable = false)
	private Book bookId;
	
	private int stock;
	
	public Stock() {
	}
	
	public Stock(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return this.stock;
	}
	public void setStock(int stock) {
		this.stock =  stock;
	}
	
}
