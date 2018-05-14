package com.sakarisson.kristian.androidassignment7;

import android.provider.BaseColumns;

public final class PhoneCallsDatabase {
    private PhoneCallsDatabase() {

    }

    public static class Calls implements BaseColumns {
        public static final String TABLE_NAME = "calls";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_TIMESTAMP = "timestamp";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL, " +
                COLUMN_TIMESTAMP + " DATETIME" + ")";
    }
}
