package com.org.quote.service;

import java.math.BigDecimal;

public interface CalculationService {
	/**
	 * @return Returns average rate
	 */
	double getAverageRate();

	/**
	 * @return Returns monthly payment
	 */
	double getMonthlyPayment();

	/**
	 * @return Returns Total payment
	 */
	double getTotalPayment();
}
