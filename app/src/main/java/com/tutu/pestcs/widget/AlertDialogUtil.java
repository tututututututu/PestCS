package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;

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
}
