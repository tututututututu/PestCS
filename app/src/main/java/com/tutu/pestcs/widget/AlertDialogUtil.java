package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancelWithDialog;

/**
 * Created by tutu on 16/4/26.
 */
public class AlertDialogUtil {
    public static void showDialog(Context context, final IOnConfirmOrCancel lister) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lister.OnConfrim();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                lister.OnCancel();
            }
        });
        builder.create().show();
    }

    public static void showDialog1(Context context, final IOnConfirmOrCancelWithDialog lister) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("退出前，是否保存当前录入的数据？");

        builder.setNegativeButton("不保持", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                lister.OnCancel(dialog);
            }
        });
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lister.OnConfrim(dialog);
            }
        });

        builder.create().show();
    }
}
