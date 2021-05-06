package com.example.orderservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.orderservice.util.ApplicationConstants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetail")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "ordervalue")
	private BigDecimal value;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
