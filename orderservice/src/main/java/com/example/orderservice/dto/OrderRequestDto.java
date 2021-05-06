package com.example.orderservice.dto;

import java.math.BigDecimal;

import com.example.orderservice.util.ApplicationConstants.OrderStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {
	
	Long stockId;
	Long Quantity;
	BigDecimal value;
	OrderStatus status;
}
