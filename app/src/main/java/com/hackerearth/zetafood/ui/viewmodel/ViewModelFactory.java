package com.hackerearth.zetafood.ui.viewmodel;

import android.app.Application;

import com.hackerearth.zetafood.data.local.FoodDatabase;
import com.hackerearth.zetafood.data.remote.api.FoodApiService;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private FoodDatabase database;
    private FoodApiService apiService;
    private Application mApplication;
    public ViewModelFactory(Application mApplication,FoodDatabase database,FoodApiService apiService) {
        this.database=database;
        this.apiService=apiService;
        this.mApplication=mApplication;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FoodItemsViewModel.class))
            return (T) new FoodItemsViewModel(mApplication,database.foodDao(),apiService);
        else if (modelClass.isAssignableFrom(CartViewModel.class))
            return (T) new CartViewModel(database.cartDao());
        return null;
    }
}
