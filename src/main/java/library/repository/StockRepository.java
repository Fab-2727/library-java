package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{

}
