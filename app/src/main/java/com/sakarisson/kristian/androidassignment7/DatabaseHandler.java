package com.sakarisson.kristian.androidassignment7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;

public final class DatabaseHandler {
    private SQLiteDatabase database;

    public DatabaseHandler(Context context) {
        database = new PhoneCallsDatabaseSQLiteHelper(context).getWritableDatabase();
    }

    public long insertRowToCalls(String number, double latitude, double longitude) {
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
        getAllCallRows();
        return newRowId;
    }

    public void getAllCallRows() {
        String[] projection = {
                PhoneCallsDatabase.Calls._ID,
                PhoneCallsDatabase.Calls.COLUMN_NUMBER,
                PhoneCallsDatabase.Calls.COLUMN_LATITUDE,
                PhoneCallsDatabase.Calls.COLUMN_LONGITUDE
        };

        Cursor cursor = database.query(
                PhoneCallsDatabase.Calls.TABLE_NAME, projection, null, null, null, null, null
        );
        String[] columnNames = cursor.getColumnNames();
        if (cursor.moveToFirst()) {
            String testNumber = cursor.getString(cursor.getColumnIndex("number"));
        }
    }
}
