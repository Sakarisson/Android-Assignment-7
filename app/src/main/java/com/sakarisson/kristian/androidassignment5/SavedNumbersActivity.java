package com.sakarisson.kristian.androidassignment5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SavedNumbersActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_numbers);

        toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
    }
}
