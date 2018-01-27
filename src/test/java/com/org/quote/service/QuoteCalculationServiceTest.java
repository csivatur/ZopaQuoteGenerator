package com.org.quote.service;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.org.quote.model.Loan;
import com.org.quote.model.Offer;
import com.org.quote.service.impl.QuoteCalculationService;
import com.org.quote.service.impl.ZopaOfferService;

public class QuoteCalculationServiceTest {

    private CalculationService calculationService;

    @Before
    public void setUp() throws Exception {
        String filepath = getClass().getClassLoader().getResource("Market.csv").getPath();
        OfferService offerService = new ZopaOfferService(filepath);
        Loan loanRequest = new Loan("1000");
        List<Offer> offers = offerService.getLoanOffers(loanRequest);

        calculationService = new QuoteCalculationService(loanRequest, offers);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAverageRate() throws Exception {
        // Assert
        assertEquals("The rate should be 0.07", 0.07, calculationService.getAverageRate(), 0);
    }

    @Test
    public void getMonthlyPayment() throws Exception {
        // Assert
        assertEquals("The monthly payment should be 30.88", "30.88", String.format("%.2f", calculationService.getMonthlyPayment(),2));
    }

    @Test
    public void getTotalPayment() throws Exception {
        // Assert
        assertEquals("The total payment should be 1111.64", "1111.64",String.format("%.2f", calculationService.getTotalPayment(),2));
    }
}