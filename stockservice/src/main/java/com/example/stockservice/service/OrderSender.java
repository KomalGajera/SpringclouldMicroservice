package com.example.stockservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {
	
	@Autowired 
	private Source source;

	public boolean send(Boolean result) {
	    return this.source.output().send(MessageBuilder.withPayload(result).build());
	  }
}
