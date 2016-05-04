package com.tutu.pestcs.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.bean.UnitsType;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.widget.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangeFocusType extends BaseActivity {
    @Bind(R.id.rb1)
    CheckBox rb1;
    @Bind(R.id.rb2)
    CheckBox rb2;
    @Bind(R.id.rb3)
    CheckBox rb3;
    @Bind(R.id.rb4)
    CheckBox rb4;
    @Bind(R.id.rb5)
    CheckBox rb5;
    @Bind(R.id.rb6)
    CheckBox rb6;
    @Bind(R.id.rb7)
    CheckBox rb7;
    @Bind(R.id.rb8)
    CheckBox rb8;
    @Bind(R.id.rb9)
    CheckBox rb9;
    @Bind(R.id.rb10)
    CheckBox rb10;
    @Bind(R.id.rb11)
    CheckBox rb11;
    @Bind(R.id.rb12)
    CheckBox rb12;
    @Bind(R.id.rb13)
    CheckBox rb13;
    @Bind(R.id.rb14)
    CheckBox rb14;
    @Bind(R.id.rb15)
    CheckBox rb15;
    @Bind(R.id.rb16)
    CheckBox rb16;
    @Bind(R.id.rb17)
    CheckBox rb17;
    @Bind(R.id.rb18)
    CheckBox rb18;

    private List<ExtendSortUnitBean> data;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        readTypeData();
    }

    private void readTypeData() {
        Tasks.executeInBackground(this, new BackgroundWork<List<ExtendSortUnitBean>>() {
            @Override
            public List<ExtendSortUnitBean> doInBackground() throws Exception {
                return ExtendUnitDao.queryAll();
            }
        }, new Completion<List<ExtendSortUnitBean>>() {
            @Override
            public void onSuccess(Context context, List<ExtendSortUnitBean> result) {
                data = result;
                updateUI();
            }

            @Override
            public void onError(Context context, Exception e) {
                ToastUtils.showToast("读取数据失败");
                finish();
            }
        });
    }

    private void updateUI() {
        rb1.setChecked(data.get(0).iskeyClass());
        rb2.setChecked(data.get(1).iskeyClass());
        rb3.setChecked(data.get(2).iskeyClass());
        rb4.setChecked(data.get(3).iskeyClass());
        rb5.setChecked(data.get(4).iskeyClass());
        rb6.setChecked(data.get(5).iskeyClass());
        rb7.setChecked(data.get(6).iskeyClass());
        rb8.setChecked(data.get(7).iskeyClass());
        rb9.setChecked(data.get(8).iskeyClass());
        rb10.setChecked(data.get(9).iskeyClass());
        rb11.setChecked(data.get(10).iskeyClass());
        rb12.setChecked(data.get(11).iskeyClass());
        rb13.setChecked(data.get(12).iskeyClass());
        rb14.setChecked(data.get(13).iskeyClass());
        rb15.setChecked(data.get(14).iskeyClass());
        rb16.setChecked(data.get(15).iskeyClass());
        rb17.setChecked(data.get(16).iskeyClass());
        rb18.setChecked(data.get(17).iskeyClass());
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_focus_type;
    }

    @OnClick({R.id.ll_back, R.id.ll_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_confirm:
                save();
                break;
        }
    }

    private void save() {
        svProgressHUD.showWithStatus("保存中...");
        data.get(0).setIskeyClass(rb1.isChecked());
        data.get(1).setIskeyClass(rb2.isChecked());
        data.get(2).setIskeyClass(rb3.isChecked());
        data.get(3).setIskeyClass(rb4.isChecked());
        data.get(4).setIskeyClass(rb5.isChecked());
        data.get(5).setIskeyClass(rb6.isChecked());
        data.get(6).setIskeyClass(rb7.isChecked());
        data.get(7).setIskeyClass(rb8.isChecked());
        data.get(8).setIskeyClass(rb9.isChecked());
        data.get(9).setIskeyClass(rb10.isChecked());
        data.get(10).setIskeyClass(rb11.isChecked());
        data.get(11).setIskeyClass(rb12.isChecked());
        data.get(12).setIskeyClass(rb13.isChecked());
        data.get(13).setIskeyClass(rb14.isChecked());
        data.get(14).setIskeyClass(rb15.isChecked());
        data.get(15).setIskeyClass(rb16.isChecked());
        data.get(16).setIskeyClass(rb17.isChecked());
        data.get(17).setIskeyClass(rb18.isChecked());

        Tasks.executeInBackground(this, new BackgroundWork<Void>() {
            @Override
            public Void doInBackground() throws Exception {
                for (ExtendSortUnitBean bean : data) {
                    ExtendUnitDao.upData(bean);
                }


                return null;
            }
        }, new Completion<Void>() {
            @Override
            public void onSuccess(Context context, Void result) {
                svProgressHUD.dismissImmediately();
                finish();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
