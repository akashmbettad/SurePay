package com.java.surepay.Entity;

public class FailedTransaction {

	private Long reference;
	
	private String errorMessage;
	

	public FailedTransaction() {

	}

	public FailedTransaction(Long reference, String errorMessage) {
		super();
		this.reference = reference;
		this.errorMessage = errorMessage;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
