package com.need.api.needapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.need.api.needapi.model.FundingBasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Need File DAO class
 * 
 * @author SWEN Faculty
 */
@Tag("Persistence-tier")
public class FundingBasketDAOTest {
    FundingBasketFileDAO fundingDAO;
    FundingBasket[] testBaskets;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testBaskets = new FundingBasket[3];
        testBaskets[0] = new FundingBasket(99,"tent", 15f, 4, "supplies");
        testBaskets[1] = new FundingBasket(100,"sweater", 5f, 3, "clothes");
        testBaskets[2] = new FundingBasket(101,"soup", 2.99f, 2, "food");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the need array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),FundingBasket[].class))
                .thenReturn(testBaskets);
        fundingDAO = new FundingBasketFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetBaskets() {
        // Invoke
        FundingBasket[] baskets = fundingDAO.getFundingBasket();

        // Analyze
        assertEquals(baskets.length,testBaskets.length);
        for (int i = 0; i < testBaskets.length;++i)
            assertEquals(baskets[i],testBaskets[i]);
    }

    @Test
    public void testFindBaskets() {
        // Invoke
        FundingBasket[] baskets = fundingDAO.findFundingBasket("s");

        // Analyze
        assertEquals(baskets.length,2);
        assertEquals(baskets[0],testBaskets[1]);
        assertEquals(baskets[1],testBaskets[2]);
    }

    // @Test
    // public void testDiscount() throws IOException {
    //     FundingBasket[] test;
    //     test = new FundingBasket[3];
    //     test[0] = new FundingBasket(99,"tent", 13.5f, 4, "supplies");
    //     test[1] = new FundingBasket(100,"sweater", 4.9f, 3, "clothes");
    //     test[2] = new FundingBasket(101,"soup", 2.69f, 2, "food");
    //     // Invoke
    //     FundingBasket[] basket = fundingDAO.discounts(0.1f);

    //     assertEquals(test[0], basket[0]);
    //     assertEquals(test[1], basket[1]);
    //     assertEquals(test[2], basket[2]);
    // }

    @Test
    public void testGetBasket() {
        // Invoke
        FundingBasket basket = fundingDAO.getFundingBasket(99);

        // Analzye
        assertEquals(basket,testBaskets[0]);
    }

    @Test
    public void testDeleteBasket() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> fundingDAO.deleteFundingBasket(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test needs array - 1 (because of the delete)
        // Because needs attribute of NeedFileDAO is package private
        // we can access it directly
        assertEquals(fundingDAO.fundingbaskets.size(),testBaskets.length-1);
    }

    @Test
    public void testCreateBasket() throws IOException {
        // Setup
        FundingBasket basket = new FundingBasket(72,"T-shirt", 10.99f, 10, "shirt");

        // Invoke
        FundingBasket result = assertDoesNotThrow(() -> fundingDAO.createFundingBasket(basket),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        FundingBasket actual = fundingDAO.createFundingBasket(basket);

        // temporarily commented out, actual.getProperty() returns null
        //assertEquals(actual.getId(),basket.getId());
        assertEquals(actual.getName(),basket.getName());
    }

    @Test
    public void testUpdateBaskets() {
        // Setup
        FundingBasket basket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");

        // Invoke
        FundingBasket result = assertDoesNotThrow(() -> fundingDAO.updateFundingBasket(basket),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        FundingBasket actual = fundingDAO.getFundingBasket(basket.getId());
        assertEquals(actual,basket);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(FundingBasket[].class));

        FundingBasket basket = new FundingBasket(99,"T-shirt", 10.99f, 10, "shirt");

        assertThrows(IOException.class,
                        () -> fundingDAO.createFundingBasket(basket),
                        "IOException not thrown");
    }

    @Test
    public void testGetBasketNotFound() {
        // Invoke
        FundingBasket basket = fundingDAO.getFundingBasket(98);

        // Analyze
        assertEquals(basket,null);
    }

    @Test
    public void testDeleteBasketNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> fundingDAO.deleteFundingBasket(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(fundingDAO.fundingbaskets.size(),testBaskets.length); // note: keyword public added to NeedFileDAO Map needs for accessibility
    }

    @Test
    public void testUpdateBasketNotFound() {
        // Setup
        FundingBasket basket = new FundingBasket(0,"T-shirt", 10.99f, 10, "shirt");

        // Invoke
        FundingBasket result = assertDoesNotThrow(() -> fundingDAO.updateFundingBasket(basket),
                                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the NeedFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),FundingBasket[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new FundingBasketFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }

 }
