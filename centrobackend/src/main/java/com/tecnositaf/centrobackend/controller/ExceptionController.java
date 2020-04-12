package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.response.Response;

@RestControllerAdvice
public class ExceptionController {

	final  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(FailureException.class)
	public ResponseEntity<Response> handleFailureException(FailureException exception)  {
		logger.error("ERROR", exception.toString());
		HttpStatus httpStatus = exception.getHttpStatus();
		ResponseErrorEnum responseErrorEnum = exception.getResponseErrorEnum();
		return ResponseEntity.status(httpStatus).body(
			new Response(responseErrorEnum)
		);
	}

	/*** CASO POZZO (sempre presente) ***/ 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception exception)  {
		logger.error("UNEXPECTED ERROR", exception.toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			new Response(ResponseErrorEnum.ERR_500)
		);
	}
}
