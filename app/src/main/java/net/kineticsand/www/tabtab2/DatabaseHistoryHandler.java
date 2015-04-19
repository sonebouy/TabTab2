package net.kineticsand.www.tabtab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nut on 30/3/2558.
 */
public class DatabaseHistoryHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ebasketDatabase2";

    // Contacts table name
    private static final String TABLE_HISTORIES = "histories";

    // Contacts Table Columns names
    private static final String KEY_HID = "hid";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_INSERT_DATE = "insert_date";

    public DatabaseHistoryHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_HISTORIES + "("
                + KEY_HID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " varchar(255),"+ KEY_PRICE+ " varchar(255),"
                + KEY_AMOUNT + " varchar(255),"+ KEY_INSERT_DATE+ " varchar(255)" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORIES);
        // Create tables again
        onCreate(db);
    }

    public void addHistory(History history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, history.name);
        values.put(KEY_PRICE, history.price);
        values.put(KEY_AMOUNT, history.amount);
        values.put(KEY_INSERT_DATE, history.insertDate);

        // Inserting Row
        db.insert(TABLE_HISTORIES, null, values);
        db.close();
    }

    public List<History> getHistories(String targetDate) {
        List<History> histories = new ArrayList<History>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORIES,
                new String[]{KEY_NAME,KEY_PRICE,KEY_AMOUNT},
                KEY_INSERT_DATE + "=?",
                new String[] { targetDate },
                null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for(int i=0;i<count;i++) {
                cursor.moveToPosition(i);
                History history = new History();
                history.name = cursor.getString(0);
                history.price = cursor.getString(1);
                history.amount = cursor.getString(2);
                history.insertDate = targetDate;
                histories.add(history);
                cursor.moveToNext();
            }
        }

        for(int i=0;i<histories.size();i++) {
            for(int j=0;j<i;j++)
            {
                if(histories.get(i).name.equals(histories.get(j).name) && histories.get(i).price.equals(histories.get(j).price)) {
                    histories.get(j).amount = (Integer.parseInt(histories.get(j).amount) + Integer.parseInt(histories.get(i).amount))+"";
                    histories.remove(i);
                    i--;
                    break;
                }
            }
        }

        return histories;
    }

    public int getHistoriesCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HISTORIES,new String[]{KEY_NAME,KEY_PRICE,KEY_AMOUNT},null,null,null, null, null, null);
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    public void clearHistorys(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_HISTORIES, null, null);
    }

}
