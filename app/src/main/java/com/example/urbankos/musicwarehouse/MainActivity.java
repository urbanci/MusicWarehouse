package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.urbankos.musicwarehouse.dao.WarehouseDAO;
import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseAdapter;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

//  FLOATING BUTTONS
    FloatingActionButton addWarehouse = null;
    FloatingActionButton addItem = null;

//  DAO
    WarehouseDAO dao = null;

//  OBJECTS
    Warehouse warehouse = null;

//  LISTS
    ArrayList<Warehouse> warehousesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper helper = new DatabaseHelper(this);


        if(dao == null){
            dao = new WarehouseDAO(this);
        }

//      Add warehouse button
        this.addWarehouseButton();

//      Add item button
        this.addItemButton();

//      Get warehouse
        warehousesList = getWarehouses();

//      Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WarehouseAdapter(this, warehousesList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void addItemButton(){
        addItem = findViewById(R.id.add_item);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, InsertItemActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void addWarehouseButton(){
        addWarehouse = findViewById(R.id.add_warehouse);
        addWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, InsertWarehouseActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private ArrayList<Warehouse> getWarehouses() {
        return dao.getAllWarehousesRecycler();
    }
}
