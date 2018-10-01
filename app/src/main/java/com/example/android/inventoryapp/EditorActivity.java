package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import com.example.android.inventoryapp.data.BooksDbHelper;
import com.example.android.inventoryapp.data.BooksContract;

public class EditorActivity extends AppCompatActivity{

    EditText BookNameEditText ;
    EditText PriceEditTextView;
    EditText QuantityEditTextView;
    EditText SupplierNameEditTextView;

    BooksDbHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_data_activity);


        BookNameEditText = (EditText)findViewById(R.id.product_name_edit_text);
        PriceEditTextView = (EditText)findViewById(R.id.price_edit_text_view);
        QuantityEditTextView=  (EditText)findViewById(R.id.quantity_edit_text);
        SupplierNameEditTextView = (EditText)findViewById(R.id.supplier_name_edit_text);
        mDbHelper = new BooksDbHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertBooks();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertBooks(){

        String bookName = BookNameEditText.getText().toString().trim();
        int price = Integer.parseInt(PriceEditTextView.getText().toString());
        int quantity = Integer.parseInt(QuantityEditTextView.getText().toString());
        String supplierName = SupplierNameEditTextView.getText().toString().trim();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BooksContract.BookEntry.COLUMN_BOOK_NAME,bookName);
        values.put(BooksContract.BookEntry.COLUMN_BOOK_PRICE,price);
        values.put(BooksContract.BookEntry.COLUMN_BOOK_QUANTITY,quantity);
        values.put(BooksContract.BookEntry.COLUMN_BOOK_SUPPLIER_NAME,supplierName);

        long newRowID = db.insert(BooksContract.BookEntry.TABLE_NAME,null,values);

        Log.v("Editor Activity","value of the new row id is "+newRowID);

        if(newRowID==-1){
            Toast.makeText(this,"there was error in saving new Book",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"new row of Book was created with ID"+newRowID,Toast.LENGTH_SHORT).show();
        }
    }
}
