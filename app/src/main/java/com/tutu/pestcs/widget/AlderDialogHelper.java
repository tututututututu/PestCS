package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by 47066 on 2016/6/27.
 */
public class AlderDialogHelper {

    public static void showAlertDialog(Context context, String msg, DialogInterface.OnClickListener okLister,
                                       DialogInterface.OnClickListener cancleLister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage(msg)
                .setNegativeButton("取消", cancleLister)
                .setPositiveButton("确定", okLister).setCancelable(false).create().show();
    }

    public static void showTipsAlertDialot(Context contex, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(contex);
        builder.setTitle("警告")
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("去修正", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create().show();

    }
}
