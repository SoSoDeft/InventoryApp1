package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.inventoryapp.data.InventoryDbHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

public class DisplayActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;
    TextView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

         displayView = (TextView) findViewById(R.id.text_view_inventory);

        mDbHelper = new InventoryDbHelper(this);


    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu from menu_display
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.action_add_product:
                Intent intent = new Intent(DisplayActivity.this, EditorActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo(){
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
           InventoryEntry._ID,
           InventoryEntry.COLUMN_PRODUCT_NAME,
           InventoryEntry.COLUMN_PRODUCT_PRICE,
           InventoryEntry.COLUMN_PRODUCT_QUANTITY,
           InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
           InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };

        //Create cursor to represent rows and columns of data
        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try{

            displayView.setText(cursor.getCount() + " product(s) in table. \n\n");
            displayView.append(InventoryEntry._ID + " - " +
                    InventoryEntry.COLUMN_PRODUCT_NAME + " - " + InventoryEntry.COLUMN_PRODUCT_PRICE + " - "
                    + InventoryEntry.COLUMN_PRODUCT_QUANTITY + " - " + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME
                    + " - " + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER +"\n");

            //Get index of column
            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierNumberColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);



            //Loop thorough all rows that are returned in cursor
            while(cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex );
                String currentSupplier= cursor.getString(supplierNameColumnIndex);
                String currentSupplierNumber = cursor.getString(supplierNumberColumnIndex);


                //Add to textview
                displayView.append("\n" + currentId + " - " + currentName + " - " +  "$" + currentPrice
                + " - " + currentQuantity + " - " + currentSupplier + " - " + currentSupplierNumber);
            }
        } finally {
            cursor.close();
        }

    }

}
