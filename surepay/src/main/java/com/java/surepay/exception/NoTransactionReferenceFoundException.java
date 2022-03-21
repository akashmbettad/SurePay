package com.java.surepay.exception;

public class NoTransactionReferenceFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;

	public NoTransactionReferenceFoundException(String msg) {

		this.msg = msg;
	}
	

}
