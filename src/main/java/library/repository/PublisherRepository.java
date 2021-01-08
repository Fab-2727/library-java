package library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

	
	@Query("SELECT pu FROM Publisher pu WHERE pu.id = ?1")
	public Optional<Publisher> findOnePublisherById(Integer id);
	
}
