package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;

public interface PaymentService {

	Payment save(Payment payment);

	void deletePayment(Long id);

}
