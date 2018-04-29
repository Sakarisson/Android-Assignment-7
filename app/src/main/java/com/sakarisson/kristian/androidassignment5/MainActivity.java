package com.sakarisson.kristian.androidassignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DialpadView dialpadView;
    private Intent savedNumbersIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedNumbersIntent = new Intent(this, SavedNumbersActivity.class);

        setContentView(R.layout.activity_main);

        dialpadView = findViewById(R.id.dialpadView);
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        dialpadView.keyWasPressed(keyCode);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_call_list:
                startActivity(savedNumbersIntent);
                break;
            case R.id.save_button:
                dialpadView.saveNumber();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
