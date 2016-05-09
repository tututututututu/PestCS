package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.event.AddUserEvent;
import com.tutu.pestcs.sp.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePsw extends BaseActivity {

    @Bind(R.id.et_new_psw)
    EditText et_new_psw;
    @Bind(R.id.et_new_pswc)
    EditText et_new_pswc;
    @Bind(R.id.tv_username)
    TextView username;

    private User user;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        user = UserDao.queryByName(SPUtils.getStringSP(SPUtils.USERNAME));
        if (user == null) {
            svProgressHUD.showErrorWithStatus("系统错误");
            et_new_psw.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
            return;
        }
        username.setText(user.getUserName());
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_psw;
    }


    @OnClick({R.id.ll_back, R.id.ll_confirm})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_confirm:
                saveChange();
                break;
        }
    }

    private void saveChange() {
        String new1 = et_new_psw.getText().toString().trim();
        String new2 = et_new_pswc.getText().toString().trim();


        if (TextUtils.isEmpty(new1) || TextUtils.isEmpty(new2)) {
            svProgressHUD.showErrorWithStatus("请输入密码");
        }


        if (!new1.equals(new2)) {
            svProgressHUD.showErrorWithStatus("两次密码不一致");
        }

        user.setPassWord(new2);
        UserDao.saveOrUpdate(user);
        RxBus.postEvent(new AddUserEvent(user), AddUserEvent.class);
        finish();
    }
}
