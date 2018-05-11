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
    private String selectedSound = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        rg = findViewById(R.id.radiogroup);
        sp = getSharedPreferences("SAVED_SOUNDS", Context.MODE_PRIVATE);
        ArrayList<File> folders = getSoundFolders();
        selectedSound = getSelectedSound();
        for (File directory : folders) {
            String dirName = directory.getName();
            boolean checked = false;
            if (dirName.equals(selectedSound)) {
                checked = true;
            }
            generateRadioButton(directory, checked);
        }
    }

    private String getSelectedSound() {
        String value = sp.getString("SELECTED", null);
        return value;
    }

    private void setSelectedSound(String sound) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SELECTED", sound);
        editor.commit();
    }

    private void generateRadioButton(File dir, boolean checked) {
        RadioButton rb = new RadioButton(this);
        rb.setText(dir.getName());
        rg.addView(rb);
        rb.setChecked(checked);
    }

    private ArrayList<File> getSoundFolders() {
        String relativePath = "/dialpad/sounds";
        File directory;
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
