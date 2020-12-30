package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
