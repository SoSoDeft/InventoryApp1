package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class InventoryContract {

    //Create private constructor so cannot be instantiated
    private InventoryContract(){}

    //Inner class that contain InventoryEntry
    public static final class InventoryEntry implements BaseColumns{

        public static final String TABLE_NAME = "inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier_number";


    }
}
