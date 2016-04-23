package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.comfig.ActivityJumpParams;

import butterknife.Bind;
import butterknife.OnClick;

public class EditUser extends BaseActivity {
	@Bind(R.id.tv_name)
	TextView tv_name;
	@Bind(R.id.rg_type)
	RadioGroup rg_type;


	private User user;

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		user = getIntent().getParcelableExtra(ActivityJumpParams.USER_BEAN);
		if (user == null) {
			svProgressHUD.showErrorWithStatus("系统错误");
			finish();
		}
	}

	@Override
	public void initData() {

	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_edit_user;
	}

	@OnClick({R.id.ll_back, R.id.ll_confirm, R.id.btn_reset_psw})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.ll_back:
				finish();
				break;
			case R.id.ll_confirm:
				saveChange();
				break;
			case R.id.btn_reset_psw:
				resetPsw();
				break;
		}
	}

	private void resetPsw() {
		//// TODO: 16/4/14 重置密码
		user.setPassWord("123456");
	}

	private void saveChange() {
		// TODO: 16/4/14 保存修改
		user.setUserGrade(rg_type.getCheckedRadioButtonId() == R.id.rb_normal ? "1" : "0");

	}
}
