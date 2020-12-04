package com.hackerearth.zetafood.data.remote.model;

import com.hackerearth.zetafood.data.local.entity.FoodEntity;

import java.util.Comparator;

public class PriceComparator implements Comparator<FoodEntity> {

    private boolean ascending =true;
    public PriceComparator(boolean ascending) {
        this.ascending=ascending;
    }

    @Override
    public int compare(FoodEntity foodEntity, FoodEntity t1) {
        int order;
        if (ascending){
            order=(int) ((int)Double.parseDouble(foodEntity.price)-(int) Double.parseDouble(t1.price));
        }else {
            order= (int) (int) Double.parseDouble(t1.price) - ((int)Double.parseDouble(foodEntity.price));
        }
        return order;
    }

}
