package com.vilin.test;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private static final float PRIME_COST = 1000;

    private List<MiddleMan> MiddleMans = new ArrayList<MiddleMan>();

    public float getUserPrice(float factoryPrice){
        float maxMiddlePrice = 0;
        for(MiddleMan temp : MiddleMans){
            if(maxMiddlePrice == 0){
                maxMiddlePrice = temp.calOutPrice(PRIME_COST);
            } else if(maxMiddlePrice < temp.calOutPrice(PRIME_COST)) {
                maxMiddlePrice = temp.calOutPrice(PRIME_COST);
            }
        }
        return maxMiddlePrice;
    }
}
