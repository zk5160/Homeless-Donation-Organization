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

    public CheckoutController(UserDAO user){
        this.user = user;
    }

    //parameter will be user id
    public boolean checkout(int userid){
         try{
            //always null?
        //getting user's basket
        if (user.getUser(userid).getBasket() == null){
            return false;
        }
            //iterating through ids, not the fastest
            for (int id=0; id<100; id++) {
                    if (user.getUser(userid).removeFromBasket(id) != false){
                        //not sure if it actually will delete
                        need.decrementQuantity(id, user.getUser(userid).basket.get(id).getQuantity());
                    }
                }
                return true;
         }
        catch (Exception e){
            return false;
        }
    }
}
