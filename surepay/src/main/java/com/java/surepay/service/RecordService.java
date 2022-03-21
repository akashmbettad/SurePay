package com.java.surepay.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.surepay.Entity.FailedTransaction;
import com.java.surepay.Entity.Record;
import com.java.surepay.exception.NoTransactionReferenceFoundException;
import com.java.surepay.repository.RecordRepository;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repo;
	
	public Optional<Record> fetchReferenceDetails(Long reference) {
		
		Optional<Record> transactionRecord=repo.findByreference(reference);
		
		if(!transactionRecord.isPresent()) {
			
			throw new NoTransactionReferenceFoundException("Not transaction records have been found");
		}
		
		return transactionRecord;
	}
	
	public Record createTransaction(Record record) {
			
		Record transRecord=repo.save(record);
		return transRecord;
	}
	

	public void createFailedJsonReport(Record transaction, BindingResult errors)
			throws IOException, StreamWriteException, DatabindException {
		FailedTransaction failedTransaction = new FailedTransaction();
		ObjectMapper mapper = new ObjectMapper();;
		File file=new File("failedTransaction.json");

		if(errors.getErrorCount()==2) {
			failedTransaction.setReference(transaction.getReference());
			failedTransaction.setErrorMessage("This transaction failed as the transaction reference already exists and also end balance needs to be correct given the start balance and mutation");
		}

		else if((errors.getErrorCount()==1)&&errors.getFieldError("reference") != null) {
			failedTransaction.setReference(transaction.getReference());
			failedTransaction.setErrorMessage("This transaction failed as the transaction reference already exists");
		}
		else {
			failedTransaction.setReference(transaction.getReference());
			failedTransaction.setErrorMessage("This transaction failed as end balance needs to be correct given the start balance and mutation");
		}


		mapper.writeValue(file, failedTransaction);
	}
	
}
