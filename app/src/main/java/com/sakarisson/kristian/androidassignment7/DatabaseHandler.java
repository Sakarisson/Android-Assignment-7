package com.sakarisson.kristian.androidassignment7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        values.put(
                PhoneCallsDatabase.Calls.COLUMN_TIMESTAMP,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        );
        long newRowId = database.insert(PhoneCallsDatabase.Calls.TABLE_NAME, null, values);
        return newRowId;
    }

    public ArrayList<Call> getAllCallRows() {
        String[] projection = {
                PhoneCallsDatabase.Calls._ID,
                PhoneCallsDatabase.Calls.COLUMN_NUMBER,
                PhoneCallsDatabase.Calls.COLUMN_LATITUDE,
                PhoneCallsDatabase.Calls.COLUMN_LONGITUDE,
                PhoneCallsDatabase.Calls.COLUMN_TIMESTAMP
        };

        Cursor cursor = database.query(
                PhoneCallsDatabase.Calls.TABLE_NAME, projection, null, null, null, null, null
        );
        ArrayList<Call> calls = new ArrayList<>();
        if (cursor.moveToFirst() && cursor.getCount() != 0) {
            calls.add(generateCall(cursor));
            while (cursor.moveToNext()) {
                calls.add(generateCall(cursor));
            }
        }
        return calls;
    }

    private Call generateCall(Cursor cursor) {
        String number = cursor.getString(cursor.getColumnIndex(PhoneCallsDatabase.Calls.COLUMN_NUMBER));
        double latitude = cursor.getDouble(cursor.getColumnIndex(PhoneCallsDatabase.Calls.COLUMN_LATITUDE));
        double longitude = cursor.getDouble(cursor.getColumnIndex(PhoneCallsDatabase.Calls.COLUMN_LONGITUDE));
        String timestamp = cursor.getString(cursor.getColumnIndex(PhoneCallsDatabase.Calls.COLUMN_TIMESTAMP));
        return new Call(number, latitude, longitude, timestamp);
    }
}
