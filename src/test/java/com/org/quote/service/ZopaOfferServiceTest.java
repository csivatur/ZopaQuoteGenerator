package com.org.quote.service;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.org.quote.exception.InsufficientOfferException;
import com.org.quote.model.Loan;
import com.org.quote.model.Offer;
import com.org.quote.service.impl.ZopaOfferService;

public class ZopaOfferServiceTest {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private OfferService offerService;

    @Before
    public void setUp() {
        String filepath = classLoader.getResource("Market.csv").getPath();
        offerService = new ZopaOfferService(filepath);
    }

    @Test
    public void getAvailableOffers_sufficient_available_amount() throws IOException {
        // Arrange
        List<Offer> offers = offerService.getAvailableOffers();

        // Assert
        assertEquals("Confirm that 7 offers have been loaded", 7, offers.size());
    }

    @Test
    public void getAvailableOffers_sorted_first() throws IOException {
        // Arrange
        List<Offer> offers = offerService.getAvailableOffers();
        assertEquals("The first after sort should be Jane", "Jane", offers.get(0).getLender());
    }

    @Test
    public void getAvailableOffers_sorted_last() throws IOException {
        // Arrange
        List<Offer> offers = offerService.getAvailableOffers();

        // Assert
        assertEquals("The last after sort should be Mary", "Mary", offers.get(6).getLender());
    }

    @Test(expected = InsufficientOfferException.class)
    public void getOffers_not_sufficient_available_amount() throws IOException {
        // Arrange
        Loan loanRequest = new Loan("5000");

        // Assert
        List<Offer> offers = offerService.getLoanOffers(loanRequest);
    }

    @Test
    public void getOffers_applicable_to_loan_amount_1000() throws IOException {
        // Arrange
        Loan loanRequest = new Loan("1000");

        // Action
        List<Offer> offers = offerService.getLoanOffers(loanRequest);
        BigDecimal sumNeededAmount = offers.stream().map(t -> t.getNeededAmount()).reduce(BigDecimal.valueOf(0d), (a, b) -> a.add(b)).setScale(2, RoundingMode.CEILING);

        // Asserts
        assertEquals("2 offers are applicable to 1000 request", 2, offers.size());
        assertEquals("Needed amount sum must not exceed loan request amount", loanRequest.getLoanAmount().setScale(2, RoundingMode.CEILING), sumNeededAmount);
    }

    @Test
    public void getOffers_applicable_to_loan_amount_1200() throws IOException {
        // Arrange
        Loan loanRequest = new Loan("1200");

        // Action
        List<Offer> offers = offerService.getLoanOffers(loanRequest);
        BigDecimal sumNeededAmount = offers.stream().map(t -> t.getNeededAmount()).reduce(BigDecimal.valueOf(0d), (a, b) -> a.add(b)).setScale(2, RoundingMode.CEILING);

        // Assert
        assertEquals("4 offers are applicable to 1200 request", 4, offers.size());
        assertEquals("Needed amount sum must not exceed loan request amount", loanRequest.getLoanAmount().setScale(2, RoundingMode.CEILING), sumNeededAmount);
    }

    @Test
    public void getOffers_applicable_to_loan_amount_1500() throws IOException {
        // Arrange
        Loan loanRequest = new Loan("1500");

        // Action
        List<Offer> offers = offerService.getLoanOffers(loanRequest);
        BigDecimal sumNeededAmount = offers.stream().map(t -> t.getNeededAmount()).reduce(BigDecimal.valueOf(0d), (a, b) -> a.add(b)).setScale(2, RoundingMode.CEILING);

        // Assert
        assertEquals("5 offers are applicable to 1500 request", 5, offers.size());
        assertEquals("Needed amount sum must not exceed loan request amount", loanRequest.getLoanAmount().setScale(2, RoundingMode.CEILING), sumNeededAmount);
        assertEquals("Needed amount should be 300", BigDecimal.valueOf(300), offers.get(4).getNeededAmount());
    }
}