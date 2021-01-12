package library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{
	
	@Query("SELECT stock FROM Stock stock WHERE stock.id = ?1")
	public Optional<Stock> findOneStockById(Integer id);
	@Query("SELECT topic FROM Topic topic WHERE topic.book.id = ?1")
	public Optional<Stock> findByIdBook(Integer idBook);
}
