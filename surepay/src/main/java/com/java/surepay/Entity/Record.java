package com.java.surepay.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.java.surepay.validators.TransactionValidator;
import com.java.surepay.validators.UniqueTransaction;
import com.sun.istack.NotNull;


@TransactionValidator(startBalance = "startBalance", mutation="mutation",  endBalance="endBalance",message = "end balance needs to be correct given the start balance and mutation")
@Entity
public class Record {

	@Id
	@NotNull
    @UniqueTransaction(message="Transaction reference already exists")
	private Long reference;
	
	@Column
	private String accountNumber;
	
	@Column
	private String description;
	
	@Column
	private double startBalance;
	
	@Column
	private double mutation;
	
	@Column
	private double endBalance;
	
	

	public Record() {
		
	}

	
	public Record(Long reference, String accountNumber, String description, double startBalance, double mutation,
			double endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}


	public Long getReference() {
		return reference;
	}


	public void setReference(Long reference) {
		this.reference = reference;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getStartBalance() {
		return startBalance;
	}


	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}


	public double getMutation() {
		return mutation;
	}


	public void setMutation(double mutation) {
		this.mutation = mutation;
	}


	public double getEndBalance() {
		return endBalance;
	}


	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	
	
}
