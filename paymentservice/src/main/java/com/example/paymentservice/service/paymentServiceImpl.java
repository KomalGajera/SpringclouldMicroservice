package com.example.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;

@Service
public class paymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repo;

	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		return repo.save(payment);
	}

	@Override
	public void deletePayment(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
