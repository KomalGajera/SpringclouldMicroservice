package com.example.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repo;

	@Override
	public Order save(Order finalOrder) {
		// TODO Auto-generated method stub
		Order order=repo.save(finalOrder);
		return order;
	}

	@Override
	public void delete(Order orderDetail) {
		// TODO Auto-generated method stub
		repo.delete(orderDetail);
	}

}
