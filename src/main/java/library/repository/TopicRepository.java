package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
