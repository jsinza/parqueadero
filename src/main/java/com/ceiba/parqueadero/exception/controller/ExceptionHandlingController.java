package com.ceiba.parqueadero.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ceiba.parqueadero.exception.ParqueaderoException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ParqueaderoException.class)
	public ResponseEntity<Object> resourceNotFound(RuntimeException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
	}
	
}
