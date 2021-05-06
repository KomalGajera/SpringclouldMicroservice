package com.example.deliveryservice.exception;

import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.deliveryservice.dto.APIResponse;

@ControllerAdvice
public class RestResponseEntityException extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource messages;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String data = ex.getBindingResult().getAllErrors().stream().map(
				error -> this.messages.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale()))
				.collect(Collectors.joining(","));

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(status.value(), false, data));
	}

	@ExceptionHandler(value = { DeliveryException.class })
	protected ResponseEntity<APIResponse> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.ok(new APIResponse(HttpStatus.NOT_FOUND.value(), false,
				this.messages.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = {})
	protected ResponseEntity<APIResponse> handleBadRequest(RuntimeException ex, WebRequest request) {
		return ResponseEntity.ok(new APIResponse(HttpStatus.BAD_REQUEST.value(), false,
				this.messages.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	protected ResponseEntity<APIResponse> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
		String msg = "";
		if (null != ex.getMessage() && !"".equals(ex.getMessage())) {
			if (ex.getMessage().contains(":")) {
				if (ex.getMessage().contains("{") && ex.getMessage().contains("}")) {
					msg = ex.getMessage()
							.substring(ex.getMessage().trim().indexOf('{') + 1, ex.getMessage().trim().lastIndexOf('}'))
							.trim();
				} else {
					msg = ex.getMessage().substring(ex.getMessage().indexOf(':') + 1).trim();
				}
			} else {
				msg = ex.getMessage();
			}
		}
		return ResponseEntity.ok(new APIResponse(HttpStatus.BAD_REQUEST.value(), false,
				this.messages.getMessage(msg, null, LocaleContextHolder.getLocale())));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return ResponseEntity
				.ok(new APIResponse(HttpStatus.BAD_REQUEST.value(), false, ex.getParameterName() + " is required"));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(new APIResponse(HttpStatus.BAD_REQUEST.value(), false,
				this.messages.getMessage("invalid.input.global.msg", null, LocaleContextHolder.getLocale())));
	}
}
