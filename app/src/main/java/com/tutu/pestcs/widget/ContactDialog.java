package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by 47066 on 2016/7/2.
 */
public class ContactDialog {
    public static void show(Context context,String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("错误")
                .setMessage("非常抱歉发生了bug,请将此界面截图发送到470666774@qq.com邮箱,我将根据此截图定位修复bug.谢谢你的配合!\n" + msg);
        builder.setPositiveButton("关闭对话框", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
