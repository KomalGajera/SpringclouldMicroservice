package com.example.orderservice.service;

import com.example.orderservice.model.Order;

public interface OrderService {

	Order save(Order finalOrder);

	void delete(Order orderDetail);

}
