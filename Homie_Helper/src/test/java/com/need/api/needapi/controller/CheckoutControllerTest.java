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

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupNeedController() {
        mockNeedDAO = mock(NeedDAO.class);
        mockBasket = mock(FundingBasketDAO.class);
        CheckoutController = new CheckoutController(mockBasket);
    }

    @Test
    public void testCheckout() throws IOException {  // getNeed may throw IOException
        // Setup
        Need need = new Need(99,"T-shirt", 10.99f, 10, "shirt");
        FundingBasket fundingBasket = new FundingBasket (99,"T-shirt", 10.99f, 3, "shirt");
        
        // need to fix the when 
        //when(CheckoutController.checkout()).thenReturn(FundingBasket[]); 

        boolean response = CheckoutController.checkout();
        //.thenReturn(fundingBasket);
        //need to check needs were updated
        // Invoke

        //boolean response = CheckoutController.checkout();

        // Analyze
        assertEquals(true, response);
        //assertEquals(need,response.getBody());
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
