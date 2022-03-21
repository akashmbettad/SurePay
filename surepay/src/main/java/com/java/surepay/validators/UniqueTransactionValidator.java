package com.java.surepay.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.surepay.Entity.Record;
import com.java.surepay.repository.RecordRepository;

/**
 * Validator of unique transaction
 */
public class UniqueTransactionValidator implements ConstraintValidator<UniqueTransaction, Long> {

	@Autowired
	private RecordRepository repo;

	public void initialize(UniqueTransaction constraintAnnotation) {

	}

	public boolean isValid(Long reference, ConstraintValidatorContext context) {

		// Check if the transaction is unique
		Boolean flag=false;
		Optional<Record> rec=repo.findByreference(reference);
		if(rec.isPresent()) {
			flag=false;
		}
		else {
			flag=true;
		}
		return flag;
		
	}
}
