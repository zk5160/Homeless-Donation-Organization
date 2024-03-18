package com.need.api.needapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.need.api.needapi.model.User;

/**
 * The unit test suite for the User class
 * 
 * @author SWEN Faculty
 */
@Tag("Model-tier")
public class UserTest {
    @Test
    public void testCtor() {
        //id, name basket
        // Setup
        int expected_id = 0;
        String expected_name = "Fred";
        ArrayList<FundingBasket> expected_basket= new ArrayList<>();
        expected_basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));

        // Invoke
        User user = new User(expected_id, expected_name, expected_basket);

        // Analyze
        assertEquals(expected_id,user.getId());
        assertEquals(expected_name,user.getName());
        assertEquals(expected_basket,user.getBasket());
        
    }

    @Test
    public void testName() {
        // Setup
        int id = 0;
        String name = "Fred";
        ArrayList<FundingBasket> basket= new ArrayList<>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        User user = new User(id, name, basket);

        String expected_name = "updated username";

        user.setName(expected_name);
        // Analyze
        assertEquals(expected_name, user.getName());
 
    }

    @Test
    public void testToString() {
        int id = 0;
        String name = "Fred";
        ArrayList<FundingBasket> basket= new ArrayList<>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        String expected_string = String.format(User.STRING_FORMAT, id, name, basket);
        User user = new User(id, name, basket);

        String actual = user.toString();
        // Analyze
        assertEquals(expected_string,actual);
    }

    @Test
    public void testCartAdd() {
        // Setup
        int user_id = 5;
        String name = "User0";
        ArrayList<FundingBasket> basket = new ArrayList<FundingBasket>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        User user = new User(user_id,name, basket);

        ArrayList<FundingBasket> expected_basket = new ArrayList<FundingBasket>();
        expected_basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));

        FundingBasket expected_add = new FundingBasket(2, "milk", 2.99f, 2, "food");
        expected_basket.add(expected_add);

        // Invoke
        user.addToBasket(expected_add);

        // Analyze
        assertEquals(expected_basket.get(0), user.getBasket().get(0));
        assertEquals(expected_basket.get(1), user.getBasket().get(1));
    }

    @Test
    public void testCartRemove() {
        // Setup
        int id = 0;
        String name = "User0";
        ArrayList<FundingBasket> basket = new ArrayList<FundingBasket>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));

        User user = new User(id, name, basket);
        ArrayList<FundingBasket> expected_basket = new ArrayList<FundingBasket>();
        expected_basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));

        // Invoke
        user.removeFromBasket(1);

        // Analyze
        assertEquals(expected_basket, user.getBasket());
        //assertTrue(expected_basket.get(0).equals(user.getBasket().get(0)));
    }




}