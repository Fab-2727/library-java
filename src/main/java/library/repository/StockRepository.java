package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{

}
