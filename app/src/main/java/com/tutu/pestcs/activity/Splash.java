package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.AbsActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.sp.SPUtils;

import org.xutils.common.util.LogUtil;

public class Splash extends AbsActivity {
	private boolean timeOver = false;
	private boolean taskOver = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		if (!SPUtils.getBooleanSP(SPUtils.IS_FRESH_APP)) {
			initUser();
		} else {
			taskOver = true;
		}


		Tasks.executeInBackground(this, new BackgroundWork<Void>() {
			@Override
			public Void doInBackground() throws Exception {
				Thread.sleep(3000);
				return null;
			}
		}, new Completion<Void>() {
			@Override
			public void onSuccess(Context context, Void result) {
				timeOver = true;
				LogUtil.e("timeOver");
				goOn();
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});

	}

	private void initUser() {
		//创建对象，用来生成表
		User user = new User();
		user.setUserName("superadmin");
		user.setUserGrade("0");
		user.setPassWord("123456");

		User user1 = new User();
		user1.setUserName("admin");
		user1.setUserGrade("0");
		user1.setPassWord("123456");

		User user2 = new User();
		user2.setUserName("user");
		user2.setUserGrade("1");
		user2.setPassWord("123456");

		//创建表
		UserDao.saveBindID(user);
		LogUtil.e("saveOrUpdate(user)");
		UserDao.saveBindID(user1);
		LogUtil.e("saveOrUpdate(user1)");
		UserDao.saveBindID(user2);
		LogUtil.e("saveOrUpdate(user2)");
		SPUtils.writeBooleanSP(SPUtils.IS_FRESH_APP, true);
		LogUtil.e("taskOver");
		taskOver = true;
	}

	private void goOn() {
		LogUtil.e("goOn" + " taskOver=" + taskOver + " timeOver=" + timeOver);
		if (taskOver && timeOver) {
			if (!TextUtils.isEmpty(SPUtils.getStringSP(SPUtils.USERNAME)) && !TextUtils.isEmpty(SPUtils.getStringSP
				(SPUtils.PASSWORD))) {
				LogUtil.e("has user");
				loginRequest();
			} else {
				LogUtil.e("no user");
				//没有用户处于登录状态
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}


	private void loginRequest() {
		Tasks.executeInBackground(this, new BackgroundWork<User>() {
			@Override
			public User doInBackground() throws Exception {
				LogUtil.e("SP-->username=" + SPUtils.getStringSP(SPUtils.USERNAME));
				LogUtil.e("SP-->psw=" + SPUtils.getStringSP(SPUtils.PASSWORD));
				return UserDao.queryByName(SPUtils.getStringSP(SPUtils.USERNAME));
			}
		}, new Completion<User>() {
			@Override
			public void onSuccess(Context context, User result) {

				if (result != null && result.getPassWord().equals(SPUtils.getStringSP(SPUtils.PASSWORD))) {
					Intent intent = new Intent(Splash.this, MainActivity.class);
					startActivity(intent);
					LogUtil.e("默认登录成功->user=" + result.toString());
				} else {
					Intent intent = new Intent(Splash.this, LoginActivity.class);
					startActivity(intent);
					LogUtil.e("默认登录失败");
				}
				finish();
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});
	}

}
