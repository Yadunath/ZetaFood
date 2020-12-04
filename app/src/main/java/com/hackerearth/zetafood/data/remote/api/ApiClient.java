package com.hackerearth.zetafood.data.remote.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/";
    private Retrofit retrofit ;
    private FoodApiService foodApiService;
    private  static ApiClient apiClient;
    public ApiClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        foodApiService = retrofit.create(FoodApiService.class);
    }

    public static ApiClient getInstance(){
        if (apiClient == null){
            apiClient= new ApiClient();
        }
        return apiClient;
    }

    public FoodApiService getFoodApiService() {
        return foodApiService;
    }
}
