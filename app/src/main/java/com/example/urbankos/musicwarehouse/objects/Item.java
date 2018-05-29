package com.example.urbankos.musicwarehouse.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firm")
    @Expose
    private String firm;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("quantity")
    @Expose
    private int quantity;

    public Item(int id, String name, String firm, double price, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.firm = firm;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Item(String name, String firm, double price, String category, int quantity) {
        this.name = name;
        this.firm = firm;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Item(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public Item(int id, String name, String firm) {
        this.id = id;
        this.name = name;
        this.firm = firm;
    }

    public Item(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
