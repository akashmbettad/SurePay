package com.java.surepay.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Exception Handle, handle the exception
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<?> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidRequestException ire = (InvalidRequestException) e;

		List<ObjectError> errors=ire.getErrors().getAllErrors();

		List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();

		for (ObjectError fieldError : errors) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setField( fieldError.getObjectName());
			fieldErrorResource.setResource(fieldError.getObjectName());
			fieldErrorResource.setCode(fieldError.getCode());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}
		ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
		error.setFieldErrors(fieldErrorResources);

		return new ResponseEntity<ErrorResource>(error,HttpStatus.UNPROCESSABLE_ENTITY);
	}


	@ExceptionHandler({ NoTransactionReferenceFoundException.class })
	protected ResponseEntity<ErrorResource> handleNoTransactionReferenceFoundException(RuntimeException e, WebRequest request) {
		NoTransactionReferenceFoundException ire = (NoTransactionReferenceFoundException) e;
	
		ErrorResource error = new ErrorResource("No transaction details found for this reference", ire.getMessage());
		return new ResponseEntity<ErrorResource>(error,HttpStatus.NOT_FOUND);
	}
}
