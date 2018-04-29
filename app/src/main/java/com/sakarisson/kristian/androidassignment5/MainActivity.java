package com.sakarisson.kristian.androidassignment5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private DialpadView dialpadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialpadView = findViewById(R.id.dialpadView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        dialpadView.keyWasPressed(keyCode);
        return true;
    }
}
