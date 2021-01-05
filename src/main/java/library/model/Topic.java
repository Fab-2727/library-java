package library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Topic")
public class Topic implements Serializable {
	
	private static final long serialVersionUID = -5947853740101693429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String topic_name;

	// Declaration of relationships
	@OneToOne(optional = false, mappedBy = "topic")
	private Book book;

	public Topic() {
	}

	public Topic(String topic_name) {
		super();
		this.topic_name = topic_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

}
