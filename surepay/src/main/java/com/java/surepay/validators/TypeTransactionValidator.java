package com.java.surepay.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.surepay.repository.RecordRepository;

public class TypeTransactionValidator implements ConstraintValidator<TransactionValidator, Object> {


	@Autowired
	RecordRepository repo;

	private String startBalanceFieldName;
	private String mutationFieldName;
	private String endBalanceFieldName;


	public void initialize(TransactionValidator constraintAnnotation) {    
		
		startBalanceFieldName=constraintAnnotation.startBalance();
		mutationFieldName=constraintAnnotation.mutation();
		endBalanceFieldName=constraintAnnotation.endBalance();

	}
	
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		try {
			Field startBalanceField=value.getClass().getDeclaredField(startBalanceFieldName);
			startBalanceField.setAccessible(true);
			
			Field mutationField=value.getClass().getDeclaredField(mutationFieldName);
			mutationField.setAccessible(true);
			
			Field endBalanceField=value.getClass().getDeclaredField(endBalanceFieldName);
			endBalanceField.setAccessible(true);
			
			
			double startBalance=(Double) startBalanceField.get(value);
			double mutationBalance=(Double) mutationField.get(value);
			double endBalance=(Double) endBalanceField.get(value);
			
			return endBalance==(startBalance+(mutationBalance));
			
		} catch (Exception e) {
		      e.printStackTrace();
		      
		      return false;
		    }
		
	}

	//	public boolean isValid(Record transaction, ConstraintValidatorContext context) {
	//		
	//		boolean flag=false;
	//		Optional<Record> record= repo.findByreference(transaction.getReference());
	//		
	//		double startBalance=transaction.getStartBalance();
	//		double mutation=transaction.getMutation();
	//		double endBalance=transaction.getEndBalance();
	//		
	//		double checkEndBalance=startBalance+mutation;
	//		
	//		if((!record.isPresent())&&(endBalance==checkEndBalance)) {
	//			
	//			flag=true;
	//		}
	//		else {
	//			flag=false;
	//		}
	//		
	//		
	//		return flag;
	//	}

}
