package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

		
}
