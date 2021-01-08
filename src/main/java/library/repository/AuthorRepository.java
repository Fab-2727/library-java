package library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

	@Query("SELECT au FROM Author au WHERE au.id = ?1")
	public Optional<Author> findOneAuthorById(Integer id);
}
