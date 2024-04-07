package com.need.api.needapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.model.FundingBasket;

/**
 * Handles the REST API requests for the FundingBasket resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 * @author modifications by Team 7G
 */

@RestController
@RequestMapping("fundingbasket")
public class FundingBasketController {
    private static final Logger LOG = Logger.getLogger(FundingBasketController.class.getName());

    private FundingBasketDAO fundingbasketDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param fundingbasketDao The {@link FundingBasketDAO FundingBasket Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public FundingBasketController(FundingBasketDAO fundingbasketDao) {
        this.fundingbasketDao = fundingbasketDao;
    }

    /**
     * Responds to the GET request for a {@linkplain FundingBasket fundingbaskets} for the given id
     * 
     * @param id The id used to locate the {@link FundingBasket fundingbasket}
     * 
     * @return ResponseEntity with {@link FundingBasket fundingbasket} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<FundingBasket> getFundingBasket(@PathVariable int id) {
        LOG.info("GET /fundingbasket/" + id);
        try {
            FundingBasket fundingbasket = fundingbasketDao.getFundingBasket(id);
            if (fundingbasket != null)
                return new ResponseEntity<FundingBasket>(fundingbasket,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain FundingBasket fundingbaskets}
     * 
     * @return ResponseEntity with array of {@link FundingBasket fundingbasket} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<FundingBasket[]> getFundingBasket() {
        LOG.info("GET /fundingbasket");

        try{
        FundingBasket[] fundingbasket = fundingbasketDao.getFundingBasket();

        return new ResponseEntity<FundingBasket[]>(fundingbasket,HttpStatus.OK);
    }

            catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain FundingBasket fundingbaskets} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link FundingBasket fundingbasket}
     * 
     * @return ResponseEntity with array of {@link FundingBasket FundingBasket} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all fundingbaskets that contain the text "ma"
     * GET http://localhost:8080/fundingbasket/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<FundingBasket[]> searchFundingBasket(@RequestParam String name) {
        LOG.info("GET /fundingbasket/?name="+name);

        try {
            FundingBasket[] fundingbasket = fundingbasketDao.findFundingBasket(name);
            return new ResponseEntity<FundingBasket[]>(fundingbasket,HttpStatus.OK);
        }

            catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain FundingBasket fundingbaskets} with the provided fundingbasket object
     * 
     * @param fundingbasket - The {@link FundingBasket fundingbasket} to create
     * 
     * @return ResponseEntity with created {@link FundingBasket fundingbasket} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link fundingbasket fundingbasket} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<FundingBasket> createFundingBasket(@RequestBody FundingBasket fundingbasket) {
        LOG.info("POST /fundingbasket " + fundingbasket);

        try {
            FundingBasket h = fundingbasketDao.createFundingBasket(fundingbasket);

            return new ResponseEntity<FundingBasket>(h,HttpStatus.CREATED);
        }

            catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain FundingBasket fundingbaskets} with the provided {@linkplain FundingBasket fundingbaskets} object, if it exists
     * 
     * @param fundingbasket The {@link FundingBasket fundingbasket} to update
     * 
     * @return ResponseEntity with updated {@link FundingBasket fundingbasket} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<FundingBasket> updateFundingBasket(@RequestBody FundingBasket fundingbasket) {
        LOG.info("PUT /fundingbasket " + fundingbasket);
        
        try {
            FundingBasket h = fundingbasketDao.updateFundingBasket(fundingbasket);
            LOG.info("" + h);
            if (h != null)
                return new ResponseEntity<FundingBasket>(h,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain FundingBasket fundingbaskets} with the given id
     * 
     * @param id The id of the {@link FundingBasket fundingbasket} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<FundingBasket>deleteFundingBasket(@PathVariable int id) {
        LOG.info("DELETE /fundingbasket/" + id);

        try {
            boolean fundingbasket = fundingbasketDao.deleteFundingBasket(id);

            if (fundingbasket == true)
                return new ResponseEntity<FundingBasket>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
