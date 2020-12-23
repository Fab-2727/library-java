package library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
