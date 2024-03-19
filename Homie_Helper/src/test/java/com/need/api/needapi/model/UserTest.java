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
    public void testUserAddToBasket() {
        // Setup
        int user_id = 5;
        String name = "User0";
        ArrayList<FundingBasket> basket = new ArrayList<>();
        //basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        User user = new User(user_id,name, basket);

        ArrayList<FundingBasket> expected_basket = new ArrayList<>();
        //expected_basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));

        //FundingBasket expected_add = new ArrayList<FundingBasket>();
        FundingBasket expected_add =new FundingBasket(2, "milk", 2.99f, 2, "food");
        expected_basket.add(expected_add);

        // Invoke
        user.addToBasket(expected_add);

        // Analyze
        assertEquals(expected_basket, user.getBasket());
        //assertEquals(expected_basket.get(0), user.getBasket().get(0));
       // assertEquals(expected_basket.get(1), user.getBasket().get(1));
    }

    @Test
    public void testUserRemoveFromBasket() {
        // Setup
        int id = 0;
        String name = "User0";
        ArrayList<FundingBasket> basket = new ArrayList<>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));

        User user = new User(id, name, basket);

        ArrayList<FundingBasket> expected_basket = new ArrayList<>();
        //FundingBasket expected_add =new FundingBasket(2, "milk", 2.99f, 2, "food");
        //expected_basket.add(expected_add);
        // 
        expected_basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));

        // Invoke
        boolean response = user.removeFromBasket(1);

        // Analyze
        assertEquals(true, response);
    }

    @Test
    public void testUserRemoveInvalidFromBasket() {
        // Setup
        int id = 0;
        String name = "User0";
        ArrayList<FundingBasket> basket = new ArrayList<>();
        basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
        basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));

        User user = new User(id, name, basket);

        // Invoke
        boolean response = user.removeFromBasket(3);

        // Analyze
        assertEquals(false, response);
    }

    @Test
    public void testCheckId(){
                // Setup
                int id = 0;
                String name = "User0";
                ArrayList<FundingBasket> basket = new ArrayList<>();
                basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
                basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));
        
                User user = new User(id, name, basket);
        
                // Invoke
                boolean response = user.checkBasketId(1);
        
                // Analyze
                assertEquals(true, response);
    }

    @Test
    public void testCheckIdNotValid(){
                // Setup
                int id = 0;
                String name = "User0";
                ArrayList<FundingBasket> basket = new ArrayList<>();
                basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
                basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));
        
                User user = new User(id, name, basket);
        
                // Invoke
                boolean response = user.checkBasketId(5);
        
                // Analyze
                assertEquals(false, response);
    }

    @Test
    public void testBasketQuantity(){
                // Setup
                int id = 0;
                String name = "User0";
                ArrayList<FundingBasket> basket = new ArrayList<>();
                basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
                basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));
                int expected = 3;
                User user = new User(id, name, basket);
        
                // Invoke
                int response = user.BasketQuantity(1);
        
                // Analyze
                assertEquals(expected, response);
    }

    @Test
    public void testBasketQuantityZero(){
                // Setup
                int id = 0;
                String name = "User0";
                ArrayList<FundingBasket> basket = new ArrayList<>();
                basket.add(new FundingBasket(1, "tent", 10.99f, 3, "housing"));
                basket.add(new FundingBasket(2, "milk", 2.99f, 4, "food"));
                int expected = 0;
                User user = new User(id, name, basket);
        
                // Invoke
                int response = user.BasketQuantity(5);
        
                // Analyze
                assertEquals(expected, response);
    }


}