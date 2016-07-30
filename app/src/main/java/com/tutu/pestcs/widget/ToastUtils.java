package com.tutu.pestcs.widget;

import android.content.Context;
import android.widget.Toast;

import com.github.pierry.simpletoast.SimpleToast;
import com.tutu.pestcs.app.TApplication;

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

    public static void showOKToast(String msg){
        SimpleToast.ok(TApplication.getInstance(), msg);
    }

    public static void showErrorToast(String msg){
        SimpleToast.error(TApplication.getInstance(), msg);
    }
    public static void showInfoToast(String msg){
        SimpleToast.info(TApplication.getInstance(), msg);
    }

    public static void showWarningToast(String msg){
        SimpleToast.warning(TApplication.getInstance(), msg);
    }

    public static void showMutedToast(String msg){
        SimpleToast.muted(TApplication.getInstance(), msg);
    }

}
