package com.example.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;

@RestController
@Validated
@Transactional
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	MessageSource messages;

	@PostMapping(value = "/updatepayment")
	public Payment updatePaymentDetail(@RequestBody Payment payment) {

		try {
			return paymentService.save(payment);
		} catch (Exception e) {
			return null;
		}

	}

	@DeleteMapping(value = "/deletepayment")
	public void deletePayment(@RequestBody Long id) {
		paymentService.deletePayment(id);
	}

	/*
	 * @GetMapping(value = "/updatepayment/{orderid}/{amount}") public
	 * ResponseEntity<APIResponse> updatePaymentDetail(@PathVariable("orderid") Long
	 * id,
	 * 
	 * @PathVariable("amount") BigDecimal amount) { Payment payment = new
	 * Payment(null, id, amount, PaymentStatus.BILLED);
	 * 
	 * Payment finalPayment = paymentService.save(payment);
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(new
	 * APIResponse(HttpStatus.OK.value(), true,
	 * messages.getMessage("payment save...", null,
	 * LocaleContextHolder.getLocale()), finalPayment)); }
	 */

}
