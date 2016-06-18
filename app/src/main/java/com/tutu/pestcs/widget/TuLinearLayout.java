package com.tutu.pestcs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 47066 on 2016/6/18.
 */
public class TuLinearLayout extends LinearLayout {
    public TuLinearLayout(Context context) {
        super(context);
    }

    public TuLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TuLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChildEnable(boolean enable) {


        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enable);
        }
        postInvalidate();

    }
}
