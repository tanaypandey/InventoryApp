package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.BooksContract.BookEntry;

public class BooksDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = BooksDbHelper.class.getSimpleName();

    //name of the database
    private static final String DATABASE_NAME = "Books.db";

    /*version of the database this is to ensure that new database gets
     created everytime the old one is destoryed in order to show new
     and updated information
      */
    private static final int DATABASE_VERSION = 1;

    //constructor for creating a database helper instance to create a new database
    public BooksDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreate method is overriden in order ot create new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL commands to create new table with the following attreibute

//        String SQL_CREATE_NEW_TABLE = "CREATE TABLE " + BookEntry.TABLE_NAME + " ("
//                + BookEntry.COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + BookEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
//                +BookEntry.COLUMN_BOOK_PRICE + " INTEGER DEFAULT 0, "
//                +BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
//                +BookEntry.COLUMN_BOOK_SUPPLIER_NAME + "TEXT"
//                + " )";
//
//        db.execSQL(SQL_CREATE_NEW_TABLE);

        String SQL_CREATE_BookEntry_TABLE =  "CREATE TABLE " + BookEntry.TABLE_NAME + " ("
                + BookEntry.COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + BookEntry.COLUMN_BOOK_PRICE + " INTEGER, "
                + BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
                + BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " TEXT NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_BookEntry_TABLE);

    }

    //onUpgrade() method is used to update the database whenever new information needs to be entered
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //nothing to be done here because the table is not updataing
    }
}
