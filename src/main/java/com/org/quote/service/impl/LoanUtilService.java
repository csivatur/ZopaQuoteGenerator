package com.org.quote.service.impl;

import static com.org.quote.constants.AppConstants.INCREMENT_AMOUNT;
import static com.org.quote.constants.AppConstants.LOWER_RANGE;
import static com.org.quote.constants.AppConstants.UPPER_RANGE;

import java.math.BigDecimal;

import com.org.quote.exception.InvalidRequestAmountException;
import com.org.quote.model.Loan;
import com.org.quote.service.UtilService;

public class LoanUtilService implements UtilService {

	/**
	 * @param Validates
	 *            for Numeric value
	 * @return
	 * @throws InvalidRequestAmountException
	 */
	@Override
	public boolean isNumeric(String requestedAmountArg) {
		return requestedAmountArg != null
				&& requestedAmountArg.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * @param validates
	 *            Requested Loan amount
	 * @return
	 * @throws InvalidRequestAmountException
	 */
	@Override
	public boolean validateLoan(Loan loan) throws InvalidRequestAmountException {

		if (loan.getLoanAmount().compareTo(LOWER_RANGE) < 0
				|| loan.getLoanAmount().compareTo(UPPER_RANGE) > 0) {
			throw new InvalidRequestAmountException(
					"The requested amount should be between the range of 1000 and 15000");
		}

		BigDecimal remainder = loan.getLoanAmount().remainder(INCREMENT_AMOUNT);

		if (remainder.intValueExact() > 0)
			throw new InvalidRequestAmountException(
					"The requested amount is not incremental of 100");

		return true;
	}
}
