package com.tutu.pestcs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.tutu.pestcs.R;

/**
 * Created by tutu on 16/4/17.
 */
public class UnitTypeDialog {

	public interface onDialogClick {
		public void onCofirm(int cheakIndex);

		public void onCancle();
	}

	public static AlertDialog getInstace(Context context, onDialogClick listener) {

		return initDialog(context, listener);
	}

	public static AlertDialog initDialog(Context context, final onDialogClick listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		int cheakIndex = -1;

		View view = LayoutInflater.from(context).inflate(R.layout.unit_type_dialog, null);
		final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_type);


		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int cheakIndex = -1;
				int cheakedID = radioGroup.getCheckedRadioButtonId();
				switch (cheakedID) {
					case R.id.rb_1:
						cheakIndex = 1;
						break;
					case R.id.rb_2:
						cheakIndex = 2;
						break;
					case R.id.rb_3:
						cheakIndex = 3;
						break;
					case R.id.rb_4:
						cheakIndex = 4;
						break;
					case R.id.rb_5:
						cheakIndex = 5;
						break;
					case R.id.rb_6:
						cheakIndex = 6;
						break;
					case R.id.rb_7:
						cheakIndex = 7;
						break;
					case R.id.rb_8:
						cheakIndex = 8;
						break;
					case R.id.rb_9:
						cheakIndex = 9;
						break;
					case R.id.rb_10:
						cheakIndex = 10;
						break;
					case R.id.rb_11:
						cheakIndex = 11;
						break;
					case R.id.rb_12:
						cheakIndex = 12;
						break;
					case R.id.rb_13:
						cheakIndex = 13;
						break;
					case R.id.rb_14:
						cheakIndex = 14;
						break;
					case R.id.rb_15:
						cheakIndex = 15;
						break;
					case R.id.rb_16:
						cheakIndex = 16;
						break;
					case R.id.rb_17:
						cheakIndex = 17;
						break;
					case R.id.rb_18:
						cheakIndex = 18;
						break;
				}
				listener.onCofirm(cheakIndex);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onCancle();
				dialog.dismiss();
			}
		});
		return builder.create();
	}

}
