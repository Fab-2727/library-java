package library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Publisher")
public class Publisher implements Serializable {

	private static final long serialVersionUID = 2104087270831931122L;

	@Id
	@Column(name = "id_publisher")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "publisher_name")
	private String publisherName;
	@NotNull
	@Column(name = "address")
	private String address;
	@NotNull
	@Column(name = "country")
	private String country;

	// Declaration of relationships
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;

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
		String StrPublisherToString = "Publisher: "+ this.id +" "+this.publisherName+" "+this.address +" "+ this.country;
		return StrPublisherToString;
	}

}
