package com.example.orderservice.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.dto.APIResponse;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.model.Delivery;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.Payment;
import com.example.orderservice.model.Stock;
import com.example.orderservice.service.OrderSender;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.util.ApplicationConstants.DeliveryStatus;
import com.example.orderservice.util.ApplicationConstants.OrderStatus;
import com.example.orderservice.util.ApplicationConstants.PaymentStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Validated
@Transactional
@Slf4j
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StockInterface stockImpl;

	@Autowired
	private DeliveryInterface deliveryImpl;

	@Autowired
	PaymentInterface paymentImplement;

	@Autowired
	OrderSender orderSender;

	@Autowired
	ObjectMapper mapper;

	boolean result = false;

	@StreamListener(target = Processor.INPUT)
	public void receiveOrder(boolean result) throws JsonProcessingException {
		this.result = result;
	}

	/*
	 * This is message bus approach...
	 */
	@PostMapping(value = "/test")
	public String process(@RequestBody Stock stock) throws JsonProcessingException, InterruptedException {
		String message;
		try {
			Boolean isSent = orderSender.send(stock);
			if (isSent) {
				log.info(stock.toString());
				Thread.sleep(10);
				boolean finalresult = result;
				if (finalresult) {
					message = "All good";
				} else {
					throw new Exception("down service");
				}
			}else {
				message="error while calling another micro-service";
			}			
		} catch (Exception e) {
			// TODO: handle exception
			message = e.getMessage();
		}
		return message;

	}

	/*
	 * This is message bus open feign approach...
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<APIResponse> saveOrder(@Valid @RequestBody OrderRequestDto order) {

		Stock oldstock = new Stock();
		Stock finalstock = new Stock();
		Payment payment = null;
		Order orderDetail;
		String message;

		Boolean isStockAvailable = stockImpl.checkStockById(order.getStockId(), order.getQuantity());

		if (isStockAvailable) {
			Order finalOrder = modelMapper.map(order, Order.class);
			finalOrder.setStatus(OrderStatus.NEW);
			orderDetail = orderService.save(finalOrder);
			oldstock = stockImpl.getStockById(orderDetail.getStockId());
			try {

				finalstock = stockImpl.updateStock(new Stock(orderDetail.getStockId(), orderDetail.getQuantity()));
				payment = paymentImplement.updatePaymentDetail(
						new Payment(null, orderDetail.getId(), orderDetail.getValue(), PaymentStatus.BILLED));
				if (payment == null)
					throw new Exception("There is some error while doing payment..");
				Delivery delivery = deliveryImpl.savedelivery(new Delivery(null, finalstock.getId(),
						orderDetail.getId(), payment.getId(), DeliveryStatus.DELIVER));
				if (delivery == null)
					throw new Exception("There is some error while deliver the order...");
				message = "order save successfully";
			} catch (Exception e) {

				Long id = (payment == null) ? 0l : payment.getId();
				stockImpl.save(oldstock);
				paymentImplement.deletePayment(id);
				orderService.delete(orderDetail);
				message = e.getMessage();
			}

		} else {
			message = "out of stock";
		}
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(HttpStatus.OK.value(), true,
				messages.getMessage(message, null, LocaleContextHolder.getLocale()), null));
	}

}
