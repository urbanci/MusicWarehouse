package com.example.urbankos.musicwarehouse.objects;

public class ItemQuantity {
    private int id;
    private int quantity;
    private int quantityBefore;

    public ItemQuantity(int id, int quantity, int quantityBefore) {
        this.id = id;
        this.quantity = quantity;
        this.quantityBefore = quantityBefore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityBefore() {
        return quantityBefore;
    }

    public void setQuantityBefore(int quantityBefore) {
        this.quantityBefore = quantityBefore;
    }

}
