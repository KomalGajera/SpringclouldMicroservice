package com.example.deliveryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliveryservice.model.Delivery;
import com.example.deliveryservice.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;

	@PostMapping(value = "/savedelivery")
	public Delivery savedelivery(@RequestBody Delivery delivery) {
		try {
			System.out.println(10/0);
			return deliveryService.save(delivery);
		} catch (Exception e) {
			return null;
		}

	}

}
