package library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Topic")
public class Topic implements Serializable {
	
	private static final long serialVersionUID = -5947853740101693429L;

	@Id
	@Column(name = "id_topic")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "topic_name")
	private String topicName;

	// Declaration of relationships
	@OneToOne(optional = false, mappedBy = "topic")
	private Book book;

	public Topic() {
	}

	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic_name() {
		return topicName;
	}

	public void setTopic_name(String topicName) {
		this.topicName = topicName;
	}

}
