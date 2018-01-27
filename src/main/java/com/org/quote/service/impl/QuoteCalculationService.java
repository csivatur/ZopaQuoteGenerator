package com.org.quote.service.impl;

import static com.org.quote.constants.AppConstants.TERM_MONTHS;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.org.quote.model.Loan;
import com.org.quote.model.Offer;
import com.org.quote.service.CalculationService;

public class QuoteCalculationService implements CalculationService {

	private final Loan loan;
	private final List<Offer> offers;

	/**
	 * @param loan
	 * @param offers
	 */
	public QuoteCalculationService(Loan loan, List<Offer> offers) {

		this.loan = loan;
		this.offers = offers;
	}

	/**
	 * @return Returns average rate
	 */
	@Override
	public double getAverageRate() {
		double averageRate = this.offers.stream().mapToDouble(t -> t.getRate())
				.average().getAsDouble();

		// set to 1 decimal places
		return Math.round(averageRate * 1000.0) / 1000.0;
	}

	/**
	 * @return Returns monthly payment
	 */
	@Override
	public double getMonthlyPayment() {
		MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
		this.offers.forEach(offer -> {
			// monthly offer rate
				double offerMonthlyRate = offer.getRate() / 12;
				// Compound interest calculation
				BigDecimal offerMonthlyPayment = offer.getNeededAmount()
						.multiply(BigDecimal.valueOf(offerMonthlyRate));
				offerMonthlyPayment = BigDecimal.valueOf(offerMonthlyPayment
						.doubleValue()
						/ (1 - Math.pow(1 + offerMonthlyRate, -TERM_MONTHS)));

				offer.setMonthlyPayment(offerMonthlyPayment);
			});

		// sum monthly payment for all offers to get total
		BigDecimal totalMonthly = BigDecimal.valueOf(0);
		for (Offer offer : this.offers) {
			totalMonthly = totalMonthly.add(offer.getMonthlyPayment());
		}

		return totalMonthly.doubleValue();
	}

	/**
	 * @return Returns Total payment
	 */
	@Override
	public double getTotalPayment() {
		return this.getMonthlyPayment() * TERM_MONTHS;
	}
}
