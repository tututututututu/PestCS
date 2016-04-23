package com.tutu.pestcs.app;

import android.app.Application;

import com.tutu.pestcs.comfig.ConfigString;

import org.xutils.x;


public class TApplication extends Application {
	private static TApplication app;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
		//PhoneInfo.initPhoneInfo(getApplicationContext());
		x.Ext.init(this);
		x.Ext.setDebug(ConfigString.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
	}

	public static TApplication getInstance() {
		return app;
	}

}
