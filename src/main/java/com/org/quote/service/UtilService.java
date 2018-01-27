package com.org.quote.service;

import com.org.quote.exception.InvalidRequestAmountException;
import com.org.quote.model.Loan;

public interface UtilService {

	/**
	 * @param Validates
	 *            for Numeric value
	 * @return
	 */
	boolean isNumeric(String requestedAmountArg);

	/**
	 * @param validates
	 *            Requested Loan amount
	 * @return true/false
	 * @throws InvalidRequestAmountException
	 */
	boolean validateLoan(Loan loan) throws InvalidRequestAmountException;
}