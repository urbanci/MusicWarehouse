package com.example.urbankos.musicwarehouse.recyclers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.urbankos.musicwarehouse.helpers.MinMaxFilter;

import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.objects.ItemQuantity;
import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;

import java.util.ArrayList;


//povemo, da se bodo v recyclerviewu prikazovali podatki preko ViewHolderja
public class WarehouseItemAdapter extends RecyclerView.Adapter<WarehouseItemAdapter.ViewHolder> {
    private ArrayList<Warehouse> warehouseList;
    private Context activityContext;
    private int quantity;
    private ArrayList<ItemQuantity> itemQuantities = new ArrayList<>();
    private ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();


//  INTERFACE
    private QuantityInterface quantityInterface;

    public interface QuantityInterface {
        void onQuantityChange(int newValue);
        void sendAllData(ArrayList<WarehouseItem> warehouseItems, int position);
    }

    public void updateQuantity (int newQuantity){
        quantity = newQuantity;
    }

//  MAIN
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView recycler_warehouse_item_name;
        private TextView recycler_warehouse_item_id;
        private EditText recycler_warehouse_item_items;

    //  Zapomni si vsakega posebej v viewu
        public ViewHolder(final View itemView) {
            super(itemView);

            recycler_warehouse_item_name = itemView.findViewById(R.id.recycler_warehouse_item_name);
            recycler_warehouse_item_id = itemView.findViewById(R.id.recycler_warehouse_item_id);
            recycler_warehouse_item_items = itemView.findViewById(R.id.recycler_warehouse_item_items);

        }
    }

    public WarehouseItemAdapter(Context context, ArrayList<Warehouse> list, int q, QuantityInterface Iquantity) {
        warehouseList = list;
        activityContext = context;
        quantity = q;
        quantityInterface = Iquantity;
    }

    @NonNull
    @Override
    public WarehouseItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RECYCLER ", parent.toString());
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_warehouse_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final WarehouseItemAdapter.ViewHolder holder, final int position) {

        holder.recycler_warehouse_item_name.setText(warehouseList.get(position).getName());
        holder.recycler_warehouse_item_id.setText(String.valueOf(warehouseList.get(position).getId())+". ");

        itemQuantities.add(new ItemQuantity(position, 0, 0));
        warehouseItems.add(new WarehouseItem(
                warehouseList.get(position).getId(),
                0, 0));
        Log.d("POSITION: ", String.valueOf(position));

            holder.recycler_warehouse_item_items.setFilters(new InputFilter[]{ new MinMaxFilter(0, quantity)});

            holder.recycler_warehouse_item_items.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int newValue = 0;
                    ItemQuantity itemQ = itemQuantities.get(position);

                    if (s.toString().equals("")) {
                        itemQ.setQuantity(0);
                    }else{
                        itemQ.setQuantity(Integer.valueOf(s.toString()));
                    }

                    newValue = quantity - itemQ.getQuantity() + itemQ.getQuantityBefore();

                    quantityInterface.onQuantityChange(newValue);
                    warehouseItems.get(position).setQuantity(itemQ.getQuantity());
                    quantityInterface.sendAllData(warehouseItems, position);

                    itemQ.setQuantityBefore(itemQ.getQuantity());
                }
            });


    }

    @Override
    public int getItemCount() {
        return warehouseList.size();
    }

}