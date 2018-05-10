package com.sakarisson.kristian.androidassignment6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences sp;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        rg = findViewById(R.id.radiogroup);
        RadioButton rb = new RadioButton(this);
        rb.setText("This option was added programmically!");
        rg.addView(rb);
        rb.setChecked(true);
        sp = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);

        String test = sp.getString("waa", "not found");
    }
}
