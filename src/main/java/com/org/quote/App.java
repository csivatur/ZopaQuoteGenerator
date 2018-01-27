package com.org.quote;

import java.io.IOException;
import java.util.List;

import com.org.quote.exception.InsufficientOfferException;
import com.org.quote.exception.InvalidRequestAmountException;
import com.org.quote.model.Loan;
import com.org.quote.model.Offer;
import com.org.quote.service.CalculationService;
import com.org.quote.service.OfferService;
import com.org.quote.service.UtilService;
import com.org.quote.service.impl.LoanUtilService;
import com.org.quote.service.impl.QuoteCalculationService;
import com.org.quote.service.impl.ZopaOfferService;

/**
 * Hello world!
 *
 */
public class App {

	// Ideally the following services should be injected
	private static OfferService offerService;
	private static UtilService utilService = new LoanUtilService();
	private static CalculationService calculationService;

	/**
	 * @param args
	 *            supplied file path and loan amount
	 * @throws IOException
	 *             and InvalidRequestAmountException
	 */
	public static void main(String[] args) {

		validate(args);

		try {
			Loan loan = new Loan(args[1]);

			// Step1 - Validate the Requested Loan Amount
			utilService.validateLoan(loan);

			offerService = new ZopaOfferService(args[0]);

			// Step2 - Parse CSV and get all Loan Offers
			List<Offer> offers = offerService.getLoanOffers(loan);

			calculationService = new QuoteCalculationService(loan, offers);

			// Step3 - Print the results
			System.out.println("Request Amount: £"
					+ String.format("%.0f", loan.getLoanAmount()));
			System.out.println("Rate: "
					+ String.format("%.1f",
							calculationService.getAverageRate() * 100) + "%");
			System.out.println("Monthly repayment £"
					+ String.format("%.2f",
							calculationService.getMonthlyPayment()));
			System.out.println("Total repayment: £"
					+ String.format("%.2f",
							calculationService.getTotalPayment()));
		} catch (IOException e) {
			System.out.println("IO Exception - " + e.getMessage());
			System.exit(1);
		} catch (InvalidRequestAmountException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientOfferException e) {
			System.out.println(e.getMessage());
		}

		System.exit(0);
	}

	/*
	 * Validates the command line arguments
	 */
	private static void validate(String[] args) {
		if (args.length < 2) {
			System.err.println("Insufficient parameters supplied");
			System.exit(1);
		}

		if (!utilService.isNumeric(args[1])) {
			System.err.println("Supplied Loan amount should be Numeric value");
			System.exit(1);
		}
	}
}
