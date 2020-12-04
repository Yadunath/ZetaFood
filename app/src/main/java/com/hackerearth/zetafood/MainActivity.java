package com.hackerearth.zetafood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hackerearth.zetafood.data.local.FoodDatabase;
import com.hackerearth.zetafood.data.local.entity.CartEntity;
import com.hackerearth.zetafood.data.local.entity.FoodEntity;
import com.hackerearth.zetafood.data.remote.api.ApiClient;
import com.hackerearth.zetafood.data.remote.model.PriceComparator;
import com.hackerearth.zetafood.ui.adapter.FoodAdapter;
import com.hackerearth.zetafood.ui.viewmodel.CartViewModel;
import com.hackerearth.zetafood.ui.viewmodel.FoodItemsViewModel;
import com.hackerearth.zetafood.ui.viewmodel.ViewModelFactory;
import com.hackerearth.zetafood.ui.views.CartActivity;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;

import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity implements CardStackListener, View.OnClickListener {
    private static final String TAG="MainActivity";
    private FoodItemsViewModel foodItemsViewModel;
    private CartViewModel cartViewModel;
    private CardStackView cardStackView;
    private FloatingActionButton button,closeButton;
    private CardStackLayoutManager manager;
    private List<FoodEntity> foodEntityList;
    boolean ascending=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBar();
        cardStackView = findViewById(R.id.card_stack_view);
        button = findViewById(R.id.addToCart);
        closeButton=findViewById(R.id.close);

        ApiClient apiClient=ApiClient.getInstance();
        FoodDatabase foodDatabase=FoodDatabase.getInstance(getApplicationContext());
        ViewModelFactory viewModelFactory=new ViewModelFactory(getApplication(),foodDatabase,apiClient.getFoodApiService());
        foodItemsViewModel=new ViewModelProvider(this,viewModelFactory).get(FoodItemsViewModel.class);
        cartViewModel=new ViewModelProvider(this,viewModelFactory).get(CartViewModel.class);
        observeDataChange();
        foodItemsViewModel.loadFoodList();

        manager = new CardStackLayoutManager(getApplicationContext(),this);
        cardStackView.setLayoutManager(manager);

        button.setOnClickListener(view -> {
            addToCart(manager.getTopPosition());
        });
        closeButton.setOnClickListener(view -> {
            cardStackView.scrollToPosition(0);
        });
    }

    private void addToCart(int position) {
        if (foodEntityList.size()>0){
            Toast.makeText(getApplicationContext(),"Item Addded to Cart",Toast.LENGTH_SHORT).show();
            FoodEntity foodEntity=foodEntityList.get(position);
            CartEntity cartEntity=new CartEntity(foodEntity.id,foodEntity.name,foodEntity.image,foodEntity.category,foodEntity.label,foodEntity.price,foodEntity.description);
            cartViewModel.insert(cartEntity);
        }
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        ImageView imageView = findViewById(R.id.cart);
        ImageView sort=findViewById(R.id.sort);
        sort.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    private void observeDataChange(){
        foodItemsViewModel.getFoodLiveData().observe(this, listResource -> {
            setAdapter(listResource.data);
        });
    }

    private void setAdapter(List<FoodEntity> list){
        FoodAdapter foodAdapter=new FoodAdapter(list);
        foodEntityList=list;
        cardStackView.setAdapter(foodAdapter);
    }


    @Override
    public void onCardDragging(Direction direction, float ratio) {
    }

    @Override
    public void onCardSwiped(Direction direction) {
        handleSwipe(direction);
    }

    private void handleSwipe(Direction direction) {
        if (direction.equals(Direction.Right)){
            addToCart(manager.getTopPosition()-1);
        }else {
            foodEntityList.add(foodEntityList.get(manager.getTopPosition()-1));
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {
    }

    @Override
    public void onCardDisappeared(View view, int position) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cart:
                Intent intent=new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.sort:
                if (foodEntityList.size()>0){
                    changeSortState();
                    Collections.sort(foodEntityList, new PriceComparator(ascending));
                    cardStackView.getAdapter().notifyDataSetChanged();
                }
                break;
        }
    }

    private void changeSortState() {
        if (ascending){
            ascending=false;
        }else {
            ascending=true;
        }
    }
}