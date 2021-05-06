package com.example.stockservice.service;

import com.example.stockservice.model.Stock;

public interface StockService {

	Boolean findByIdAndQuantity(Long id, Long quantity);
	
	Stock save(Stock stock);

	Stock getStockById(Long id);

	Stock updateStock(Stock stock);

}
