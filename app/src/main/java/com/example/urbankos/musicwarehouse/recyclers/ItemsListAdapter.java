package com.example.urbankos.musicwarehouse.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.urbankos.musicwarehouse.ItemActivity;
import com.example.urbankos.musicwarehouse.ItemListActivity;
import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.objects.Item;

import java.util.ArrayList;

//  povemo, da se bodo v recyclerviewu prikazovali podatki preko ViewHolderja
public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private ArrayList<Item> itemsList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout items_list_view_holder;
        private TextView recycler_items_list_name;
        private TextView recycler_items_list_id;
        private TextView recycler_items_list_firm;

//      Zapomni si vsakega posebej v viewu
        public ViewHolder(final View itemView) {
            super(itemView);
            recycler_items_list_name = itemView.findViewById(R.id.recycler_items_list_name);
            recycler_items_list_id = itemView.findViewById(R.id.recycler_items_list_id);
            recycler_items_list_firm = itemView.findViewById(R.id.recycler_items_list_firm);
            items_list_view_holder = itemView.findViewById(R.id.items_list_view_holder);

        }
    }

    public ItemsListAdapter(Context con, ArrayList<Item> list) {
        itemsList = list;
        context = con;
    }

    @NonNull
    @Override
    public ItemsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_items_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsListAdapter.ViewHolder holder, final int position) {
        holder.recycler_items_list_id.setText(String.valueOf(position+1));
        holder.recycler_items_list_name.setText(itemsList.get(position).getName());
        holder.recycler_items_list_firm.setText(itemsList.get(position).getFirm());

        holder.items_list_view_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("id", String.valueOf(itemsList.get(position).getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}


