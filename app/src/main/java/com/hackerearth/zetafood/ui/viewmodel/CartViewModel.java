package com.hackerearth.zetafood.ui.viewmodel;

import com.hackerearth.zetafood.data.Resource;
import com.hackerearth.zetafood.data.local.dao.CartDao;
import com.hackerearth.zetafood.data.local.entity.CartEntity;
import com.hackerearth.zetafood.data.repository.CartRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private MutableLiveData<Resource<List<CartEntity>>> cartLiveData=new MutableLiveData<>();
    private CartRepository cartRepository;
    public CartViewModel(CartDao cartDao) {
        cartRepository=new CartRepository(cartDao);
    }

    public void insert(CartEntity cartEntity){
        cartRepository.addToCart(cartEntity);
    }
    public void fetchCart(){
        cartRepository.fetchCartList().subscribe(listResource -> cartLiveData.postValue(listResource));
    }

    public LiveData<Resource<List<CartEntity>>> getCartLiveData() {
        return cartLiveData;
    }
}
