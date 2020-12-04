package com.hackerearth.zetafood.data.repository;

import android.util.Log;

import com.hackerearth.zetafood.data.NetworkBoundResource;
import com.hackerearth.zetafood.data.Resource;
import com.hackerearth.zetafood.data.local.dao.FoodDao;
import com.hackerearth.zetafood.data.local.entity.FoodEntity;
import com.hackerearth.zetafood.data.remote.api.FoodApiService;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class FoodRepository {
    private String TAG="FoodRepository";
    public FoodDao foodDao;
    public FoodApiService foodApiService;
    private boolean isConnected ;
    public FoodRepository(FoodDao foodDao,FoodApiService foodApiService,boolean isConnected) {
        this.foodDao = foodDao;
        this.foodApiService = foodApiService;
        this.isConnected=isConnected;
    }

    public Observable<Resource<List<FoodEntity>>> loadFoodList(){
        return new NetworkBoundResource<List<FoodEntity>,List<FoodEntity>>(){

            @Override
            protected void saveCallResult(@NonNull List<FoodEntity> item) {
                Log.d(TAG,"result "+item.size());
                for(FoodEntity foodEntity : item){
                    foodDao.insert(foodEntity);
                }
            }

            @Override
            protected boolean shouldFetch() {
                return isConnected;
            }

            @NonNull
            @Override
            protected Flowable<List<FoodEntity>> loadFromDb() {
                List<FoodEntity> movieEntities = foodDao.getAll();
                if(movieEntities == null || movieEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(movieEntities).sorted();
            }

            @NonNull
            @Override
            protected Observable<Resource<List<FoodEntity>>> createCall() {
                return foodApiService.getAllItems().
                        flatMap(response -> Observable.just(Resource.success(response)));
            }
        }.getAsObservable();
    }
}
