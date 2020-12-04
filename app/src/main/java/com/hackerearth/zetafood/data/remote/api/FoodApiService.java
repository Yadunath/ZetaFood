package com.hackerearth.zetafood.data.remote.api;

import com.hackerearth.zetafood.data.local.entity.FoodEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FoodApiService {

    @GET ("reciped9d7b8c.json")
    Observable<List<FoodEntity>> getAllItems();
}
