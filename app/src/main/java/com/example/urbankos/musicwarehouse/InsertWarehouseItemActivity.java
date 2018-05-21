package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbankos.musicwarehouse.dao.WarehouseDAO;
import com.example.urbankos.musicwarehouse.dao.WarehouseItemDAO;
import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseItemAdapter;

import java.util.ArrayList;

public class InsertWarehouseItemActivity extends AppCompatActivity implements WarehouseItemAdapter.QuantityInterface{
//  DAO
    WarehouseDAO warehouseDAO = null;
    WarehouseItemDAO warehouseItemDAO = null;

//  INTENT
    private Intent insertItemIntent = null;
    private String quantity = null;
    private String id = null;

//  ITEMS
    private TextView items_available = null;

//  BUTTONS
    private Button buttonWarehouseItem = null;

//  WAREHOUSE ARRAY
    ArrayList<Warehouse> warehousesList = null;
    ArrayList<WarehouseItem> warehouseItemList = null;

//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private WarehouseItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_warehouse_item);

//      Set dao
        if(warehouseDAO == null){
            warehouseDAO = new WarehouseDAO(this);
        }

        if (warehouseItemDAO == null){
            warehouseItemDAO = new WarehouseItemDAO(this);
        }

//      Set items
        setItems();

//      Get extras
        getExtras();

//      Get warehouses
        warehousesList = getWarehouses();

//      Recycler view
        setRecyclerView();

//      Insert Warehouse item
        buttonWarehouseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(warehouseItemList.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Items cannot be delivered to the Warehouse.", Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(items_available.getText().toString()) > 0){
                    Toast.makeText(getApplicationContext(), "You have to arrange all the items.", Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(items_available.getText().toString()) < 0){
                    Toast.makeText(getApplicationContext(), "Number of items is too big.", Toast.LENGTH_SHORT).show();

                }else{
                    if(warehouseItemDAO.insertWarehouseItem(warehouseItemList, Integer.parseInt(id))){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getApplicationContext().startActivity(intent);
                    }
                }
            }
        });

    }

//    -----------------------------------------------------------------------------------------------------------
    private void getExtras(){
        insertItemIntent = getIntent();

        if(insertItemIntent != null && insertItemIntent.getStringExtra("quantity") != null
                && insertItemIntent.getStringExtra("id") != null) {

            quantity = insertItemIntent.getStringExtra("quantity");
            id = insertItemIntent.getStringExtra("id");

            items_available.setText(quantity);
        }

    }

    private void setItems(){
        items_available = findViewById(R.id.items_available);
        buttonWarehouseItem = findViewById(R.id.button_insert_warehouse_item);
    }

    private ArrayList<Warehouse> getWarehouses(){
        return warehouseDAO.getAllWarehousesRecycler();
    }

    private void setRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_warehouse_item);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WarehouseItemAdapter(this, warehousesList,
                Integer.valueOf(quantity), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onQuantityChange(int newValue) {
        items_available.setText(String.valueOf(newValue));
        mAdapter.updateQuantity(newValue);
    }

    @Override
    public void sendAllData(ArrayList<WarehouseItem> warehouseItems, int position) {
        warehouseItemList = warehouseItems;
    }

}
