package com.org.quote.model;

import java.math.BigDecimal;

public class Offer implements Comparable<Offer> {

	private final String lender;
	private final double rate;
	private final BigDecimal availableAmount;
	private BigDecimal neededAmount;
	private BigDecimal monthlyPayment;

	/**
	 * @param lender
	 * @param rate
	 * @param availableAmount
	 */
	public Offer(String lender, double rate, BigDecimal availableAmount) {
		this.lender = lender;
		this.rate = rate;
		this.availableAmount = availableAmount;
	}

	public Offer() {

		lender = null;
		rate = 0;
		availableAmount = BigDecimal.valueOf(0);
	}

	@Override
	public String toString() {
		return this.getLender() + ' ' + this.getRate() + ' '
				+ this.getAvailableAmount();
	}

	/**
	 * @return Lender
	 */
	public String getLender() {
		return lender;
	}

	/**
	 * @return Loan rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @return Available amount
	 */
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}

	@Override
	public int compareTo(Offer o) {
		if (this.getRate() == o.getRate())
			return 0;
		else
			return this.getRate() > o.getRate() ? 1 : -1;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		Offer objOffer = (Offer) obj;

		return this.getRate() == objOffer.getRate()
				&& this.getAvailableAmount() == objOffer.getAvailableAmount()
				&& this.getLender() == objOffer.getLender();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @param neededAmount
	 */
	public void setNeededAmount(BigDecimal neededAmount) {
		this.neededAmount = neededAmount;
	}

	/**
	 * @return the needed amount
	 */
	public BigDecimal getNeededAmount() {
		return neededAmount;
	}

	/**
	 * @param monthlyPayment
	 *            set the monthly payment
	 */
	public void setMonthlyPayment(BigDecimal monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	/**
	 * @return returns the monthly payment
	 */
	public BigDecimal getMonthlyPayment() {
		return monthlyPayment;
	}
}
