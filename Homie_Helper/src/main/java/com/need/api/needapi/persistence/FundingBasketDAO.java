package com.need.api.needapi.persistence;

import java.io.IOException;
import com.need.api.needapi.model.FundingBasket;

/**
 * Defines the interface for FundingBasket object persistence
 * 
 * @author SWEN Faculty
 * @author modification by Team 7G
 */
public interface FundingBasketDAO {
    /**
     * Retrieves all {@linkplain FundingBasket fundingbaskets}
     * 
     * @return An array of {@link FundingBasket fundingbasket} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    FundingBasket[] getFundingBasket() throws IOException;

    /**
     * Finds all {@linkplain FundingBasket fundingbaskets} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link FundingBasket fundingbasket} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    FundingBasket[] findFundingBasket(String containsText) throws IOException;


    /**
     * Retrieves a {@linkplain FundingBasket fundingbaskets} with the given id
     * 
     * @param id The id of the {@link FundingBasket fundingbasket} to get
     * 
     * @return a {@link FundingBasket fundingbasket} object with the matching id
     * <br>
     * null if no {@link FundingBasket fundingbasket} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    FundingBasket getFundingBasket(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain FundingBasket fundingbaskets}
     * 
     * @param fundingbasket {@linkplain FundingBasket fundingbaskets} object to be created and saved
     * <br>
     * The id of the fundingbasket object is ignored and a new uniqe id is assigned
     *
     * @return new {@link FundingBasket fundingbasket} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    FundingBasket createFundingBasket(FundingBasket fundingbasket) throws IOException;

    /**
     * Updates and saves a {@linkplain FundingBasket fundingbasket}
     * 
     * @param {@link FundingBasket fundingbasket} object to be updated and saved
     * 
     * @return updated {@link FundingBasket fundingbasket} if successful, null if
     * {@link FundingBasket fundingbasket} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    FundingBasket updateFundingBasket(FundingBasket fundingbasket) throws IOException;

    /**
     * Deletes a {@linkplain FundingBasket fundingbasket} with the given id
     * 
     * @param id The id of the {@link FundingBasket fundingbasket}
     * 
     * @return true if the {@link FundingBasket fundingbasket} was deleted
     * <br>
     * false if fundingbasket with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteFundingBasket(int id) throws IOException;

    //checkout user
    //need to create user id when we create user files
    //boolean checkout() throws IOException;
}
