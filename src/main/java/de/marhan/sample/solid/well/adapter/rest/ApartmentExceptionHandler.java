package de.marhan.sample.solid.well.adapter.rest;

import de.marhan.sample.solid.well.domain.WellApartmentNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApartmentExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({WellApartmentNotFoundException.class})
	protected ResponseEntity<Object> handleApartmentNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, "Apartment not found, please try later!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}