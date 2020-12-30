package library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = -958993525449794383L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String authorName;
	private String authorLastName;

	// @OneToOne(optional=false, mappedBy="customerRecord")
	@OneToOne(optional = false, mappedBy = "author")
	private Book book;

	public Author() {
	}

	public Author(String authorName, String authorLastName) {
		super();
		this.authorName = authorName;
		this.authorLastName = authorLastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

}
