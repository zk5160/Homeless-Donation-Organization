package com.need.api.needapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

//import com.need.api.needapi.model.Need;

/**
 * The unit test suite for the Hero class
 * 
 * @author SWEN Faculty
 */
@Tag("Model-tier")
public class FundingBasketTest {
    @Test
    public void testCtor() {
        //static final String STRING_FORMAT = "Need [id=%d, name=%s, cost=%f, quantity=%d, type=%s]";
        // Setup
        int expected_id = 99;
        String expected_name = "Wi-Fire";
        float expected_cost = 10.99f;
        int expected_quantity = 5;
        String expected_type = "candy";

        // Invoke
        FundingBasket fundingbasket = new FundingBasket(expected_id,expected_name, expected_cost, expected_quantity, expected_type);

        // Analyze
        assertEquals(expected_id,fundingbasket.getId());
        assertEquals(expected_name,fundingbasket.getName());
        assertEquals(expected_cost,fundingbasket.getCost());
        assertEquals(expected_quantity,fundingbasket.getQuantity());
        assertEquals(expected_type,fundingbasket.getType());

        
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";

        FundingBasket fundingbasket = new FundingBasket(id, name, cost, quantity, type);

        String expected_name = "Galactic Agent";

        // Invoke
        fundingbasket.setName(expected_name);

        // Analyze
        assertEquals(expected_name,fundingbasket.getName());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";
        String expected_string = String.format(FundingBasket.STRING_FORMAT, id, name, cost, quantity, type);
        FundingBasket fundingbasket = new FundingBasket(id, name, cost, quantity, type);

        // Invoke
        String actual_string = fundingbasket.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}