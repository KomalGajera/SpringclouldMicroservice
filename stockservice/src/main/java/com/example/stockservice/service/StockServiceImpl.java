package com.example.stockservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stockservice.model.Stock;
import com.example.stockservice.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	Stock reStock=new Stock();

	@Override
	public Boolean findByIdAndQuantity(Long id, Long quantity) {
		// TODO Auto-generated method stub
		Optional<Stock> stock=stockRepository.findByIdAndQuantityGreaterThanEqual(id,quantity);
		if(!stock.isPresent()) {			
			return false;
		}else {
			reStock=stock.get();
			return true;
		}
	}

	@Override
	public Stock save(Stock stock) {
		// TODO Auto-generated method stub
		//Stock newStock=stockRepository.findById(stock.getId()).get();
		return stockRepository.save(stock);
	}


	@Override
	public Stock updateStock(Stock stock) {
		// TODO Auto-generated method stub
		Stock newstock=stockRepository.findById(stock.getId()).get();
		Long Rquantity=newstock.getQuantity();
		newstock.setQuantity(Rquantity-stock.getQuantity());
		return stockRepository.save(newstock);
	}

	@Override
	public Stock getStockById(Long id) {
		// TODO Auto-generated method stub		
		System.out.print("\n\n\n\n\n\n my old stock is:"+reStock);
		return stockRepository.findById(id).get();
	}

}
