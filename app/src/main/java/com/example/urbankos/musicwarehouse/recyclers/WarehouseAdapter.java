package com.example.urbankos.musicwarehouse.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.urbankos.musicwarehouse.CategoryActivity;
import com.example.urbankos.musicwarehouse.ItemActivity;
import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.objects.Warehouse;

import java.util.ArrayList;

//povemo, da se bodo v recyclerviewu prikazovali podatki preko ViewHolderja
public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.ViewHolder> {
    private ArrayList<Warehouse> warehouseList;
    private Context mainActivityContext;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout warehouse_view_holder;
        private TextView recycler_warehouse_name;
        private TextView recycler_warehouse_town;
        private TextView recycler_warehouse_id;

//      Zapomni si vsakega posebej v viewu
        public ViewHolder(final View itemView) {
            super(itemView);
            recycler_warehouse_name = itemView.findViewById(R.id.recycler_warehouse_name);
            recycler_warehouse_town = itemView.findViewById(R.id.recycler_warehouse_town);
            recycler_warehouse_id = itemView.findViewById(R.id.recycler_warehouse_id);
            warehouse_view_holder = itemView.findViewById(R.id.warehouse_view_holder);

        }
    }

    public WarehouseAdapter(Context context, ArrayList<Warehouse> list) {
        warehouseList = list;
        mainActivityContext = context;
    }

    @NonNull
    @Override
    public WarehouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_warehouse, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final WarehouseAdapter.ViewHolder holder, final int position) {
        holder.recycler_warehouse_name.setText(warehouseList.get(position).getName());
        holder.recycler_warehouse_town.setText(warehouseList.get(position).getTown());
        holder.recycler_warehouse_id.setText(String.valueOf(warehouseList.get(position).getId())+". ");

        holder.warehouse_view_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityContext, CategoryActivity.class);
                intent.putExtra("id", String.valueOf(warehouseList.get(position).getId()));
                mainActivityContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return warehouseList.size();
    }

}


