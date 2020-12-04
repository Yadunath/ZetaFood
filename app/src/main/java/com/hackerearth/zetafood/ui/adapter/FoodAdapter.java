package com.hackerearth.zetafood.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hackerearth.zetafood.R;
import com.hackerearth.zetafood.data.local.entity.FoodEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.RecyclerViewHolder> {
    private static final String TAG="FoodAdapter";
    private List<FoodEntity> items;

    public FoodAdapter(List<FoodEntity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.food_layout_item,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        FoodEntity foodEntity=items.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
        Glide.with(holder.itemView.getContext()).load(foodEntity.image).apply(requestOptions).into(holder.imageView);
        holder.name.setText(foodEntity.name);

        holder.label.setText(foodEntity.label);
        if (foodEntity.label.isEmpty()){
            holder.label.setText(foodEntity.category);
        }
        holder.price.setText(foodEntity.price+" $");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView label;
        TextView price;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.textName);
            label=itemView.findViewById(R.id.label);
            price=itemView.findViewById(R.id.price);
        }
    }
}
