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

import com.example.urbankos.musicwarehouse.ItemListActivity;
import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.objects.Item;

import java.util.ArrayList;

//povemo, da se bodo v recyclerviewu prikazovali podatki preko ViewHolderja
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<String> categoryList;
    private Context context;
    private String id_warehouse;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout category_view_holder;
        private TextView recycler_category_name;
        private TextView recycler_category_id;

        //      Zapomni si vsakega posebej v viewu
        public ViewHolder(final View itemView) {
            super(itemView);
            recycler_category_name = itemView.findViewById(R.id.recycler_category_name);
            recycler_category_id = itemView.findViewById(R.id.recycler_category_id);
            category_view_holder = itemView.findViewById(R.id.category_view_holder);

        }
    }

    public CategoryAdapter(Context con, ArrayList<String> list, String id_warehouse) {
        categoryList = list;
        context = con;
        this.id_warehouse = id_warehouse;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_category, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, final int position) {
        holder.recycler_category_name.setText(categoryList.get(position));
        holder.recycler_category_id.setText(String.valueOf(position+1)+". ");

        holder.category_view_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemListActivity.class);
                intent.putExtra("category", categoryList.get(position));
                intent.putExtra("id", id_warehouse);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}


