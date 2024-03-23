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
import com.need.api.needapi.model.Need;


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
public class FundingBasketFileDAO implements FundingBasketDAO {
    private static final Logger LOG = Logger.getLogger(FundingBasketFileDAO.class.getName());
    Map<Integer, FundingBasket> fundingbaskets;   // Provides a local cache of the fundingbasket objects
                                // so that we don't fundingbasket to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between FundingBasket
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new fundingbasket
    private String filename;    // Filename to read from and write to

    /**
     * Creates a FundingBasket File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public FundingBasketFileDAO(@Value("${fundingbaskets.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the fundingbaskets from the file
    }

    /**
     * Generates the next id for a new {@linkplain FundingBasket fundingbasket}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain FundingBasket fundingbaskets} from the tree map
     * 
     * @return  The array of {@link FundingBasket fundingbaskets}, may be empty
     */
    private FundingBasket[] getFundingBasketArray() {
        return getFundingBasketArray(null);
    }

    /**
     * Generates an array of {@linkplain FundingBasket fundingbaskets} from the tree map for any
     * {@linkplain FundingBasket fundingbaskets} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain FundingBasket fundingbaskets}
     * in the tree map
     * 
     * @return  The array of {@link FundingBasket fundingbaskets}, may be empty
     */
    private FundingBasket[] getFundingBasketArray(String containsText) { // if containsText == null, no filter
        ArrayList<FundingBasket> fundingbasketArrayList = new ArrayList<>();

        for (FundingBasket fundingbasket : fundingbaskets.values()) {
            if (containsText == null || fundingbasket.getName().contains(containsText)) {
                fundingbasketArrayList.add(fundingbasket);
            }
        }

        FundingBasket[] fundingbasketArray = new FundingBasket[fundingbasketArrayList.size()];
        fundingbasketArrayList.toArray(fundingbasketArray);
        return fundingbasketArray;
    }

    /**
     * Saves the {@linkplain FundingBasket fundingbaskets} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link FundingBasket fundingbaskets} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        FundingBasket[] fundingbasketArray = getFundingBasketArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),fundingbasketArray);
        return true;
    }

    /**
     * Loads {@linkplain FundingBasket fundingbaskets} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        fundingbaskets = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of fundingbaskets
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        FundingBasket[] fundingbasketArray = objectMapper.readValue(new File(filename),FundingBasket[].class);

        // Add each fundingbasket to the tree map and keep track of the greatest id
        for (FundingBasket fundingbasket : fundingbasketArray) {
            fundingbaskets.put(fundingbasket.getId(), fundingbasket);
            if (fundingbasket.getId() > nextId)
                nextId = fundingbasket.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket[] getFundingBasket() {
        synchronized(fundingbaskets) {
            return getFundingBasketArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket[] findFundingBasket(String containsText) {
        synchronized(fundingbaskets) {
            return getFundingBasketArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket getFundingBasket(int id) {
        synchronized(fundingbaskets) {
            if (fundingbaskets.containsKey(id))
                return fundingbaskets.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket createFundingBasket(FundingBasket fundingbasket) throws IOException {
        synchronized(fundingbaskets) {
            // We create a new fundingbasket object because the id field is immutable
            // and we fundingbasket to assign the next unique id
            FundingBasket newFundingBasket = new FundingBasket(nextId(), fundingbasket.getName(), fundingbasket.getCost(), fundingbasket.getQuantity(), fundingbasket.getType());
            fundingbaskets.put(newFundingBasket.getId(), newFundingBasket);
            save(); // may throw an IOException
            return newFundingBasket;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket updateFundingBasket(FundingBasket fundingbasket) throws IOException {
        synchronized(fundingbaskets) {
            if (fundingbaskets.containsKey(fundingbasket.getId()) == false)
                return null;  // fundingbasket does not exist

            fundingbaskets.put(fundingbasket.getId(),fundingbasket);

            save(); // may throw an IOException
            return fundingbasket;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteFundingBasket(int id) throws IOException {
        synchronized(fundingbaskets) {
            if (fundingbaskets.containsKey(id)) {
                fundingbaskets.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public FundingBasket[] discounts(float percent) throws IOException {
        //need array of fundingbasket
        //update funding basket
        //change cost, multiply by percent
        //in for loop for each id
        //System.out.println("TEST");
        synchronized(fundingbaskets) {
            //for loop is not the fastest, don't think for each would work
            //System.out.println("SYNC");
            for (int id=0; id<100; id++){
                //System.out.println("FOR");
                if (fundingbaskets.containsKey(id)) {
                    //System.out.println("IF");
                    FundingBasket f = getFundingBasket(id);
                    float cost = (1-percent)*f.getCost();
                    FundingBasket update = new FundingBasket(id, f.getName(), cost, f.getQuantity(), f.getType());
                    updateFundingBasket(update);
                }
            }
            return getFundingBasketArray();
        }
        
    }

    /**
    ** {@inheritDoc}
     */
//     @Override
//     public boolean checkout() throws IOException {
//         //if null, nothing to checkout
//         if (getFundingBasket() == null) {
//             return false;
//         }
//         //checkout
//         else {
//         //getting funding basket
//         synchronized(fundingbaskets) {
//             //going through all ids, not the fastest method
//             //pretending there are at most 10, rn
//             for (int id=0; id<10; id++) {
//                 if (fundingbaskets.containsKey(id)) {
//                     //getting item quanity
//                     //needdao.getneed(id);
//                     int x = getFundingBasket(id).getQuantity();
//                     //int y = getNeed(id).getQuantity();
//                     //int z = y-x;
//                     //updating quantity
//                     //updating need.json file
//                     //[id=%d, name=%s, cost=%f, quantity=%d, type=%s]
//                     //Need.updateNeed(need);
//                     //Need need = new Need(id, getFundingBasket(id).getName(), getFundingBasket(id).getCost(), z, getFundingBasket(id).getType());
//                     //updateNeed(need);
//                     //updateNeed();
//                     //removing id
//                     deleteFundingBasket(id);
//                 }
//              }
//         }
//     }
//         return save();
// }
}
