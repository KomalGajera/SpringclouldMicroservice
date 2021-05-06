package com.example.orderservice.model;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.orderservice.util.ApplicationConstants.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	
	private Long id;

	
	private Long orderId;

	
	private BigDecimal valueBilled;

	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

}
