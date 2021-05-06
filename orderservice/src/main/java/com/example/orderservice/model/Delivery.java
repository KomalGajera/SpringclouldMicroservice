package com.example.orderservice.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.orderservice.util.ApplicationConstants.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

	private Long id;

	private Long stockId;

	private Long orderId;
	
	private Long paymentId;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
}
