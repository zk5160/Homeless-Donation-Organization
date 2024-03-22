package com.need.api.needapi.controller;

import java.io.IOException;

//import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.NeedDAO;
import com.need.api.needapi.persistence.UserDAO;

public class CheckoutController {
    //private FundingBasketDAO basket;
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
        if (user.getUser(userid).basket.size() <= 0){
            return false;
        }
        //checking length?
        System.out.println(user.getUser(userid).basket.size());
        while (user.getUser(userid).basket.size() > 0) {
            //iterating through ids of funding basket, not the fastest
            //need to decremenet first, then remove?
            //System.out.println("TEST1");
            for (int id=0; id<100; id++) {
                    //check if id exists
                    //System.out.println("IN FOR LOOP");
                    if (user.getUser(userid).checkBasketId(id) == true){
                        System.out.println("IF STATEMENT");
                        //need decrement failing
                        int x = (user.getUser(userid).BasketQuantity(id));
                        need.decrementQuantity(id, x);
                        //System.out.println("NEED");
                        user.getUser(userid).removeFromBasket(id);
                       
                    }
                }
                //System.out.println("OUT");
                //return true;
            }
            //System.out.println("TRUE");
            return true;
        //  }
        // catch (Exception e){
        //     return false;
        // }
    }
}
