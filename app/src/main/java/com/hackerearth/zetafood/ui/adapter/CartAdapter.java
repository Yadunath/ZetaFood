package com.hackerearth.zetafood.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackerearth.zetafood.R;
import com.hackerearth.zetafood.data.local.entity.CartEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartEntity> cartEntities;

    public CartAdapter(List<CartEntity> cartEntities) {
        this.cartEntities = cartEntities;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_layout_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartEntity cartEntity = cartEntities.get(position);
        if (cartEntity !=null){
            holder.title.setText(cartEntity.name);
            holder.type.setText(cartEntity.category);
            holder.price.setText(cartEntity.price+" $");
            Glide.with(holder.itemView.getContext()).load(cartEntity.imageUrl).circleCrop().into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return cartEntities.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView type;
        TextView price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
