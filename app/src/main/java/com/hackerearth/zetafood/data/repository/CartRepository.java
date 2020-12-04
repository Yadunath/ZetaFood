package com.hackerearth.zetafood.data.repository;

import android.util.Log;

import com.hackerearth.zetafood.data.NetworkBoundResource;
import com.hackerearth.zetafood.data.Resource;
import com.hackerearth.zetafood.data.local.dao.CartDao;
import com.hackerearth.zetafood.data.local.entity.CartEntity;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class CartRepository {
    private CartDao cartDao;

    public CartRepository(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addToCart(CartEntity cartEntity){
        cartDao.insert(cartEntity);
    }

    public Observable<Resource<List<CartEntity>>> fetchCartList(){
        return new NetworkBoundResource<List<CartEntity>,List<CartEntity>>(){

            @Override
            protected void saveCallResult(@NonNull List<CartEntity> item) {
            }
            @Override
            protected boolean shouldFetch() {
                return false;
            }

            @NonNull
            @Override
            protected Flowable<List<CartEntity>> loadFromDb() {
                List<CartEntity> movieEntities = cartDao.getAll();
                Log.d("TES",""+movieEntities.size());
                if(movieEntities == null || movieEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(movieEntities);
            }

            @NonNull
            @Override
            protected Observable<Resource<List<CartEntity>>>createCall() {
                return null;
            }
        }.getAsObservable();
    }
}
