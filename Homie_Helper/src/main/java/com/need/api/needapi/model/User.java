package com.need.api.needapi.model;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Janked together reskin of the product system to do the same but with user accounts
 * 
 * @author Ethan Patterson
 */
public class User {
    private static final Logger LOG = Logger.getLogger(User.class.getName());
    // Package private for tests
    static final String STRING_FORMAT = "User [id=%d, name=%s, basket=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("basket") private ArrayList<FundingBasket> basket;


    /**
     * Create a user with the given id, name and empty cart
     * @param id The id of the user
     * @param name The name of the user
     * @param cart The cart of products

     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public User(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("basket") ArrayList<FundingBasket> basket) {
        this.id = id;
        this.name = name;
        this.basket = basket;

    }

    /**
     * Retrieves the id of the user
     * @return The id of the user
     */
    public int getId() {return id;}

    /**
     * Sets the name of the user- this should never really need to be used except maybe for user creation.
     * @param name The name of the user
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getName() {return name;}


    /**
     * Retrieves the full shopping cart
     * @return The whole cart list
     */
    public ArrayList<FundingBasket> getBasket() {return basket;}

    /**
     * adds an item to the end of the shopping cart
     * @param quantity The product to add
     */
    public void addToBasket(FundingBasket productToAdd) {this.basket.add(productToAdd);}

    /**
     * removes an item from shopping cart. this assumes the given id is actually in the shopping cart, does nothing otherwise
     * @param id The id of the product to remove
     */
    public void removeFromBasket(int id) {
        for (FundingBasket product : basket) {
            if(product.getId()==id){
                basket.remove(product);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,basket);
    }
}



    
