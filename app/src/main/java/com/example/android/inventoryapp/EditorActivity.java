package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryDbHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

public class EditorActivity extends AppCompatActivity {

    private TextView productName;
    private EditText productNameEdit;

    private TextView price;
    private EditText priceEdit;

    private TextView quantity;
    private EditText quantityEdit;

    private TextView supplierName;
    private EditText supplierNameEdit;

    private TextView supplierPhone;
    private EditText supplierPhoneEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Find all views
        productName = (TextView) findViewById(R.id.product_name_text);
        productNameEdit = (EditText) findViewById(R.id.product_name_edit_text);
        price = (TextView) findViewById(R.id.price_text);
        priceEdit = (EditText) findViewById(R.id.price_edit_text);
        quantity = (TextView) findViewById(R.id.quantity_text);
        quantityEdit = (EditText) findViewById(R.id.quantity_edit_text);
        supplierName = (TextView) findViewById(R.id.supplier_text);
        supplierNameEdit = (EditText) findViewById(R.id.supplier_edit_text);
        supplierPhone = (TextView) findViewById(R.id.supplier_phone_text);
        supplierPhoneEdit = (EditText) findViewById(R.id.supplier_phone_edit_text);

    }


    private void insertProduct(){
        String nameString = productNameEdit.getText().toString().trim();
        String priceString = priceEdit.getText().toString().trim();
        String quantityString = quantityEdit.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierString = supplierNameEdit.getText().toString().trim();
        String supplierPhoneString = supplierPhoneEdit.getText().toString().trim();

        //Create db instance
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);

        //Get db in writable mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create ContentValues object
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, priceString);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierString);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER,supplierPhoneString);

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

        if(newRowId == -1){
            Toast.makeText(this, "Error saving inventory ",Toast.LENGTH_SHORT).show();;
        } else{
            Toast.makeText(this, "Inventory saved with row id:" + newRowId, Toast.LENGTH_SHORT).show();
        }


    }

    private void deleteProduct(){
        productNameEdit.getText().clear();
        priceEdit.getText().clear();
        quantityEdit.getText().clear();
        supplierNameEdit.getText().clear();
        supplierPhoneEdit.getText().clear();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.action_save:
                insertProduct();
                finish();
                return true;

            case R.id.action_delete:
                deleteProduct();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
