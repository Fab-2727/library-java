package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import library.model.Publisher;

@Transactional(readOnly = true)
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
