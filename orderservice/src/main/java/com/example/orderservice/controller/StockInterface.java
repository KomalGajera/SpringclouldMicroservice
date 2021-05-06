package com.example.orderservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.orderservice.model.Stock;

@FeignClient(name = "stock-service", url = "http://localhost:8083")
@RequestMapping("stock")
public interface StockInterface {

	@GetMapping(value = "/checkstockbyid/{stockid}/{Quantity}")
	public Boolean checkStockById(@PathVariable("stockid") Long id, @PathVariable("Quantity") Long quantity);

	@PutMapping("/updatestock")
	public Stock updateStock(@RequestBody Stock stock);	

	@GetMapping(value = "/getstockbyid/{id}")
	public Stock getStockById(@PathVariable Long id);
	
	@PostMapping(value = "/update")
	public Stock save(@RequestBody Stock stock);

}
