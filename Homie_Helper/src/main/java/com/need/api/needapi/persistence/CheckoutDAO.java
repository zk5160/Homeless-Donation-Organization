package com.need.api.needapi.persistence;

import java.io.IOException;
import com.need.api.needapi.model.Need;

//connect to inventory
//connect to funding basket

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
    Basket[] getBasket() throws IOException;

    /**
     * Retrieves a {@linkplain Fundingbasket needs}
     * 
     * @return a {@link Basket needs} will be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Basket clearBasket() throws IOException;

    /**
     * Creates and saves a {@linkplain Need needs}
     * 
     * @param need {@linkplain Need needs} object to be created and saved
     * <br>
     * The id of the need object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Need need} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need createNeed(Need need) throws IOException;

    /**
     * Updates and saves a {@linkplain Need need}
     * 
     * @param {@link Need need} object to be updated and saved
     * 
     * @return updated {@link Need need} if successful, null if
     * {@link Need need} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need}
     * 
     * @return true if the {@link Need need} was deleted
     * <br>
     * false if need with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteNeed(int id) throws IOException;
}
