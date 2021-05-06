package com.example.orderservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.orderservice.model.Delivery;


@FeignClient(name="delivery-service",url = "http://localhost:8084")
@RequestMapping("/delivery")
public interface DeliveryInterface {
	
	@PostMapping(value = "/savedelivery")
	public Delivery savedelivery(@RequestBody Delivery delivery);

}
