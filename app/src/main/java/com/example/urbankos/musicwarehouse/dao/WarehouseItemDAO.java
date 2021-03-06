package com.example.urbankos.musicwarehouse.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseNames;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;

import java.util.ArrayList;
import java.util.List;

public class WarehouseItemDAO extends MainDAO{
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();


    public WarehouseItemDAO(Context context) {
        super(context);
    }

    public boolean insertWarehouseItem(ArrayList<WarehouseItem> warehouseItems, int id){
        db = getDatabase();
        ContentValues contentValues = null;
        long result = -1;

        try {
            for (WarehouseItem item : warehouseItems) {
                contentValues = new ContentValues();

                contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM, id);
                contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE, item.getId_warehouse());
                contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY, item.getQuantity());

                result = db.insertOrThrow(DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE, null, contentValues);
            }
        }catch (Exception e){

        }finally {
            db.close();

            if(result == -1){
                return false;
            }else{
                return true;
            }
        }

    }

    public boolean insertWarehouseItemFull(WarehouseItem warehouseItems){
        db = getDatabase();
        ContentValues contentValues = null;
        long result = 0;

        try {
            contentValues = new ContentValues();

            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM, warehouseItems.getId());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE, warehouseItems.getId_warehouse());
            contentValues.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY, warehouseItems.getQuantity());

            result = db.insertOrThrow(DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE, null, contentValues);

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }

    }

    public ArrayList<WarehouseItem> getQuantityesInWarehouses(String id_item){
        db = getDatabase();

        try {
            Cursor res = db.rawQuery("SELECT * FROM "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE
                    +" WHERE "+DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM+"='"+id_item+"';", null);

            if (res.getCount() != 0) {
                while (res.moveToNext()) {
                    warehouseItems.add(new WarehouseItem(
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_ID)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY)),
                            getWarehouseNames(db, res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE))
                    )));

                    Log.d("ID WAREHOUSE -> ", String.valueOf(res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE))));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            warehouseItems.clear();

        }finally {
            db.close();
            return warehouseItems;
        }
    }

    private String getWarehouseNames(SQLiteDatabase db, int id_warehouse){
        String warehouseName = "";

        try {
            Cursor res = db.rawQuery("SELECT "+DatabaseNames.Attributes.WAREHOUSE_NAME
                    +" FROM "+DatabaseNames.Entities.TABLE_WAREHOUSE
                    +" WHERE "+DatabaseNames.Attributes.WAREHOUSE_ID+"='"+id_warehouse+"';", null);

            if (res.getCount() != 0) {
                res.moveToFirst();
                warehouseName = res.getString(0);
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return warehouseName;
        }
    }

    public int updateWarehouseItem(ArrayList<WarehouseItem> warehouseItem){
        db = getDatabase();
        int success = -1;

        try {
            for (WarehouseItem wi: warehouseItem) {
                ContentValues cv = new ContentValues();
                cv.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY, wi.getQuantity());

                db.update(DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE, cv, DatabaseNames.Attributes.WAREHOUSE_ITEM_ID + "=" + wi.getId(), null);
                success = 1;
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }

    public ArrayList<WarehouseItem> getWarehouseItems(){
        db = getDatabase();
        ArrayList<WarehouseItem> items = new ArrayList<>();

        try {
            Cursor res = db.rawQuery("SELECT * FROM "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE+";", null);

            if (res.getCount() != 0) {
                while (res.moveToNext()) {
                    items.add(new WarehouseItem(
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_ID)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE)),
                            res.getInt(res.getColumnIndex(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY))));

                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return items;
        }
    }

    public int updateWarehouseItemFull(WarehouseItem warehouseItem){
        db = getDatabase();
        int success = -1;

        try {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseNames.Attributes.WAREHOUSE_ITEM_QUANTITY, warehouseItem.getQuantity());

            db.update(DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE, cv, DatabaseNames.Attributes.WAREHOUSE_ITEM_ID + "=" + warehouseItem.getId(), null);
            success = 1;


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }

}
