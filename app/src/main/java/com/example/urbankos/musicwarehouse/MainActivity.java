package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.dao.WarehouseDAO;
import com.example.urbankos.musicwarehouse.dao.WarehouseItemDAO;
import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseAdapter;
import com.example.urbankos.musicwarehouse.rest.RestInterface;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String APP_LINK = "http://164.8.160.35:3000";

//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private WarehouseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

//  FLOATING BUTTONS
    private FloatingActionButton addWarehouse = null;
    private FloatingActionButton addItem = null;

//  DAO
    private WarehouseDAO daoWarehouse = null;
    private ItemDAO daoItem = null;
    private WarehouseItemDAO daoWarehouseItem = null;

//  OBJECTS
    private Warehouse warehouse = null;

//  LISTS
    private ArrayList<Warehouse> warehousesList = null;

//  INTERFACE REST
    private RestInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper helper = new DatabaseHelper(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APP_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RestInterface.class);

        if(daoWarehouse == null && daoItem == null && daoWarehouseItem == null){
            daoWarehouse = new WarehouseDAO(this);
            daoItem = new ItemDAO(this);
            daoWarehouseItem = new WarehouseItemDAO(this);
        }

        this.updateWarehouses();
        this.updateItems();
        this.updateWarehouseItems();

//      Add warehouse button
        this.addWarehouseButton();

//      Add item button
        this.addItemButton();

//      Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WarehouseAdapter(this);
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

    private void setWarehouses(ArrayList<Warehouse> list){
        mAdapter.updateList(list);
        mAdapter.notifyDataSetChanged();
    }

    private void updateWarehouses(){
        Call<List<Warehouse>> restWarehouses = service.listWarehouses();
        restWarehouses.enqueue(new Callback<List<Warehouse>>() {
            @Override
            public void onResponse(Call<List<Warehouse>> call, Response<List<Warehouse>> response) {
                updateWarehousesBody(response);
            }

            @Override
            public void onFailure(Call<List<Warehouse>> call, Throwable t) {
                Log.e("ERROR -> ", t.toString());
            }
        });
    }

    private void updateItems(){
        Call<List<Item>> restItems = service.listItems();
        restItems.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                updateItemsBody(response);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("ERROR -> ", t.toString());
            }
        });
    }

    private void updateWarehouseItems(){
        Call<List<WarehouseItem>> restItems = service.listWarehouseItem();
        restItems.enqueue(new Callback<List<WarehouseItem>>() {
            @Override
            public void onResponse(Call<List<WarehouseItem>> call, Response<List<WarehouseItem>> response) {
                updateWarehouseItemBody(response);
            }

            @Override
            public void onFailure(Call<List<WarehouseItem>> call, Throwable t) {
                Log.e("ERROR -> ", t.toString());
            }
        });
    }

    private boolean updateWarehousesBody(Response<List<Warehouse>> response){
        List<Warehouse> warehouseList = response.body();
        ArrayList<Warehouse> warehousesOld = daoWarehouse.getAllWarehousesRecycler();
        boolean success = false;

        try {
            for (Warehouse w : warehouseList) {
                int id = w.getId();
                Log.d("PRINT -> ", w.getAddress());

                if (!warehousesOld.isEmpty()) {
                    if (warehousesOld.size() >= id) {
                        if (warehousesOld.get(id - 1).getId() == id) {
                            daoWarehouse.updateWarehouse(w);
                            Log.d("update", "update");
                        }
                    } else {
                        Log.d("insert", "insert");
                        boolean d = daoWarehouse.insertWarehouse(w);
                    }
                } else {
                    Log.d("insert", "insert");
                    boolean d = daoWarehouse.insertWarehouse(w);
                }
            }

            Log.d("SIZE -> ", String.valueOf(warehousesOld.size()));
            setWarehouses(warehousesOld);

            success = true;

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }

    private boolean updateItemsBody(Response<List<Item>> response){
        List<Item> itemList = response.body();
        ArrayList<Item> warehousesOld = daoItem.getItems();
        boolean success = false;

        try {
            for (Item i : itemList) {
                int id = i.getId();
                Log.d("PRINT -> ", i.getFirm());

                if (!warehousesOld.isEmpty()) {
                    if (warehousesOld.size() >= id) {
                        if (warehousesOld.get(id - 1).getId() == id) {
                            daoItem.updateItem(i);
                            Log.d("update", "update");
                        }
                    } else {
                        Log.d("insert", "insert");
                        int d = daoItem.insertItem(i);
                    }
                } else {
                    Log.d("insert", "insert");
                    int d = daoItem.insertItem(i);
                }
            }

            success = true;

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }

    private boolean updateWarehouseItemBody(Response<List<WarehouseItem>> response){
        List<WarehouseItem> warehouseItemList = response.body();
        ArrayList<WarehouseItem> warehousesOld = daoWarehouseItem.getWarehouseItems();
        boolean success = false;

        try {
            for (WarehouseItem i : warehouseItemList) {
                int id = i.getId();
                Log.d("PRINT -> ", String.valueOf(i.getQuantity()));

                if (!warehousesOld.isEmpty()) {
                    if (warehousesOld.size() >= id) {
                        if (warehousesOld.get(id - 1).getId() == id) {
                            daoWarehouseItem.updateWarehouseItemFull(i);
                            Log.d("update", "update");
                        }
                    } else {
                        Log.d("insert", "insert");
                        boolean d = daoWarehouseItem.insertWarehouseItemFull(i);
                    }
                } else {
                    Log.d("insert", "insert");
                    boolean d = daoWarehouseItem.insertWarehouseItemFull(i);
                }
            }

            success = true;

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }
}
