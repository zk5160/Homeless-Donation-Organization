package com.need.api.needapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a FundingBasket entity
 * 
 * @author SWEN Faculty
 * @author modifications by Team 7G
 */
public class FundingBasket {
    private static final Logger LOG = Logger.getLogger(FundingBasket.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "FundingBasket [id=%d, name=%s, cost=%f, quantity=%d, type=%s]";
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private float cost;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("type") private String type;
    @JsonProperty("id") private int id;

    /**
     * Create a fundingbasket with the given id, name, cost, quantity and type
     * @param quantity : amount of fundingbaskets that we want
     * @param cost  : how much fundingbasket is worth
     * @param type  : the type of fundingbasket
     * @param name  : name of the fundingbasket
     * @param id    : id of fundingbasket to keep organized
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public FundingBasket(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("cost") float cost, 
                @JsonProperty("quantity") int quantity, @JsonProperty("type") String type) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
        this.id = id;
    }

    /**
     * Retrieves the type of the fundingbasket
     * @return The type of the fundingbasket
     */
    public String getType() {return type;}

    /**
     * Retrieves the quantity of the fundingbasket
     * @return The quantity of the fundingbasket
     */
    public int getQuantity() {return quantity;}

    /**
     * Retrieves the cost of the fundingbasket
     * @return The cpst of the fundingbasket
     */
    public float getCost() {return cost;}

    /**
     * Retrieves the id of the fundingbasket
     * @return The id of the fundingbasket
     */
    public int getId() {return id;}

    /**
     * Retrieves the name of the fundingbasket
     * @return The name of the fundingbasket
     */
    public String getName() {return name;}

    /**
     * Sets the name of the fundingbasket - necessary for JSON object to Java object deserialization
     * @param name The name of the fundingbasket
     */
    public void setName(String name) {this.name = name;}

    /**
     * Sets the name of the fundingbasket - necessary for JSON object to Java object deserialization
     * @param cost The cost of the fundingbasket
     */
    public void setCost(float cost) {this.cost = cost;}

    /**
     * Sets the type of the fundingbasket - necessary for JSON object to Java object deserialization
     * @param type The name of the fundingbasket
     */
    public void setType(String type) {this.type = type;}

    /**
     * Sets the quantity of the fundingbasket - necessary for JSON object to Java object deserialization
     * @param quanitty The quantity of the fundingbasket
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name, cost, quantity, type);
    }
}