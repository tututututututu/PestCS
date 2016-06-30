package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.event.AddUserEvent;
import com.tutu.pestcs.sp.SPUtils;
import com.tutu.pestcs.widget.ToastUtils;

import org.xutils.ex.DbException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.Bind;
import butterknife.OnClick;

public class AddUser extends BaseActivity {
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.rg_type)
    RadioGroup rg_type;
    @Bind(R.id.rb_manager)
    RadioButton rbManager;
    @Bind(R.id.rb_normal)
    RadioButton rbNormal;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_cancel)
    Button btnCancel;
    @Bind(R.id.base_layout)
    LinearLayout baseLayout;
    private User user;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String permison = SPUtils.getStringSP(SPUtils.PERMISSON);
        if ("-1".equals(permison)) {
            //超级管理员
            rbManager.setVisibility(View.VISIBLE);
        } else {
            rbManager.setVisibility(View.GONE);
        }

        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = et_username.getText().toString();
                String str = stringFilter(editable.toString());
                if(!editable.equals(str)){
                    et_username.setText(str);
                    //设置新的光标所在位置
                    et_username.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        try {
            UserDao.addUser(user);
        } catch (DbException e) {
            svProgressHUD.showErrorWithStatus("用户已存在");
            return;
        }
        RxBus.postEvent(new AddUserEvent(user), AddUserEvent.class);
        finish();
    }

    //要判断是否包含特殊字符的目标字符串
    private void compileExChar(String str){
        String limitEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        if( m.find()){
            ToastUtils.showToast("不允许输入特殊符号!");
            et_username.setText(et_username.getText().toString().substring(0,et_username.getText().toString().length()-1));
        }
    }

    public static String stringFilter(String str)throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String   regEx  =  "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern   p   =   Pattern.compile(regEx);
        Matcher   m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }



}
