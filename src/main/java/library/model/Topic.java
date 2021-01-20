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
@Table(name = "Topic")
public class Topic implements Serializable {

	private static final long serialVersionUID = -5947853740101693429L;

	@Id
	@Column(name = "id_topic")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name = "topic_name")
	private String topicName;

	// Declaration of relationships
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Topic other = (Topic) obj;

		if (id == 0) {
			if (other.id != 0) {
				return false;
			}
		} else {
			if (!(id == other.id)) {
				return false;
			}
		}
		if (topicName == null) {
			if (other.topicName != null) {
				return false;
			}
		} else if (!topicName.equals(other.topicName)) {
			return false;
		}

		return true;

	}

}
