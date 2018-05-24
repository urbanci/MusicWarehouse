package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.recyclers.CategoryAdapter;
import com.example.urbankos.musicwarehouse.recyclers.ItemsListAdapter;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
//  INTENT
    private Intent insertItemIntent = null;

//  WAREHOUSE ID
    String category = null;
    String id_warehouse = null;

//  DAO
    ItemDAO dao = null;

//  ARRAYI
    ArrayList<Item> itemList = new ArrayList<>();

//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private ItemsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

//      Dao
        if(dao == null){
            dao = new ItemDAO(this);
        }

//      Get extras
        getExtras();

//      Get items in dao
        itemList = getItems();

//      Set recycler view
        setRecyclerView();
    }

//  ------------------------------------------------------------------------------------------------

    private void getExtras(){
        insertItemIntent = getIntent();

        if(insertItemIntent != null && insertItemIntent.getStringExtra("category") != null
                && insertItemIntent.getStringExtra("id") != null) {
            category = insertItemIntent.getStringExtra("category");
            id_warehouse = insertItemIntent.getStringExtra("id");
        }
    }

    private ArrayList<Item> getItems(){
        return dao.getItemNameFirm(category, id_warehouse);
    }

    private void setRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_items_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ItemsListAdapter(this, itemList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

}
