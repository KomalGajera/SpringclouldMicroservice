package com.example.deliveryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliveryservice.model.Delivery;
import com.example.deliveryservice.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	DeliveryRepository repo;

	@Override
	public Delivery save(Delivery delivery) {
		// TODO Auto-generated method stub
		return repo.save(delivery);
	}

}
