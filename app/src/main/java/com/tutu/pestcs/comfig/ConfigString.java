package com.tutu.pestcs.comfig;

public class ConfigString {
	public static final boolean DEBUG = true;

	public static String URL = "";

	enum PRODUCT_MODE {
		MODE_LOCAL_TEST_HUANG
	}

	private static final PRODUCT_MODE PRODUCT_MODEL = PRODUCT_MODE.MODE_LOCAL_TEST_HUANG;

	static {
		if (PRODUCT_MODEL == PRODUCT_MODE.MODE_LOCAL_TEST_HUANG) {
			URL = "http://10.200.125.24:8090/monitor/api.htm";
		}

	}
}
