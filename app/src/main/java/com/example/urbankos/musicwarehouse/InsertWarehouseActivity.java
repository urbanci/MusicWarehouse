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

import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.dao.WarehouseDAO;

public class InsertWarehouseActivity extends AppCompatActivity {
//  LOG
    public final String LOG = "INSERT_WAREHOUSE ";


//  DAO
    private WarehouseDAO dao = null;

//  ITEMS
    private SeekBar seekBar = null;

//  WAREHOUSE ITEMS
    private TextView capacity = null;
    private EditText name = null;
    private EditText address = null;
    private EditText town = null;
    private EditText country = null;

//  BUTTON
    private Button insertWarehouse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_warehouse);

//      Set all items
        setWarehouse();

//      Set dao
        if(dao == null){
            dao = new WarehouseDAO(this);
        }

//      SEEKBAR listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                capacity.setText(String.valueOf(progress));
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

//      INSERT WAREHOUSE listener
        insertWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertWarehouse()){
                    Intent myIntent = new Intent(InsertWarehouseActivity.this, MainActivity.class);
                    InsertWarehouseActivity.this.startActivity(myIntent);
                }
            }
        });

    }

    public void setWarehouse(){
        insertWarehouse = findViewById(R.id.button_insert_warehouse);
        seekBar = findViewById(R.id.seekbar_warehouse);
        capacity = findViewById(R.id.insert_warehouse_capacity);
    }

    public boolean insertWarehouse(){
        name = findViewById(R.id.insert_warehouse_name);
        address = findViewById(R.id.insert_warehouse_address);
        town = findViewById(R.id.insert_warehouse_town);
        country = findViewById(R.id.insert_warehouse_country);

        if(Integer.valueOf(capacity.getText().toString()) != 0) {
            return dao.insertWarehouse(new Warehouse(
                    name.getText().toString(),
                    address.getText().toString(),
                    town.getText().toString(),
                    country.getText().toString(),
                    Integer.parseInt(capacity.getText().toString())
            ));
        }else{
            return false;
        }
    }


}
