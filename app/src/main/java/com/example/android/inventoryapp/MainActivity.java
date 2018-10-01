package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryapp.data.BooksContract;
import com.example.android.inventoryapp.data.BooksDbHelper;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    BooksDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new BooksDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertBooks();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertBooks() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(BooksContract.BookEntry.COLUMN_BOOK_NAME, "N/A");
        values.put(BooksContract.BookEntry.COLUMN_BOOK_PRICE, 0);
        values.put(BooksContract.BookEntry.COLUMN_BOOK_QUANTITY, 0);
        values.put(BooksContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME, "N/A");

        long rowID = db.insert(BooksContract.BookEntry.TABLE_NAME, null, values);

        Log.e("rowID", "value of the new row ID is ");
    }
    public void displayDatabaseInfo() {
        //create a new database with the help of the database helper
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] projection = {
                BooksContract.BookEntry.COLUMN_BOOK_ID,
                BooksContract.BookEntry.COLUMN_BOOK_NAME,
                BooksContract.BookEntry.COLUMN_BOOK_PRICE,
                BooksContract.BookEntry.COLUMN_BOOK_QUANTITY,
                BooksContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME
        };
        Cursor cursor = db.query(BooksContract.BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayInfo = (TextView) findViewById(R.id.display_data);

        try {
            displayInfo.setText("the table contains " + cursor.getCount() + "Books \n \n");
            displayInfo.append(BooksContract.BookEntry.COLUMN_BOOK_ID + "-"
                    + BooksContract.BookEntry.COLUMN_BOOK_NAME + "+"
                    + BooksContract.BookEntry.COLUMN_BOOK_PRICE + "-"
                    + BooksContract.BookEntry.COLUMN_BOOK_QUANTITY + "-"
                    + BooksContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME + "-"
                    + "\n");

            //int values to get the column value of the data to be diaplyed
            int columnIdIndex = cursor.getColumnIndex(BooksContract.BookEntry._ID);
            int columnNameIndex = cursor.getColumnIndex(BooksContract.BookEntry.COLUMN_BOOK_NAME);
            int columnPriceIndex = cursor.getColumnIndex(BooksContract.BookEntry.COLUMN_BOOK_PRICE);
            int columnQuantityIndex = cursor.getColumnIndex(BooksContract.BookEntry.COLUMN_BOOK_QUANTITY);
            int columnSupplierNameIndex = cursor.getColumnIndex(BooksContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME);

            //add lines to the displayInfo TextView
            while (cursor.moveToNext()) {
                int columnId = cursor.getInt(columnIdIndex);
                String bookName = cursor.getString(columnNameIndex);
                int price = cursor.getInt(columnPriceIndex);
                int quantity = cursor.getInt(columnQuantityIndex);
                String supplierName = cursor.getString(columnSupplierNameIndex);

                displayInfo.append(("\n" + columnId +
                        "-" + bookName +
                        "-" + price +
                        "-" + quantity +
                        "-" + supplierName +
                        "\n"));
            }
        } finally {
            cursor.close();
        }
    }
}
