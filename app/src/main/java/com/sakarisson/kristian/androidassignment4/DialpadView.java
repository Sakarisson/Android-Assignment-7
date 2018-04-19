package com.sakarisson.kristian.androidassignment4;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

public class DialpadView extends TableLayout {
    public DialpadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        inflate(getContext(), R.layout.dialpad_layout, this);
        initializeListeners();
    }

    private void initializeListeners() {
        ImageView[] buttons = {
                findViewById(R.id.imageView0),
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
                findViewById(R.id.imageViewPound),
        };
        for (ImageView button : buttons) {
            initializeIndividualListener(button);
        }
    }

    private void initializeIndividualListener(final ImageView button) {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWasClicked(button);
            }
        };
        button.setOnClickListener(buttonListener);
    }

    private void buttonWasClicked(final ImageView button) {
        // Run new thread for color switcharoo
        new Thread(new Runnable() {
            public void run() {
                try {
                    button.setBackgroundColor(Color.rgb(100, 100, 100));
                    Thread.sleep(150);
                    button.setBackgroundColor(Color.rgb(255, 255, 255));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
