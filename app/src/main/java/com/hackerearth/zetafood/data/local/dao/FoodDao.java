package com.hackerearth.zetafood.data.local.dao;

import com.hackerearth.zetafood.data.local.entity.FoodEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM foodentity")
    List<FoodEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FoodEntity foodEntity);

    @Delete
    void delete(FoodEntity foodEntity);
}
