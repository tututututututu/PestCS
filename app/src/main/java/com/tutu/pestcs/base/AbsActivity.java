package com.tutu.pestcs.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.tutu.pestcs.R;

public abstract class AbsActivity extends AppCompatActivity {


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= 21) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(
				R.color.colorPrimary));
		}

		com.tutu.pestcs.base.ActivityStack.push(this);
	}


	/**
	 * 隐蔽软键盘
	 */
	public void hideKeyBoard() {
		View view = getCurrentFocus();
		if (view != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
				hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public void showKeyBoard() {
		View view = getCurrentFocus();
		if (view != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
				hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		hideKeyBoard();
		ActivityStack.pop(this);
		super.onDestroy();
	}

}
