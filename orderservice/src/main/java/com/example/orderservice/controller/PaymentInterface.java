package com.example.orderservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.orderservice.model.Payment;

//, configuration = FallBackConfig.class, fallback = PaymentFallback.class
@FeignClient(name = "payment-service", url = "http://localhost:8082")
@RequestMapping("/payment")
public interface PaymentInterface {

	@PostMapping(value = "/updatepayment")
	public Payment updatePaymentDetail(@RequestBody Payment payment);

	@DeleteMapping(value = "/deletepayment")
	public void deletePayment(@RequestBody Long id);

}
