package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.dao.WarehouseItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

//  INTENT
    private Intent insertItemIntent;

//  VARIABLES
    private String id_item;

//  DAO
    private ItemDAO daoItem;
    private WarehouseItemDAO daoWarehouseItem;

//  ITEM
    private Item item;
    private ArrayList<WarehouseItem> warehouseItem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

    //  Dao
        if(daoItem == null && daoWarehouseItem == null){
            daoItem = new ItemDAO(this);
            daoWarehouseItem = new WarehouseItemDAO(this);
        }

    //  Get extras
        getExtras();

    //  Set elements
        setElements();

    //  Get item
        item = getItem();
        warehouseItem = getWarehouseItem();

        Log.d("ITEM -> ", item.getFirm());

        for(WarehouseItem wi : warehouseItem){
            Log.d("WAREHOUSE ITEMS-> ", wi.getWarehouseName()+" "+wi.getId_item());
        }

    }

    private void getExtras(){
        insertItemIntent = getIntent();

        if(insertItemIntent != null && insertItemIntent.getStringExtra("id") != null) {
            id_item = insertItemIntent.getStringExtra("id");
        }
    }

    private void setElements(){

    }

    private Item getItem(){
        return daoItem.getItem(id_item);
    }

    private ArrayList<WarehouseItem> getWarehouseItem(){
        return daoWarehouseItem.getQuantityesInWarehouses(id_item);
    }
}
