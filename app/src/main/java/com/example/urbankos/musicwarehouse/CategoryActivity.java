package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.recyclers.CategoryAdapter;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseAdapter;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
//  INTENT
    private Intent insertItemIntent = null;

//  WAREHOUSE ID
    String id_warehouse = null;

//  DAO
    ItemDAO dao = null;

//  ARRAYI
    ArrayList<String> categoryList = new ArrayList<>();

//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//      Dao
        if(dao == null){
            dao = new ItemDAO(this);
        }

//      Get extras
        getExtras();

//      Get categories in dao
        categoryList = getCategories();

        for (String i: categoryList){
            Log.d("KATEGORIJA -> ",i+" + "+categoryList.size());
        }

//      Recycler view
        setRecyclerView();
    }

//  --------------------------------------------------------------------------------------------------------------
    private void getExtras(){
        insertItemIntent = getIntent();

        if(insertItemIntent != null && insertItemIntent.getStringExtra("id") != null) {
            id_warehouse = insertItemIntent.getStringExtra("id");
        }
    }

    private ArrayList<String> getCategories(){
        return dao.getCategoriesOfItemsInWarehouse(id_warehouse);
    }

    private void setRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_category);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(this, categoryList, id_warehouse);
        mRecyclerView.setAdapter(mAdapter);
    }
}
