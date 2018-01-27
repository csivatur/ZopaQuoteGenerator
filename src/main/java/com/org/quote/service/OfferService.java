package com.org.quote.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.org.quote.exception.InsufficientOfferException;
import com.org.quote.model.Loan;
import com.org.quote.model.Offer;

public interface OfferService {

	/**
	 * @return List of offers
	 */
	default List<Offer> getLoanOffers(Loan loan)
			throws InsufficientOfferException, IOException {
		return new ArrayList<>();
	}

	default List<Offer> getAvailableOffers() throws InsufficientOfferException,
			IOException {
		return new ArrayList<>();
	}
}
