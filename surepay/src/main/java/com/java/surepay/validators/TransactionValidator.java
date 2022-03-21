package com.java.surepay.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { TypeTransactionValidator.class })
public @interface TransactionValidator {

	String message() default "{com.java.surepay.validators.message}";
	  Class <?> [] groups() default {};
	  Class <? extends Payload> [] payload() default {};
	  
	  String startBalance();
	  String mutation();
	  String endBalance();
}
