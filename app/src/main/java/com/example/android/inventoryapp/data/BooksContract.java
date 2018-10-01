package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class BooksContract {

    //private constructor
    private BooksContract(){};

    public class  BookEntry implements BaseColumns{

        public static final String TABLE_NAME = "Books";
        public static final String COLUMN_BOOK_ID = "_id";
        public static final String COLUMN_BOOK_NAME = "Name";
        public static final String COLUMN_BOOK_PRICE = "Price";
        public static final String COLUMN_BOOK_QUANTITY = "Quatity";
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "Supplier_Name";
    }
}
