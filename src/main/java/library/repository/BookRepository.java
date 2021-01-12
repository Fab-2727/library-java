package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import library.model.Book;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT book FROM Book book WHERE book.id = ?1")
	public Optional<Book> findOneBookById(Integer id);
	
	// Query for books by name
	public List<Book> findByBookName(String bookName);
	
	// Query for books by CATEGORY
	@Query("SELECT book FROM Book book WHERE book.topic.id = ?1")
	public List<Book> findByIdTopic (Integer idTopic);
	
	// Query for books by ID_AUTHOR
	@Query("SELECT book FROM Book book WHERE book.author.id = ?1")
	public List<Book> findByIdAuthor(Integer idAuthor);
	
	
}
