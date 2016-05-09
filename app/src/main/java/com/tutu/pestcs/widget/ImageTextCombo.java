package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutu.pestcs.R;


public class ImageTextCombo extends LinearLayout {
    private ImageView iv;
    private TextView tv;
    private Drawable mDrawable;
    private String mText;

    public ImageTextCombo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        initView();
    }

    public ImageTextCombo(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView();
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageTextCombo);
        CharSequence text = a.getText(R.styleable.ImageTextCombo_text);
        if (text != null) {
            mText = text.toString();
            setText(mText);
        }
        Drawable drawable = a.getDrawable(R.styleable.ImageTextCombo_src);
        if (drawable != null) {
            mDrawable = drawable;
            setDrawable(mDrawable);
        }
        a.recycle();

    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        iv.setImageDrawable(mDrawable);
    }

    public void setText(String text) {
        mText = text;
        tv.setText(mText);
    }

    public ImageTextCombo(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.image_text_combo, this);
        iv = (ImageView) view.findViewById(R.id.iv_img);
        tv = (TextView) view.findViewById(R.id.tv_text);
    }

    public ImageView getImageView() {
        return iv;
    }

}
