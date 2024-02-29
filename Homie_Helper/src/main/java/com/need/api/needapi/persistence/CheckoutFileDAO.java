package com.need.api.needapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.need.api.needapi.model.FundingBasket;


/**
 * Implements the functionality for JSON file-based peristance for FundingBaskets
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as fundingbasketed
 * 
 * @author SWEN Faculty
 * @author modificaitons by Team 7G
 */
@Component
public class CheckoutFileDAO implements CheckoutDAO {
    // private static final Logger LOG = Logger.getLogger(CheckoutBasketFileDAO.class.getName());
    // Map<Integer, Checkout> checkout;   // Provides a local cache of the fundingbasket objects
    //                             // so that we don't fundingbasket to read from the file
    //                             // each time
    // private ObjectMapper objectMapper;  // Provides conversion between FundingBasket
    //                                     // objects and JSON text format written
    //                                     // to the file
    // private static int nextId;  // The next Id to assign to a new fundingbasket
    // private String filename;    // Filename to read from and write to

    /**
     * Creates a FundingBasket File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    // public CheckoutFileDAO(@Value("${checkout.file}") String filename,ObjectMapper objectMapper) throws IOException {
    //     this.filename = filename;
    //     this.objectMapper = objectMapper;
    //     load();  // load the checkout from the file
    // }



    // @Override
    // public Checkout[] getCheckout() {
    //     synchronized(fundingbaskets) {
    //         return getCheckout();
    //     }
    // }
}

