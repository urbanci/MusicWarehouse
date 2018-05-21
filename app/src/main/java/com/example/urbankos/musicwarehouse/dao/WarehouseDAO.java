package com.example.urbankos.musicwarehouse.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseNames;
import com.example.urbankos.musicwarehouse.objects.Warehouse;

import java.util.ArrayList;

public class WarehouseDAO {

    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    private Warehouse warehouse = null;
    private ArrayList<Warehouse> warehousesList = new ArrayList<>();

    public WarehouseDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private void getDatabase(){
        if(db == null){
            db = databaseHelper.getWritableDatabase();
        }
    }

    public boolean insertWarehouse(Warehouse warehouse){
        getDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;

        try {
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ADDRESS, warehouse.getAddress());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_CAPACITY, warehouse.getCapacity());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_COUNTRY, warehouse.getCountry());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_NAME, warehouse.getName());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_TOWN, warehouse.getTown());

            result = db.insertOrThrow(DatabaseNames.Entities.TABLE_WAREHOUSE, null, contentValues);

        }catch (Exception e){
            return false;

        }finally {
            db.close();

            if(result == -1){
                return false;
            }else{
                return true;
            }
        }
    }

    public ArrayList<Warehouse> getAllWarehousesRecycler(){
        getDatabase();

        try{
            Cursor res = db.rawQuery("SELECT "+DatabaseNames.Attributes.WAREHOUSE_ID+", "
                    +DatabaseNames.Attributes.WAREHOUSE_NAME+", "
                    +DatabaseNames.Attributes.WAREHOUSE_TOWN
                    +" FROM "+DatabaseNames.Entities.TABLE_WAREHOUSE, null);

            if(res.getCount() != 0){

                while(res.moveToNext()) {
                    warehouse = new Warehouse();

                    warehouse.setId(res.getInt(0));
                    warehouse.setName(res.getString(1));
                    warehouse.setTown(res.getString(2));

                    warehousesList.add(warehouse);
                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            db.close();
            return warehousesList;
        }


    }

}
