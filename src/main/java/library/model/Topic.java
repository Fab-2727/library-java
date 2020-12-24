package library.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Topic {

	//@IdClass
	//https://stackoverflow.com/questions/7146671/hibernate-foreign-key-as-part-of-primary-key
	@OneToOne(optional = false, mappedBy = "topic")
	private Book book;
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
