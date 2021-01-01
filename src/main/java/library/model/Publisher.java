package library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Publisher implements Serializable {

	private static final long serialVersionUID = 2104087270831931122L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String publisherName;
	@NotNull
	private String address;
	@NotNull
	private String country;

	// Declaration of relationships
	@OneToOne(optional = false, mappedBy = "publisher")
	private Book book;

	public Publisher() {
	}

	public Publisher(String publisherName, String address, String country) {
		super();
		this.publisherName = publisherName;
		this.address = address;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		String StrPublisherToString = "Publisher: "+this.publisherName+" "+this.address +" "+ this.country;
		return StrPublisherToString;
	}

}
