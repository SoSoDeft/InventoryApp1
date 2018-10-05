package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();


    //Create db name
    public static final String DATABASE_NAME = "store.db";

    //Create db version number
    public static final int DATABASE_VERSION = 1;


    //Constructor to create db instance
    public InventoryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        //Create SQL state to CREATE Inventory table within store database
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";



        //Execute SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){


    }
}
