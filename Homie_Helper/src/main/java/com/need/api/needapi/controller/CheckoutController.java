package com.need.api.needapi.controller;

import java.io.IOException;

import com.need.api.needapi.model.FundingBasket;
import com.need.api.needapi.model.Need;
import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.NeedDAO;
import com.need.api.needapi.persistence.UserDAO;

public class CheckoutController {
    private FundingBasketDAO basket;
    private NeedDAO need;
    private UserDAO user;

    public CheckoutController(UserDAO user, NeedDAO need){
        this.user = user;
        this.need = need;
    }

    //parameter will be user id
    public boolean checkout(int userid) throws IOException{
         //try{
            //always null? fixed
        //getting user's basket
        if (user.getUser(userid).getBasket() == null){
            return false;
        }
        //checking length?
        while (user.getUser(userid).basket.size() != 0) {
            //iterating through ids of funding basket, not the fastest
            //need to decremenet first, then remove?
            for (int id=0; id<100; id++) {
                    //check if id exists
                    if (user.getUser(userid).checkBasketId(id) == true){
                        
                        need.decrementQuantity(id, user.getUser(userid).basket.get(id).getQuantity());
                        user.getUser(userid).removeFromBasket(id);
                    }
                }
                //return true;
            }
            return true;
        //  }
        // catch (Exception e){
        //     return false;
        // }
    }
}
