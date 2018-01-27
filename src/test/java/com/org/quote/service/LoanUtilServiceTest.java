package com.org.quote.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.org.quote.exception.InvalidRequestAmountException;
import com.org.quote.model.Loan;
import com.org.quote.service.impl.LoanUtilService;

public class LoanUtilServiceTest {

    UtilService validationService = new LoanUtilService();

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_out_of_range_below() throws InvalidRequestAmountException {
        Loan loan = new Loan("980");
        validationService.validateLoan(loan);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_out_of_range_above() throws InvalidRequestAmountException {
        Loan loan = new Loan("1666");
        validationService.validateLoan(loan);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_not_incremental() throws InvalidRequestAmountException {
        Loan loan = new Loan("1150");
        validationService.validateLoan(loan);
    }

    @Test
    public void validate_requested_amount_isValid() throws InvalidRequestAmountException {
        Loan loan = new Loan("1100");
        boolean valid = validationService.validateLoan(loan);
        assertTrue("Requested amount is valid", valid);
    }
}