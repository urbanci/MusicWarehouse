package com.example.urbankos.musicwarehouse.database_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

//    DATABASE NAMES
    public static final String DATABASE_NAME = "music_warehouse";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseNames.Queries.CREATE_TABLE_WAREHOUSE);
        db.execSQL(DatabaseNames.Queries.CREATE_TABLE_ITEM);
        db.execSQL(DatabaseNames.Queries.CREATE_TABLE_ITEM_WAREHOUSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseNames.Queries.DROP_TABLE_ITEM_WAREHOUSE);
        db.execSQL(DatabaseNames.Queries.DROP_TABLE_ITEM);
        db.execSQL(DatabaseNames.Queries.DROP_TABLE_WAREHOUSE);
        onCreate(db);
    }
}
