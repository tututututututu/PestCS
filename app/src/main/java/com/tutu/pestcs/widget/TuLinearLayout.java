package com.tutu.pestcs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

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

    public void setChildEnable(ViewGroup viewGroup, boolean enable) {
        if (viewGroup == null) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof EditText) {
                EditText newDtv = (EditText) view;
                newDtv.setEnabled(enable);
            } else if(view instanceof RadioButton){
                RadioButton newDtv = (RadioButton) view;
                newDtv.setEnabled(enable);
            }
            else if (view instanceof ViewGroup) {
                // 若是布局控件（LinearLayout或RelativeLayout）,继续查询子View
                this.setChildEnable((ViewGroup) view, enable);
            }
        }
    }
}
