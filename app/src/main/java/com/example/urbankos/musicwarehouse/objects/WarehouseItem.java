package com.example.urbankos.musicwarehouse.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarehouseItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_warehouse")
    @Expose
    private int id_warehouse;
    @SerializedName("id_item")
    @Expose
    private int id_item;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    private String warehouseName;

    public WarehouseItem(){}

    public WarehouseItem(int id, int id_warehouse, int id_item, int quantity) {
        this.id = id;
        this.id_warehouse = id_warehouse;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public WarehouseItem(int id_warehouse, int id_item, int quantity) {
        this.id_warehouse = id_warehouse;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public WarehouseItem(int id, int id_warehouse, int id_item, int quantity, String warehouseName) {
        this.id = id;
        this.id_warehouse = id_warehouse;
        this.id_item = id_item;
        this.quantity = quantity;
        this.warehouseName = warehouseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_warehouse() {
        return id_warehouse;
    }

    public void setId_warehouse(int id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
