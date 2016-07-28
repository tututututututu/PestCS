package com.tutu.pestcs.widget;

import android.content.Context;
import android.widget.Toast;

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

    public static void showNorToast(String msg){
        //Toast.makeText(TApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
//        SuperActivityToast.create(activity, new Style(), Style.TYPE_PROGRESS_BAR)
//                //.setButtonText("UNDO")
//                //.setButtonIconResource(R.drawable.ic_undo)
//                //.setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
//                //.setProgressBarColor(Color.WHITE)
//                .setText(msg)
//                .setDuration(Style.DURATION_LONG)
//                .setFrame(Style.FRAME_LOLLIPOP)
//                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
//                .setAnimations(Style.ANIMATIONS_FLY).show();
        SuperCustomToast stoast = SuperCustomToast.getInstance(TApplication.getInstance());
        //stoast.setDefaultBackgroundResource(TApplication.getInstance().getResources().getColor(R.color.colorPrimary));
        stoast.showSameMsg(msg, 5000);
    }
}
