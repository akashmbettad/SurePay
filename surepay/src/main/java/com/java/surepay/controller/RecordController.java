package com.java.surepay.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.java.surepay.Entity.Record;
import com.java.surepay.exception.InvalidRequestException;
import com.java.surepay.service.RecordService;


@RestController
public class RecordController {
	
	Logger logger=LoggerFactory.getLogger(RecordController.class);

	@Autowired
	private RecordService service;


	@PostMapping("/createTransaction")
	public ResponseEntity<Record> createTransaction(@Valid @RequestBody Record transaction, BindingResult errors) throws NoSuchFieldException, SecurityException, StreamWriteException, DatabindException, IOException{

		logger.info("Transaction has validation errors");
		// Check if transaction reference is unique
		if(errors.hasErrors()){

			logger.info("Creating a file/report with failed transaction and their details");
			service.createFailedJsonReport(transaction, errors);

			throw new InvalidRequestException("Error with transaction", errors);
		}

		else {
			logger.info("Transaction has no validation errors- Saving transaction details to database");
			Record recordTransaction = service.createTransaction(transaction);
			return new ResponseEntity<Record>(recordTransaction, HttpStatus.CREATED);
		}

	}

	@GetMapping("/getDetails/{referenceId}")
	public ResponseEntity<Optional<Record>> getTransactionDetails(@PathVariable("referenceId") Long referenceId){

		logger.info("Fetching transaction details");
		Optional<Record> transactionRecord = service.fetchReferenceDetails(referenceId);
		return new ResponseEntity<Optional<Record>>(transactionRecord,HttpStatus.OK);
	}
}
