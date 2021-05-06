package com.example.stockservice.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockservice.model.Stock;
import com.example.stockservice.model.StockDto;
import com.example.stockservice.service.OrderSender;
import com.example.stockservice.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Transactional
@Valid
@Slf4j
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	MessageSource messages;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	OrderSender sender;

	@StreamListener(target = Processor.INPUT)
	public void receiveOrder(StockDto stock) throws JsonProcessingException {

		log.info("recieved order:[" + stock.toString() + "]");
		Stock newstock = modelMapper.map(stock, Stock.class);
		Boolean result = stockService.findByIdAndQuantity(newstock.getId(), newstock.getQuantity());
		sender.send(result);
		System.out.println("Order received: {}" + mapper.writeValueAsString(stock));
	}

	@GetMapping(value = "/checkstockbyid/{stockid}/{Quantity}")
	public Boolean checkStockById(@PathVariable("stockid") Long id, @PathVariable("Quantity") Long quantity) {

		return stockService.findByIdAndQuantity(id, quantity);

	}

	@GetMapping(value = "/getstockbyid/{id}")
	public Stock getStockById(@PathVariable Long id) {

		return stockService.getStockById(id);

	}

	@PutMapping("/updatestock")
	public Stock updateStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);

	}

	@PostMapping(value = "/update")
	public Stock save(@RequestBody @Validated Stock stock) {
		System.out.println("\n\n\n\n\nfall back method call..");
		return stockService.save(stock);

	}

	/*
	 * @GetMapping(value = "/updatestockbyid/{stockid}/{Quantity}") public
	 * ResponseEntity<APIResponse> getStockById(@PathVariable("stockid") Long id,
	 * 
	 * @PathVariable("Quantity") Long quantity) {
	 * 
	 * Stock stock = stockService.findByIdAndQuantity(id, quantity); return
	 * ResponseEntity.status(HttpStatus.OK).body(new
	 * APIResponse(HttpStatus.OK.value(), true,
	 * messages.getMessage("stock is available", null,
	 * LocaleContextHolder.getLocale()), stock)); }
	 */

	/*
	 * @PostMapping(value = "/update") public ResponseEntity<APIResponse>
	 * save(@RequestBody @Validated Stock stock) { stockService.save(stock); return
	 * ResponseEntity.status(HttpStatus.OK).body(new
	 * APIResponse(HttpStatus.OK.value(), true,
	 * messages.getMessage("stock update sucess fully", null,
	 * LocaleContextHolder.getLocale()), null)); }
	 */

}
