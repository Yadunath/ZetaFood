package com.hackerearth.zetafood.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodEntity {
    @PrimaryKey
    public int id;
    @ColumnInfo(name =  "name")
    public String name;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "label")
    public String label;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "description")
    public String description;
}
