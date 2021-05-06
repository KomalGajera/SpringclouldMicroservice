package com.example.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.orderservice.model.Stock;

@Service
public class OrderSender {
	
	@Autowired 
	private Source source;

	public boolean send(Stock stock) {
	    return this.source.output().send(MessageBuilder.withPayload(stock).build());
	  }
}
