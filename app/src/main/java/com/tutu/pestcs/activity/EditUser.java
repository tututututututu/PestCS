package com.tutu.pestcs.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.event.UserEidteEvent;
import com.tutu.pestcs.sp.SPUtils;
import com.tutu.pestcs.widget.ToastUtils;

import org.xutils.common.util.LogUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class EditUser extends BaseActivity {
    @Bind(R.id.tv_username)
    TextView tv_name;
    @Bind(R.id.rg_type)
    RadioGroup rg_type;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.ll_confrim)
    LinearLayout llConfrim;
    @Bind(R.id.rb_manager)
    RadioButton rbManager;
    @Bind(R.id.rb_normal)
    RadioButton rbNormal;
    @Bind(R.id.btn_reset_psw)
    Button btnResetPsw;
    @Bind(R.id.btn_delete)
    Button btnDelete;
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
            rbManager.setEnabled(true);
        } else {
            rbManager.setEnabled(false);
        }

        user = getIntent().getParcelableExtra(ActivityJumpParams.USER_BEAN);
        LogUtil.e("EditUser user=" + user.toString());
        if (user == null) {
            svProgressHUD.showErrorWithStatus("系统错误");
            finish();
        }

        tv_name.setText(user.getUserName());

        if (user.getUserGrade().equals("0")) {
            //管理员
            rg_type.check(R.id.rb_manager);
        } else {
            //普通用户
            rg_type.check(R.id.rb_normal);
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_edit_user;
    }

    @OnClick({R.id.ll_back, R.id.ll_confrim, R.id.btn_reset_psw, R.id.btn_delete})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_reset_psw:
                resetPsw();
                break;
            case R.id.btn_delete:
                delteUser();
                break;
        }
    }

    private void delteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("确定删除该用户吗?");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                UserDao.delete(user);
                ToastUtils.showToast("删除成功");
                RxBus.postEvent(new UserEidteEvent(user), UserEidteEvent.class);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void resetPsw() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定重置密码为\"123456\"吗?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        user.setPassWord("123456");
                        saveChange();
                    }
                });
        builder.create().show();
    }

    private void saveChange() {
        int cheakid = rg_type.getCheckedRadioButtonId();
        if (cheakid == R.id.rb_normal) {
            user.setUserGrade("1");
        } else {
            user.setUserGrade("0");
        }

        RxBus.postEvent(new UserEidteEvent(user), UserEidteEvent.class);
        LogUtil.e("准备修改的用户=" + user.toString());
        UserDao.saveOrUpdate(user);
        ToastUtils.showToast("修改成功");
        finish();
    }

}
