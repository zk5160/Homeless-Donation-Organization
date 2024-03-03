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
 * Test the Hero Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class FundingBasketControllerTest {
    private FundingBasketController fundingbasketController;
    private FundingBasketDAO mockFundingBasketDAO;

    /**
     * Before each test, create a new fundingbasketController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupfundingbasketController() {
        mockFundingBasketDAO = mock(FundingBasketDAO.class);
        fundingbasketController = new FundingBasketController(mockFundingBasketDAO);
    }

    @Test
    public void testgetFundingBasket() throws IOException {  // getFundingBasket may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // When the same id is passed in, our mock Need DAO will return the Need object
        when(mockFundingBasketDAO.getFundingBasket(fundingbasket.getId())).thenReturn(fundingbasket);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.getFundingBasket(fundingbasket.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(fundingbasket,response.getBody());
    }

    @Test
    public void testgetFundingBasketNotFound() throws Exception { // createHero may throw IOException
        // Setup
        int fundingbasketId = 99;
        // When the same id is passed in, our mock Hero DAO will return null, simulating
        // no hero found
        when(mockFundingBasketDAO.getFundingBasket(fundingbasketId)).thenReturn(null);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.getFundingBasket(fundingbasketId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testgetFundingBasketHandleException() throws Exception { // createHero may throw IOException
        // Setup
        int fundingbasketId = 99;
        // When getHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).getFundingBasket(fundingbasketId);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.getFundingBasket(fundingbasketId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /*****************************************************************
     * The following tests will fail until all HeroController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testCreateFundingBasket() throws IOException {  // createHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // when createHero is called, return true simulating successful
        // creation and save
        when(mockFundingBasketDAO.createFundingBasket(fundingbasket)).thenReturn(fundingbasket);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.createFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(fundingbasket,response.getBody());
    }

    @Test
    public void testcreateFundingBasketFailed() throws IOException {  // createHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // when createHero is called, return false simulating failed
        // creation and save
        when(mockFundingBasketDAO.createFundingBasket(fundingbasket)).thenReturn(null);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.createFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testcreateFundingBasketHandleException() throws IOException {  // createHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");

        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).createFundingBasket(fundingbasket);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.createFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateFundingBasket() throws IOException { // updateHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // when updateHero is called, return true simulating successful
        // update and save
        when(mockFundingBasketDAO.updateFundingBasket(fundingbasket)).thenReturn(fundingbasket);
        ResponseEntity<FundingBasket> response = fundingbasketController.updateFundingBasket(fundingbasket);
        fundingbasket.setName("Bolt");

        // Invoke
        response = fundingbasketController.updateFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(fundingbasket,response.getBody());
    }

    @Test
    public void testUpdateFundingBasketFailed() throws IOException { // updateHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // when updateHero is called, return true simulating successful
        // update and save
        when(mockFundingBasketDAO.updateFundingBasket(fundingbasket)).thenReturn(null);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.updateFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateFundingBasketHandleException() throws IOException { // updateHero may throw IOException
        // Setup
        FundingBasket fundingbasket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        // When updateHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).updateFundingBasket(fundingbasket);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.updateFundingBasket(fundingbasket);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testgetFundingBaskets() throws IOException { // getHeroes may throw IOException
        // Setup
        FundingBasket[] fundingbaskets = new FundingBasket[2];
        fundingbaskets[0] = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        fundingbaskets[1] = new FundingBasket(100,"Pants", 15.99f, 7, "pants");
        // needs[0] = new Need(99,"Bolt");
        // needs[1] = new Need(100,"The Great Iguana");
        
        // When getHeroes is called return the heroes created above
        when(mockFundingBasketDAO.getFundingBasket()).thenReturn(fundingbaskets);

        // Invoke
        ResponseEntity<FundingBasket[]> response = fundingbasketController.getFundingBasket();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(fundingbaskets,response.getBody());
    }

    @Test
    public void testgetFundingBasketsHandleException() throws IOException { // getHeroes may throw IOException
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).getFundingBasket();

        // Invoke
        ResponseEntity<FundingBasket[]> response = fundingbasketController.getFundingBasket();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchFundingBaskets() throws IOException { // findHeroes may throw IOException
        // Setup
        String searchString = "la";
        FundingBasket[] fundingbaskets = new FundingBasket[2];
        fundingbaskets[0] = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");
        fundingbaskets[1] = new FundingBasket(100,"Pants", 15.99f, 7, "pants");
        // needs[0] = new Need(99,"Bolt");
        // needs[1] = new Need(100,"The Great Iguana");
        
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockFundingBasketDAO.findFundingBasket(searchString)).thenReturn(fundingbaskets);

        // Invoke
        ResponseEntity<FundingBasket[]> response = fundingbasketController.searchFundingBasket(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(fundingbaskets,response.getBody());
    }

    @Test
    public void testSearchFundingBasketsHandleException() throws IOException { // findHeroes may throw IOException
        // Setup
        String searchString = "an";
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).findFundingBasket(searchString);

        // Invoke
        ResponseEntity<FundingBasket[]> response = fundingbasketController.searchFundingBasket(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteFundingBasket() throws IOException { // deleteHero may throw IOException
        // Setup
        int fundingbasketId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockFundingBasketDAO.deleteFundingBasket(fundingbasketId)).thenReturn(true);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.deleteFundingBasket(fundingbasketId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteFundingBasketNotFound() throws IOException { // deleteHero may throw IOException
        // Setup
        int fundingbasketId = 99;
        // when deleteHero is called return false, simulating failed deletion
        when(mockFundingBasketDAO.deleteFundingBasket(fundingbasketId)).thenReturn(false);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.deleteFundingBasket(fundingbasketId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteFundingBasketHandleException() throws IOException { // deleteHero may throw IOException
        // Setup
        int fundingbasketId = 99;
        // When deleteHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockFundingBasketDAO).deleteFundingBasket(fundingbasketId);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingbasketController.deleteFundingBasket(fundingbasketId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
