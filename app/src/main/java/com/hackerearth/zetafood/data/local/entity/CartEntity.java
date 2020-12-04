package com.hackerearth.zetafood.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartEntity {
    @PrimaryKey
    public int id;
    @ColumnInfo(name =  "name")
    public String name;

    @ColumnInfo(name = "image")
    public String imageUrl;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "label")
    public String label;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "description")
    public String description;

    public CartEntity(int id, String name, String imageUrl, String category, String label, String price, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.label = label;
        this.price = price;
        this.description = description;
    }
}
