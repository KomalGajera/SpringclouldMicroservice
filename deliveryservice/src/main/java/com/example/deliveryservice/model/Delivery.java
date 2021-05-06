package com.example.deliveryservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.deliveryservice.util.ApplicationConstants.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
}
