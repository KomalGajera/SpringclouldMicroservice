package com.example.deliveryservice.exception;

public class DeliveryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeliveryException() {
		super();
	}

	public DeliveryException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeliveryException(String message) {
		super(message);
	}

	public DeliveryException(Throwable cause) {
		super(cause);
	}

}
