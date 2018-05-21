package com.example.urbankos.musicwarehouse.objects;

public class WarehouseItem {
    private int id;
    private int id_warehouse;
    private int id_item;
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
