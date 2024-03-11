package com.need.api.needapi.controller;

import java.io.IOException;

import com.need.api.needapi.persistence.FundingBasketDAO;
import com.need.api.needapi.persistence.NeedDAO;

public class CheckoutController {
    private FundingBasketDAO basket;
    private NeedDAO need;

    public CheckoutController(FundingBasketDAO basket){
        this.basket = basket;
    }

    public boolean checkout() throws IOException{
        if (basket.getFundingBasket() == null){
            return false;
        }
        else{
            //iterating through ids, not the fastest
            for (int id=0; id<100; id++) {
                    if (basket.deleteFundingBasket(id) != false){
                        //not sure if it actually will delete
                        //need to create inventory decrement function, only decrements one
                        need.decrementQuantity(id, basket.getFundingBasket(id).getQuantity());
                    }
                }
             }
        return true;
    };
}
