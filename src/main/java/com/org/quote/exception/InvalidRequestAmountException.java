package com.org.quote.exception;

public class InvalidRequestAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -681166534006612489L;
	private final String message;

	/**
	 * @param message
	 */
	public InvalidRequestAmountException(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
