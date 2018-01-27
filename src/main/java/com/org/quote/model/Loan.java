package com.org.quote.model;

import java.math.BigDecimal;

public class Loan {

	private final BigDecimal loanAmount;

	/**
	 * @param requestedAmount
	 */
	public Loan(String loanAmount) {
		this.loanAmount = BigDecimal.valueOf(Long.parseLong(loanAmount));
	}

	/**
	 * @return Requested Amount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
}
