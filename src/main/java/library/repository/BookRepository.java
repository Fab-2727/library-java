package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

		
}
