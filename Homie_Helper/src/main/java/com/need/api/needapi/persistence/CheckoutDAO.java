package com.need.api.needapi.persistence;

import java.io.IOException;
import com.need.api.needapi.model.Need;

//connect to inventory
//connect to funding basket

//checkout has everything in funding basket
//get each item in funding basket and subtract quantity
//clear checkout nad funding basket items
/**
 * Defines the interface for Checkout persistence
 * The funding basket should have the ability to add and remove items
 * The checkout should take all needs from the funding basket and checkout,
 * clearing the funding basket items and decrementing the inventory
 * @author Team 7G, Jade Kimmel
 */
public interface CheckoutDAO {
    /**
     * Retrieves all {@linkplain Fundingbasket needs}
     * 
     * @return An array of {@link Basket needs} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
//    FundingBasket[] getCheckout() throws IOException;

    /**
     * Retrieves a {@linkplain Fundingbasket needs}
     * 
     * @return a {@link Basket needs} will be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
 //   Checkout clearCheckout() throws IOException;

    //inventory will either use the need json file or copy it so that
    //the quantity can be decremented
    //not sure if we're keeping track of prices and how much bought
    /**
     * Updates and saves a {@linkplain Inventory need}
     * 
     * @param {@link Need need} object to be updated and saved
     * 
     * @return updated {@link Inventory need} if successful, null if
     * {@link Need need} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
//    Checkout updateInventory(Need need) throws IOException;

}