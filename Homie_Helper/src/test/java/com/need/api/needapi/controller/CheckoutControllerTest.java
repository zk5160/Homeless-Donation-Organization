package com.need.api.needapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.File;
import java.io.IOException;
import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.FundingBasketFileDAO;
import com.need.api.needapi.persistence.NeedDAO;
import com.need.api.needapi.persistence.NeedFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.need.api.needapi.model.FundingBasket;
import com.need.api.needapi.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Checkout Controller class
 * 
 * @author Jade Kimmel
 */
@Tag("Controller-tier")
public class CheckoutControllerTest {
    
    private CheckoutController CheckoutController;
    private FundingBasketDAO mockBasket;
    private NeedDAO mockNeedDAO;
    private NeedController NeedController;
    private FundingBasketController FundingBasketController;
    private FundingBasketFileDAO f;
    FundingBasket[] testFunding;


    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * @throws IOException 
     */
    @BeforeEach
    public void setupCheckoutController() throws IOException {
        mockNeedDAO = mock(NeedDAO.class);
        mockBasket = mock(FundingBasketDAO.class);
        CheckoutController = new CheckoutController(mockBasket);
        NeedController = new NeedController(mockNeedDAO);
        FundingBasketController = new FundingBasketController(mockBasket);
    }

    @Test
    public void testCheckout() throws IOException {  // getNeed may throw IOException
        // Setup
        boolean response = CheckoutController.checkout();
        // Analyze
        //getbasket returning null, nothing in it
        //need to fix, not supposed to return false
        assertEquals(true, response);
    
        
    }

    @Test
    public void testCheckoutNothing() throws IOException {  // getNeed may throw IOException
        // Setup

        boolean response = CheckoutController.checkout();;

        // Analyze
        //getbasket returning null
        assertEquals(false, response);
        
    }

}
