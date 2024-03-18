package com.need.api.needapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.FundingBasketFileDAO;
import com.need.api.needapi.persistence.NeedDAO;
import com.need.api.needapi.persistence.NeedFileDAO;
import com.need.api.needapi.persistence.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.need.api.needapi.model.FundingBasket;
import com.need.api.needapi.model.Need;
import com.need.api.needapi.model.User;

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
    private UserDAO mockUser;
    FundingBasket[] testFunding;


    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * @throws IOException 
     */
    @BeforeEach
    public void setupCheckoutController() throws IOException {
         mockNeedDAO = mock(NeedDAO.class);
        // mockBasket = mock(FundingBasketDAO.class);
        mockUser = mock(UserDAO.class);        
        CheckoutController = new CheckoutController(mockUser, mockNeedDAO);
        //NeedController = new NeedController(mockNeedDAO);
        // FundingBasketController = new FundingBasketController(mockBasket);
    }

    @Test
    public void testCheckout() throws IOException {  // getNeed may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>(1);
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        //array.add(new FundingBasket(70,"T-shirt", 10.99f, 3, "shirt"));
        ArrayList<Need> need = new ArrayList<Need>(1);
        need.add(new Need(72,"T-shirt", 10.99f, 10, "shirt"));

        User user0 = new User(0, "Jade", array);
        //mockUser.createUser(user0);
        //need?
        when (mockUser.getUser(0)).thenReturn(user0);
        //when (moc)
        //when (CheckoutController.checkout(0)).thenReturn(true);
        //assertEquals(true,array);
        boolean response = CheckoutController.checkout(user0.getId());
        assertEquals(true, response);
        // Analyze
        //getbasket returning null, nothing in it
        //need to fix, not supposed to return false
       
    
        
    }

    @Test
    public void testCheckoutNothing() throws IOException {  // getNeed may throw IOException
        // Setup

        boolean response = CheckoutController.checkout(0);

        // Analyze
        //getbasket returning null
        assertEquals(false, response);
        
    }

}
