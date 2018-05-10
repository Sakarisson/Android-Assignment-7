package com.sakarisson.kristian.androidassignment6;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);

        String test = sp.getString("waa", "not found");
    }
}
