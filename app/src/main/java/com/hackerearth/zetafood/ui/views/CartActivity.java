package com.hackerearth.zetafood.ui.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackerearth.zetafood.R;
import com.hackerearth.zetafood.data.local.FoodDatabase;
import com.hackerearth.zetafood.data.remote.api.ApiClient;
import com.hackerearth.zetafood.ui.adapter.CartAdapter;
import com.hackerearth.zetafood.ui.viewmodel.CartViewModel;
import com.hackerearth.zetafood.ui.viewmodel.ViewModelFactory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {
    private String TAG="CartActivity";
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private TextView emptyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        emptyText =findViewById(R.id.empty);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        ImageView imageView=findViewById(R.id.cart);
        imageView.setVisibility(View.GONE);
        findViewById(R.id.sort).setVisibility(View.GONE);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ApiClient apiClient=ApiClient.getInstance();
        FoodDatabase foodDatabase=FoodDatabase.getInstance(getApplicationContext());
        ViewModelFactory viewModelFactory=new ViewModelFactory(getApplication(),foodDatabase,apiClient.getFoodApiService());
        cartViewModel=new ViewModelProvider(this,viewModelFactory).get(CartViewModel.class);
        observeForcart();
        cartViewModel.fetchCart();
    }

    private void observeForcart(){
        cartViewModel.getCartLiveData().observe(this,listResource -> {
            Log.d(TAG,""+listResource.data.get(0).name);
            if (listResource.data.size()>0){
                emptyText.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(new CartAdapter(listResource.data));
        });
    }
}