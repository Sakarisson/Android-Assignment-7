package com.sakarisson.kristian.androidassignment6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private RadioGroup rg;
    private String relativePath = "/dialpad/sounds";
    private File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        rg = findViewById(R.id.radiogroup);
        ArrayList<File> folders = getSoundFolders();
        for (File directory : folders) {
            generateRadioButton(directory, false);
        }
        sp = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        String test = sp.getString("waa", "not found");
    }

    private void generateRadioButton(File dir, boolean checked) {
        RadioButton rb = new RadioButton(this);
        rb.setText(dir.getName());
        rg.addView(rb);
        rb.setChecked(checked);
    }

    private ArrayList<File> getSoundFolders() {
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + relativePath;
        directory = new File(fullPath);
        ArrayList<File> files = new ArrayList<File>();
        for (File inFile : directory.listFiles()) {
            if (inFile.isDirectory()) {
                files.add(inFile);
            }
        }
        return files;
    }
}
