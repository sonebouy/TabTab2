package net.kineticsand.www.tabtab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bouy on 11/02/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ebasketDatabase";

    // Contacts table name
    private static final String TABLE_PRODUCTS = "products";

    // Contacts Table Columns names
    private static final String KEY_PID = "pid";
    private static final String KEY_BARCODE = "barcode";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DID = "did";
    private static final String KEY_UPDATE_DATE = "update_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_PID + " varchar(255) PRIMARY KEY," + KEY_BARCODE + " varchar(255),"
                + KEY_NAME + " varchar(255),"+ KEY_PRICE+ " varchar(255),"
                + KEY_DID + " varchar(255),"+ KEY_UPDATE_DATE+ " varchar(255)" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        // Create tables again
        onCreate(db);
    }

    // Adding new product
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PID, product.pid);
        values.put(KEY_BARCODE, product.barcode);
        values.put(KEY_NAME, product.name);
        values.put(KEY_PRICE, product.price);
        values.put(KEY_DID, product.did);
        values.put(KEY_UPDATE_DATE, product.update_date);

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single product
    public Product getProduct(String barcode) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS,
                new String[]{KEY_PID,KEY_BARCODE,KEY_NAME,KEY_PRICE,KEY_DID,KEY_UPDATE_DATE},
                KEY_BARCODE + "=?",
                new String[] { barcode },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product();
        product.pid = cursor.getString(0);
        product.barcode = cursor.getString(1);
        product.name = cursor.getString(2);
        product.price = Double.parseDouble(cursor.getString(3));
        product.did = cursor.getString(4);
        product.update_date = cursor.getString(5);

        return product;
    }

    public void clearProducts(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
    }


}
