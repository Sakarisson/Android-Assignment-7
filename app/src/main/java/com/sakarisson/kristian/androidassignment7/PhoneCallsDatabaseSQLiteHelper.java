package com.sakarisson.kristian.androidassignment7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneCallsDatabaseSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "phone_calls_database";

    public PhoneCallsDatabaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PhoneCallsDatabase.Calls.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PhoneCallsDatabase.Calls.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertRowToCalls(String number, double latitude, double longitude) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(
                PhoneCallsDatabase.Calls.COLUMN_NUMBER,
                number
        );
        values.put(
                PhoneCallsDatabase.Calls.COLUMN_LATITUDE,
                latitude
        );
        values.put(
                PhoneCallsDatabase.Calls.COLUMN_LONGITUDE,
                longitude
        );
       long newRowId = database.insert(PhoneCallsDatabase.Calls.TABLE_NAME, null, values);
       return newRowId;
    }
}
