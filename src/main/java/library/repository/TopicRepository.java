package library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

	@Query("SELECT topic FROM Topic topic WHERE topic.id = ?1")
	public Optional<Topic> findOneTopicById(Integer id);
	
}
