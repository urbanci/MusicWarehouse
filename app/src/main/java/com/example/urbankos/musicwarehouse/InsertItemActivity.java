package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;

public class InsertItemActivity extends AppCompatActivity {

//  DAO
    private ItemDAO dao = null;

//  ITEMS
    private SeekBar seekBar = null;

//  ITEM ITEMS
    private TextView quantity = null;
    private EditText name = null;
    private EditText country = null;
    private EditText price = null;

//  BUTTONS
    private Button insertItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

//      Set dao
        if(dao == null){
            dao = new ItemDAO(this);
        }

//      Set items
        setItems();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                quantity.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        insertItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = insertItem();
                Log.d("INT .> ", String.valueOf(id));

                if(id != 0){
                    Intent warehouseSelection = new Intent(getApplicationContext(), InsertWarehouseItemActivity.class);
                    warehouseSelection.putExtra("quantity", quantity.getText().toString());
                    warehouseSelection.putExtra("id", String.valueOf(id));
                    getApplicationContext().startActivity(warehouseSelection);
                }else{
                    Toast.makeText(getApplicationContext(), "Error occurred, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setItems(){
        seekBar = findViewById(R.id.insert_item_quantity);
        quantity = findViewById(R.id.item_quantity);
        insertItem = findViewById(R.id.button_insert_item);
    }

    private int insertItem(){
        name = findViewById(R.id.insert_item_name);
        country = findViewById(R.id.insert_item_country);
        price = findViewById(R.id.insert_item_price);
        quantity = findViewById(R.id.item_quantity);

        if(Integer.valueOf(quantity.getText().toString()) != 0) {
            return dao.insertItem(new Item(
                    name.getText().toString(),
                    country.getText().toString(),
                    Double.valueOf(price.getText().toString()),
                    "DIABLO",
                    Integer.valueOf(quantity.getText().toString())
            ));
        }else{
            return 0;
        }

    }
}

