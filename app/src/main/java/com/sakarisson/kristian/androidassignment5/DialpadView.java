package com.sakarisson.kristian.androidassignment5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;

public class DialpadView extends TableLayout {
    private Context context;

    private ImageView[] buttons;
    private int[] rawButtonSounds;

    private EditText numberBox;
    private ImageButton deleteButton;
    private ImageButton callButton;

    public DialpadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        context = getContext();
        inflate(getContext(), R.layout.dialpad_layout, this);
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
        rawButtonSounds = new int[] {
                R.raw.one,
                R.raw.two,
                R.raw.three,
                R.raw.four,
                R.raw.five,
                R.raw.six,
                R.raw.seven,
                R.raw.eight,
                R.raw.nine,
                R.raw.star,
                R.raw.zero,
                R.raw.pound,
        };
        for (int i = 0; i < buttons.length; i++) {
            initializeIndividualListener(i);
        }
        View.OnClickListener callButtonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberBox.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                context.startActivity(intent);
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

    private void buttonWasClicked(final int index) {
        addPressToTextField(index);
        final MediaPlayer buttonSound = MediaPlayer.create(context, rawButtonSounds[index]);
        // Run new thread for color switcharoo
        new Thread(new Runnable() {
            public void run() {
                try {
                    buttonSound.start();
                    buttons[index].setBackgroundColor(Color.rgb(100, 100, 100));
                    Thread.sleep(150);
                    buttons[index].setBackgroundColor(Color.rgb(255, 255, 255));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();
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
