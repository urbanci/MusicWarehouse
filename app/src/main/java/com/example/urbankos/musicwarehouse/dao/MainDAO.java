package com.example.urbankos.musicwarehouse.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.urbankos.musicwarehouse.database_sqlite.DatabaseHelper;
import com.example.urbankos.musicwarehouse.objects.Item;

import java.util.ArrayList;

public abstract class MainDAO {
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    public MainDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    protected SQLiteDatabase getDatabase(){
        if(db == null){
            db = databaseHelper.getWritableDatabase();
        }

        return db;
    }
}
