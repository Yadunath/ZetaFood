package com.hackerearth.zetafood.data.local.dao;

import com.hackerearth.zetafood.data.local.entity.CartEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cartentity")
    List<CartEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartEntity  cartEntity);

    @Delete
    void delete(CartEntity cartEntity);
}
