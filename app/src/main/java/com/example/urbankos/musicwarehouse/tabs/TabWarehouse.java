package com.example.urbankos.musicwarehouse.tabs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.objects.ItemQuantity;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;
import com.example.urbankos.musicwarehouse.recyclers.ItemsListAdapter;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseItemAdapter;
import com.example.urbankos.musicwarehouse.recyclers.WarehouseItemTabAdapter;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class TabWarehouse extends Fragment implements WarehouseItemTabAdapter.QuantityInterface {
//  LISTS
    private ArrayList<WarehouseItem> warehouseItems;

//  LAYOUT ELEMENTS
    private TextView tab_warehouse_quantity;
    private EditText set_new_quantity;

//  ROOTVIEW
    private View rootView;

//  RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private WarehouseItemTabAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

//  QUANTITY
    private int quantity = 0;

//  BUTTONS
    private Button recycler_new_quantity;

    public TabWarehouse(ArrayList<WarehouseItem> warehouseItem) {
        this.warehouseItems = warehouseItem;
    }

//  On create --------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_warehouse, container, false);

//      Get & Set layout elements
        getLayoutElements();
        setLayoutElements();

//      Set recycler view
        setRecyclerView();

        recycler_new_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_new_quantity, null);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setView(view);

                set_new_quantity = view.findViewById(R.id.set_new_quantity);
                alertBuilder.setCancelable(true).setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("onClickDIALOG -> ", "ses");
                        quantity = Integer.valueOf(set_new_quantity.getText().toString());
                        mAdapter.updateQuantity(quantity);
                        mAdapter.updateWarehouseList();
                        mAdapter.notifyDataSetChanged();
                    }
                });

                Dialog dialog = alertBuilder.create();
                dialog.show();
            }
        });

        return rootView;
    }

//  ------------------------------------------------------------------------------------------------

    private void getLayoutElements(){
        tab_warehouse_quantity = rootView.findViewById(R.id.tab_warehouse_quantity);
        recycler_new_quantity = rootView.findViewById(R.id.recycler_new_quantity);
    }

    private void setLayoutElements(){

        for(WarehouseItem iq : warehouseItems){
            quantity = quantity + iq.getQuantity();
        }

        tab_warehouse_quantity.setText(String.valueOf(0));
    }

    private void setRecyclerView(){
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_tab_warehouse);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new WarehouseItemTabAdapter(getActivity(), warehouseItems, quantity, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onQuantityChange(int newValue) {
        tab_warehouse_quantity.setText(String.valueOf(newValue));
        mAdapter.updateQuantity(newValue);
        Log.d("newWalue -> ", String.valueOf(newValue));
    }

    @Override
    public void sendAllData(ArrayList<WarehouseItem> warehouseList, int position) {
        for (WarehouseItem warehouseItem: warehouseList){
            Log.d("ListTabHouse: -> ", warehouseItem.getQuantity()+" "+warehouseItem.getId_item()+" "+warehouseItem.getId_warehouse());
        }
    }
}
