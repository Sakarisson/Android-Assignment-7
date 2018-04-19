package com.sakarisson.kristian.androidassignment4;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

public class DialpadView extends TableLayout {
    public DialpadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        inflate(getContext(), R.layout.dialpad_layout, this);
    }
}
