package library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT book FROM Book book WHERE book.id = ?1")
	public Optional<Book> findOneBookById(Integer id);
		
}
