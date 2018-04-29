package com.sakarisson.kristian.androidassignment5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SavedNumbersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView savedNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_numbers);
        savedNumbers = findViewById(R.id.savedNumbers);
        savedNumbers.setMovementMethod(new ScrollingMovementMethod());

        toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
        drawSavedNumbers();
    }

    private void drawSavedNumbers() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String currentNumbers = sp.getString(getString(R.string.saved_numbers_key), "");
        savedNumbers.setText(currentNumbers);
    }
}
