package com.hackerearth.zetafood.ui.viewmodel;

import android.app.Application;

import com.hackerearth.zetafood.data.Resource;
import com.hackerearth.zetafood.data.local.dao.FoodDao;
import com.hackerearth.zetafood.data.local.entity.FoodEntity;
import com.hackerearth.zetafood.data.remote.api.FoodApiService;
import com.hackerearth.zetafood.data.repository.FoodRepository;
import com.hackerearth.zetafood.ui.utils.ConnectivityStatus;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodItemsViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<Resource<List<FoodEntity>>> foodLiveData =new MutableLiveData<>();
    public FoodItemsViewModel(Application mApplication,FoodDao foodDao, FoodApiService foodApiService) {
        boolean isConnected= ConnectivityStatus.isConnected(mApplication);
        foodRepository=new FoodRepository(foodDao,foodApiService,isConnected);
    }

    public void loadFoodList(){
        foodRepository.loadFoodList().
                subscribe( listResource -> getFoodLiveData().postValue(listResource));
    }

    public MutableLiveData<Resource<List<FoodEntity>>> getFoodLiveData() {
        return foodLiveData;
    }


}
