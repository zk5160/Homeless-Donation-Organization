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
public class NeedTest {
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
        Need need = new Need(expected_id,expected_name, expected_cost, expected_quantity, expected_type);

        // Analyze
        assertEquals(expected_id,need.getId());
        assertEquals(expected_name,need.getName());
        assertEquals(expected_cost,need.getCost());
        assertEquals(expected_quantity,need.getQuantity());
        assertEquals(expected_type,need.getType());

        
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";

        Need need = new Need(id, name, cost, quantity, type);

        String expected_name = "Galactic Agent";

        // Invoke
        need.setName(expected_name);

        // Analyze
        assertEquals(expected_name,need.getName());
    }

    @Test
    public void testQuantity() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";

        Need need = new Need(id, name, cost, quantity, type);

        int expected_quantity = 6;

        // Invoke
        need.setQuantity(expected_quantity);

        // Analyze
        assertEquals(expected_quantity,need.getQuantity());
    }

    @Test
    public void testType() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";

        Need need = new Need(id, name, cost, quantity, type);

        String expected_type = "food";

        // Invoke
        need.setType(expected_type);

        // Analyze
        assertEquals(expected_type,need.getType());
    }

    @Test
    public void testCost() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";

        Need need = new Need(id, name, cost, quantity, type);

        float expected_cost = 5.99f;

        // Invoke
        need.setCost(expected_cost);

        // Analyze
        assertEquals(expected_cost,need.getCost());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        float cost = 10.99f;
        int quantity = 5;
        String type = "candy";
        String expected_string = String.format(Need.STRING_FORMAT, id, name, cost, quantity, type);
        Need need = new Need(id, name, cost, quantity, type);

        // Invoke
        String actual_string = need.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}