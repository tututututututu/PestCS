package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.event.AddUserEvent;

import butterknife.Bind;
import butterknife.OnClick;

public class AddUser extends BaseActivity {
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.rg_type)
    RadioGroup rg_type;
    private User user;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_user;
    }

    @OnClick({R.id.btn_save, R.id.btn_cancel})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveUser();
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void saveUser() {
        if (TextUtils.isEmpty(et_username.getText().toString().trim())) {
            getSVProgressHUD().showInfoWithStatus("请输入用户名");
            return;
        }
        user = new User();
        user.setPassWord("123456");
        int id = rg_type.getCheckedRadioButtonId();
        user.setUserGrade(id == R.id.rg_type ? "0" : "1");
        user.setUserName(et_username.getText().toString().trim());
        UserDao.saveOrUpdate(user);
        RxBus.postEvent(new AddUserEvent(user), AddUserEvent.class);
        finish();
    }
}
