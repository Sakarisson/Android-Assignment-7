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
    private Toolbar toolbar;
    private Intent savedNumbersIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedNumbersIntent = new Intent(this, SavedNumbersActivity.class);

        setContentView(R.layout.activity_main);

        dialpadView = findViewById(R.id.dialpadView);
        toolbar = findViewById(R.id.toolbar_menu);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_call_list) {
            startActivity(savedNumbersIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
