package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.sp.SPUtils;

import org.xutils.common.util.LogUtil;

import butterknife.Bind;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {
	@Bind(R.id.et_username)
	AutoCompleteTextView etUsername;
	@Bind(R.id.til_username)
	TextInputLayout tilUsername;
	@Bind(R.id.et_pwd)
	EditText etPwd;
	@Bind(R.id.til_psw)
	TextInputLayout tilPsw;

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
		return R.layout.activity_login;
	}


	public boolean verifyInput() {
		if (TextUtils.isEmpty(etUsername.getText().toString().trim())) {
			tilUsername.setError("用户名不能为空");
			return false;
		} else {
			tilUsername.setErrorEnabled(false);
		}

		if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
			tilPsw.setError("密码不能为空");
			return false;
		} else {
			tilPsw.setErrorEnabled(false);
		}

		return true;
	}


	private Boolean readData() {

		return true;
	}


	private void toMainActivity() {
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right,
			R.anim.slide_out_left);
		finish();
	}


	@OnClick(R.id.btn_login)
	public void OnClick(View view) {
		loginRequest(etUsername.getText().toString().trim(), etPwd.getText().toString().trim());
	}


	private void loginRequest(final String username, final String password) {
		if (verifyInput()) {
			hideKeyBoard();
			showLoadingDialog("登录中...");
			Tasks.executeInBackground(this, new BackgroundWork<User>() {
				@Override
				public User doInBackground() throws Exception {
					LogUtil.e("login-->username=" + username);
					LogUtil.e("login-->psw=" + password);
					return UserDao.queryByName(username);
				}
			}, new Completion<User>() {
				@Override
				public void onSuccess(Context context, User result) {

					if (result == null) {
						//用户不存在
						svProgressHUD.showErrorWithStatus("用户不存在");
						return;
					}

					if (!result.getPassWord().equals(password)) {
						//密码不正确
						svProgressHUD.showErrorWithStatus("密码不正确");
						return;
					}
					SPUtils.writeStringSP(SPUtils.USERNAME, etUsername.getText().toString().trim());
					SPUtils.writeStringSP(SPUtils.PASSWORD, etPwd.getText().toString().trim());
					svProgressHUD.dismissImmediately();
					//验证成功
					toMainActivity();


				}

				@Override
				public void onError(Context context, Exception e) {

				}
			});
		}
	}
}

