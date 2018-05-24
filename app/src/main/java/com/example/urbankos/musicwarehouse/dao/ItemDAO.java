package com.example.urbankos.musicwarehouse.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseNames;
import com.example.urbankos.musicwarehouse.objects.Item;

import java.util.ArrayList;

public class ItemDAO {

    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<Item> itemsList = new ArrayList<>();

    public ItemDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private void getDatabase(){
        if(db == null){
            db = databaseHelper.getWritableDatabase();
        }
    }

    public int insertItem(Item item){
        getDatabase();
        long result = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseNames.Attributes.ITEM_NAME, item.getName());
        contentValues.put(DatabaseNames.Attributes.ITEM_FIRM, item.getFirm());
        contentValues.put(DatabaseNames.Attributes.ITEM_PRICE, item.getPrice());
        contentValues.put(DatabaseNames.Attributes.ITEM_CATEGORY, item.getCategory());
        contentValues.put(DatabaseNames.Attributes.ITEM_QUANTITY, item.getQuantity());

        try{
            db.insertOrThrow(DatabaseNames.Entities.TABLE_ITEM, null, contentValues);
        }catch (Exception e){
            result = -1;
        }finally {
            if(result == -1){
                return -1;
            }else{
                Cursor c = db.rawQuery("SELECT last_insert_rowid()", null);
                c.moveToFirst();
                db.close();
                return c.getInt(0);
            }
        }

    }

    public ArrayList<String> getCategoriesOfItemsInWarehouse(String id_warehouse){
        getDatabase();

        try {
            Cursor res = db.rawQuery("SELECT DISTINCT item." + DatabaseNames.Attributes.ITEM_CATEGORY
                    + " FROM " + DatabaseNames.Entities.TABLE_ITEM+", "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE
                    + " WHERE "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE+"."+DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM
                    +"="+DatabaseNames.Entities.TABLE_ITEM+"."+DatabaseNames.Attributes.ITEM_ID
                    + " AND "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE+"."+DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE
                    +"="+id_warehouse+";", null);

            if (res.getCount() != 0) {
                while (res.moveToNext()) {
                    categoryList.add(res.getString(0));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
            return categoryList;
        }

    }

    public ArrayList<Item> getItemNameFirm(String category, String id_warehouse){
        getDatabase();

        try {
            Cursor res = db.rawQuery("SELECT item." + DatabaseNames.Attributes.ITEM_ID
                    +", item."+DatabaseNames.Attributes.ITEM_NAME
                    +", item."+DatabaseNames.Attributes.ITEM_FIRM
                    +" FROM " + DatabaseNames.Entities.TABLE_ITEM+", "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE
                    +" WHERE "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE+"."+DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_ITEM
                    +"="+DatabaseNames.Entities.TABLE_ITEM+"."+DatabaseNames.Attributes.ITEM_ID
                    +" AND "+DatabaseNames.Entities.TABLE_ITEM_WAREHOUSE+"."+DatabaseNames.Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE+"="+id_warehouse
                    +" AND "+DatabaseNames.Entities.TABLE_ITEM+"."+DatabaseNames.Attributes.ITEM_CATEGORY+"='"+category+"';", null);

            if (res.getCount() != 0) {
                while (res.moveToNext()) {
                    itemsList.add(new Item(
                        Integer.valueOf(res.getString(0)),
                        res.getString(1),
                        res.getString(2)
                    ));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
            return itemsList;
        }

    }

    public Item getItem(String id_item){
        getDatabase();
        Item item = null;

        try {
            Cursor res = db.rawQuery("SELECT * FROM "+DatabaseNames.Entities.TABLE_ITEM
                    +" WHERE "+DatabaseNames.Attributes.ITEM_ID+"="+id_item+";", null);

            if (res.getCount() != 0) {
                res.moveToFirst();
                item = new Item(
                        Integer.valueOf(res.getString(0)),
                        res.getString(1),
                        res.getString(2),
                        Double.valueOf(res.getString(4)),
                        res.getString(3),
                        Integer.valueOf(res.getString(5)
                ));

            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return item;
        }
    }

    public int updateItem(Item item, String id_item){
        getDatabase();
        int success = -1;

        try {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseNames.Attributes.ITEM_CATEGORY, item.getCategory());
            cv.put(DatabaseNames.Attributes.ITEM_QUANTITY, item.getQuantity());
            cv.put(DatabaseNames.Attributes.ITEM_PRICE, item.getPrice());
            cv.put(DatabaseNames.Attributes.ITEM_NAME, item.getName());
            cv.put(DatabaseNames.Attributes.ITEM_FIRM, item.getFirm());

            db.update(DatabaseNames.Entities.TABLE_ITEM, cv, DatabaseNames.Attributes.ITEM_ID+"="+id_item, null);
            success = 1;

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return success;
        }
    }

}
