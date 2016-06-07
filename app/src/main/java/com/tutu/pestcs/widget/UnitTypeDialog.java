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
        public void onCofirm(String cheakIndex, String name);

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

        builder.setCancelable(false);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cheakIndex = "";
                String cheakString = "";
                int cheakedID = radioGroup.getCheckedRadioButtonId();
                switch (cheakedID) {
                    case R.id.rb_1:
                        cheakIndex = "01";
                        cheakString = "餐饮店";
                        break;
                    case R.id.rb_2:
                        cheakIndex = "02";
                        cheakString = "商场超市";
                        break;
                    case R.id.rb_3:
                        cheakIndex = "03";
                        cheakString = "机关";
                        break;
                    case R.id.rb_4:
                        cheakIndex = "04";
                        cheakString = "饭店宾馆";
                        break;
                    case R.id.rb_5:
                        cheakIndex = "05";
                        cheakString = "农贸市场";
                        break;
                    case R.id.rb_6:
                        cheakIndex = "06";
                        cheakString = "机场车站";
                        break;
                    case R.id.rb_7:
                        cheakIndex = "07";
                        cheakString = "医院";
                        break;
                    case R.id.rb_8:
                        cheakIndex = "08";
                        cheakString = "学校";
                        break;
                    case R.id.rb_9:
                        cheakIndex = "09";
                        cheakString = "建筑拆迁工地";
                        break;
                    case R.id.rb_10:
                        cheakIndex = "10";
                        cheakString = "居(家)委会";
                        break;
                    case R.id.rb_11:
                        cheakIndex = "11";
                        cheakString = "食品加工企业";
                        break;
                    case R.id.rb_12:
                        cheakIndex = "12";
                        cheakString = "其他企业";
                        break;
                    case R.id.rb_13:
                        cheakIndex = "13";
                        cheakString = "道路";
                        break;
                    case R.id.rb_14:
                        cheakIndex = "14";
                        cheakString = "公园公共绿地";
                        break;
                    case R.id.rb_15:
                        cheakIndex = "15";
                        cheakString = "垃圾中转站";
                        break;
                    case R.id.rb_16:
                        cheakIndex = "16";
                        cheakString = "公共厕所";
                        break;
                    case R.id.rb_17:
                        cheakIndex = "17";
                        cheakString = "大中型水体";
                        break;
                    case R.id.rb_18:
                        cheakIndex = "18";
                        cheakString = "特殊场所";
                        break;
                }
                listener.onCofirm(cheakIndex, cheakString);
            }
        });
        return builder.create();
    }

}
