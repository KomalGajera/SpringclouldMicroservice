package com.example.orderservice.util;

public class ApplicationConstants {

	public enum OrderStatus {
		NEW, DONE, CANCELED
	}
	
	public enum PaymentStatus {
		BILLED, REFUNG
	}
	
	public enum DeliveryStatus {
		DELIVER, UNDELIVER
	}

}
