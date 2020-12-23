package library.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {

	//@IdClass
	//https://stackoverflow.com/questions/7146671/hibernate-foreign-key-as-part-of-primary-key
	@EmbeddedId
	private Book bookId;
	private String topicName;
	
	public Topic() {
	}

	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	

}
