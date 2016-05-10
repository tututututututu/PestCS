package com.tutu.pestcs.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.tutu.pestcs.R;


public class TabRadioButton extends RadioButton {

    private static final int[] STATE_HASNEW = {R.attr.hasNew};

    private boolean hasNewMsg = false;
    private OnToggleListener l;

    public interface OnToggleListener {
        boolean canToggle(long id);
    }

    public void setToggleListener(OnToggleListener ll) {
        l = ll;
    }

    public TabRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TabRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabRadioButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setHasNew(boolean set) {

        hasNewMsg = set;
        if (getBackground() != null) {
            getBackground().invalidateSelf();
        }
        refreshDrawableState();
        invalidate();
    }

    @Override
    public void toggle() {
        if (l != null && !l.canToggle(getId())) {
            return;
        }
        super.toggle();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        // TODO Auto-generated method stub

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (hasNewMsg) {
            mergeDrawableStates(drawableState, STATE_HASNEW);
        }

        return drawableState;
    }

}
