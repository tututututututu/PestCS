package com.tutu.pestcs.sp;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.tutu.pestcs.app.TApplication;

import org.xutils.common.util.LogUtil;

public class SPUtils {
	private static SharedPreferences sp;
	public static final String SP_NAME = "config";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "psw";
	public static final String IS_FRESH_APP = "is_fresh_app"; //是否第一次打开应用


	public static SharedPreferences getSP() {
		return sp;
	}

	public static void writeStringSP(String key, String value) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getStringSP(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		return sp.getString(key, null);
	}

	public static void writeBooleanSP(String key, boolean value) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void writeLongSP(String key, long value) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static long getLongSP(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		return sp.getLong(key, 0);
	}

	public static boolean getBooleanSP(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
			LogUtil.e("sp=" + sp);
		}

		return sp.getBoolean(key, false);
	}

	public static void clear() {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	public static boolean contains(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}

		return sp.contains(key);
	}

	public static void writeIntSP(String key, int value) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getIntSP(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		return sp.getInt(key, 0);
	}

	public static void writeFloat(String key, float value) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static void remove(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.remove(key);
		editor.commit();
	}

	public static float getFloat(String key) {
		if (sp == null) {
			sp = TApplication.getInstance().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		}
		return sp.getFloat(key, 0);
	}

}
