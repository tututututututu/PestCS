package com.tutu.pestcs.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tutu on 2016/4/26.
 */
public class ToastUtils {
    private static Toast toast;

    public static void showToastSafe(Context context, String text) {
        if (toast == null)
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

        toast.setText(text);
        toast.show();

    }

    public static void showToast(String text) {
        if (toast != null) {
            toast.setText(text);
            toast.show();
        }
    }

    public static void initToast(Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }
}
