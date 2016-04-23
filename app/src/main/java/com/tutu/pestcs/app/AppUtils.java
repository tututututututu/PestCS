package com.tutu.pestcs.app;

import android.app.Activity;
import android.content.Context;

import com.tutu.pestcs.sp.SPUtils;

/**
 * Created by tutu on 16/4/23.
 */
public class AppUtils {
	public static void Logout(Context context) {
		SPUtils.writeStringSP(SPUtils.USERNAME, "");
		SPUtils.writeStringSP(SPUtils.PASSWORD, "");
		((Activity) context).finish();
	}
}
