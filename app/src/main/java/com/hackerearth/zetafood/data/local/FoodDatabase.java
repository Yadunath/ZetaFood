package com.hackerearth.zetafood.data.local;

import android.content.Context;

import com.hackerearth.zetafood.data.local.dao.CartDao;
import com.hackerearth.zetafood.data.local.dao.FoodDao;
import com.hackerearth.zetafood.data.local.entity.CartEntity;
import com.hackerearth.zetafood.data.local.entity.FoodEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodEntity.class, CartEntity.class}, version = 2,  exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    public abstract FoodDao foodDao();

    public abstract CartDao cartDao();

    private static volatile FoodDatabase INSTANCE;
    public static FoodDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FoodDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static FoodDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                FoodDatabase.class, "foodzeta.db")
                .allowMainThreadQueries().build();
    }
}
