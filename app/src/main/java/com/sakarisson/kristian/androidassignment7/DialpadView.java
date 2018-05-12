package com.sakarisson.kristian.androidassignment7;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;

public class DialpadView extends TableLayout {
    private Context context;
    private FusedLocationProviderClient locationProvider;

    private ImageView[] buttons;
    private String[] buttonSoundNames;

    private EditText numberBox;
    private ImageButton deleteButton;
    private ImageButton callButton;

    public DialpadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void testLocation() {
        ((MainActivity)getContext()).requestLocationPermission();
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            locationProvider.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                    }
                });
        } else {
            Toast.makeText(context, "Permission to get location data was not granted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        context = getContext();
        inflate(getContext(), R.layout.dialpad_layout, this);
        locationProvider = LocationServices.getFusedLocationProviderClient(context);
        testLocation();
        numberBox = findViewById(R.id.editText);
        numberBox.setFocusable(false);
        deleteButton = findViewById(R.id.deleteButton);
        callButton = findViewById(R.id.callButton);
        initializeListeners();
    }

    private void initializeListeners() {
        buttons = new ImageView[] {
                findViewById(R.id.imageView1),
                findViewById(R.id.imageView2),
                findViewById(R.id.imageView3),
                findViewById(R.id.imageView4),
                findViewById(R.id.imageView5),
                findViewById(R.id.imageView6),
                findViewById(R.id.imageView7),
                findViewById(R.id.imageView8),
                findViewById(R.id.imageView9),
                findViewById(R.id.imageViewStar),
                findViewById(R.id.imageView0),
                findViewById(R.id.imageViewPound),
        };
        buttonSoundNames = new String[] {
                "one.mp3",
                "two.mp3",
                "three.mp3",
                "four.mp3",
                "five.mp3",
                "six.mp3",
                "seven.mp3",
                "eight.mp3",
                "nine.mp3",
                "star.mp3",
                "zero.mp3",
                "pound.mp3",
        };
        for (int i = 0; i < buttons.length; i++) {
            initializeIndividualListener(i);
        }
        View.OnClickListener callButtonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberBox.getText().toString();
                if (number.equals("")) {
                    return;
                }
                ((MainActivity)getContext()).makeCall(number);
            }
        };
        callButton.setOnClickListener(callButtonListener);

        View.OnClickListener deleteButtonClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = numberBox.getText().toString();
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                }
                numberBox.setText(text);
            }
        };
        deleteButton.setOnClickListener(deleteButtonClickListener);

        View.OnLongClickListener deleteButtonLongClickListener = new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text = "";
                numberBox.setText(text);
                return true;
            }
        };
        deleteButton.setOnLongClickListener(deleteButtonLongClickListener);
    }

    private void initializeIndividualListener(final int index) {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWasClicked(index);
            }
        };
        buttons[index].setOnClickListener(buttonListener);
    }

    public void saveNumber() {

    }

    private String getSelectedSound() {
        SharedPreferences sp = context.getSharedPreferences("SAVED_SOUNDS", Context.MODE_PRIVATE);
        String value = sp.getString("SELECTED", null);
        return value;
    }

    private void buttonWasClicked(final int index) {
        addPressToTextField(index);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            try {
                String selectedSound = getSelectedSound();
                File sound = new File(Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/dialpad/sounds/" + selectedSound + "/" + buttonSoundNames[index]).getPath());
                MediaPlayer buttonSound = MediaPlayer.create(context, Uri.fromFile(sound));
                if (buttonSound != null) {
                    buttonSound.start();
                } else {
                    throw new Exception("Could not assign sound to MediaPlayer");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // Run new thread for color switcharoo
        /*new Thread(new Runnable() {
            public void run() {
                try {
                    buttons[index].setBackgroundColor(Color.rgb(100, 100, 100));
                    Thread.sleep(150);
                    buttons[index].setBackgroundColor(Color.rgb(255, 255, 255));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();*/
    }

    private void addPressToTextField(int index) {
        String text = numberBox.getText().toString();
        String toAdd = "";
        if (index >= 0 && index <= 8) {
            toAdd += (index + 1);
        }
        switch (index) {
            case 9:
                toAdd += "*";
                break;
            case 10:
                toAdd += "0";
                break;
            case 11:
                toAdd += "#";
                break;
            default:
                break;
        }
        text += toAdd;
        numberBox.setText(text);
    }

    public void keyWasPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                buttonWasClicked(0);
                return;
            case KeyEvent.KEYCODE_2:
                buttonWasClicked(1);
                return;
            case KeyEvent.KEYCODE_3:
                buttonWasClicked(2);
                return;
            case KeyEvent.KEYCODE_4:
                buttonWasClicked(3);
                return;
            case KeyEvent.KEYCODE_5:
                buttonWasClicked(4);
                return;
            case KeyEvent.KEYCODE_6:
                buttonWasClicked(5);
                return;
            case KeyEvent.KEYCODE_7:
                buttonWasClicked(6);
                return;
            case KeyEvent.KEYCODE_8:
                buttonWasClicked(7);
                return;
            case KeyEvent.KEYCODE_9:
                buttonWasClicked(8);
                return;
            case KeyEvent.KEYCODE_STAR:
                buttonWasClicked(9);
                return;
            case KeyEvent.KEYCODE_0:
                buttonWasClicked(10);
                return;
            case KeyEvent.KEYCODE_POUND:
                buttonWasClicked(11);
                return;
            default:
                return;
        }
    }
}
