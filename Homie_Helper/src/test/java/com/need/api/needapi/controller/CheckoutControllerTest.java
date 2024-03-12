package com.need.api.needapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.NeedDAO;
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

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupCheckoutController() {
        mockNeedDAO = mock(NeedDAO.class);
        mockBasket = mock(FundingBasketDAO.class);
        CheckoutController = new CheckoutController(mockBasket);
        NeedController = new NeedController(mockNeedDAO);
        FundingBasketController = new FundingBasketController(mockBasket);
    }

    @Test
    public void testCheckout() throws IOException {  // getNeed may throw IOException
        // Setup
        //not used??
        Need need = new Need(99,"T-shirt", 10.99f, 10, "shirt");
        FundingBasket fundingBasket = new FundingBasket (99,"T-shirt", 10.99f, 3, "shirt");
        // need to fix the when  

        //stuff created
        when(mockNeedDAO.createNeed(need)).thenReturn(need);
        when(mockBasket.createFundingBasket(fundingBasket)).thenReturn(fundingBasket);

        //doing checkout
        //when(CheckoutController.checkout()).thenReturn(true);
        //when(mockBasket.getFundingBasket(fundingBasket.getId())).thenReturn(fundingBasket);

        boolean response = CheckoutController.checkout();
        //ResponseEntity<boolean> response = CheckoutController.checkout();
        // Analyze
        //getbasket returning null
        assertEquals(true, response);
       
        
    }

    @Test
    public void testCheckoutNothing() throws IOException {  // getNeed may throw IOException
        // Setup
        Need need = new Need(99,"T-shirt", 10.99f, 10, "shirt");

        boolean response = CheckoutController.checkout();;

        // Analyze
        //getbasket returning null
        assertEquals(false, response);
        
    }

    // @Test
    // public void testDeleteNeed() throws IOException { // deleteHero may throw IOException
    //     // Setup
    //     int needId = 99;
    //     // when deleteHero is called return true, simulating successful deletion
    //     when(mockNeedDAO.deleteNeed(needId)).thenReturn(true);

    //     // Invoke
    //     ResponseEntity<Need> response = needController.deleteNeed(needId);

    //     // Analyze
    //     assertEquals(HttpStatus.OK,response.getStatusCode());
    // }

}
