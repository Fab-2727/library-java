package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
