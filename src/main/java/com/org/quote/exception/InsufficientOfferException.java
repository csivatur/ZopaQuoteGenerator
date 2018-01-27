package com.org.quote.exception;

public class InsufficientOfferException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 615726702220792445L;

	/**
	 * @return
	 */
	@Override
	public String getMessage() {
		return "It is not possible to provide a quote at this time - Insufficient offers from lenders";
	}
}