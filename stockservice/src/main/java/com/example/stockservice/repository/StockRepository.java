package com.example.stockservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stockservice.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByIdAndQuantityGreaterThanEqual(Long id, Long quantity);

}
