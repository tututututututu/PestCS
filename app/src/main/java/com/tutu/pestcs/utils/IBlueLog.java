package com.tutu.pestcs.utils;


import android.util.Log;

import com.tutu.pestcs.comfig.ConfigString;

public class IBlueLog {

    public static void e(String msg) {
        if (ConfigString.DEBUG) {
            Log.e("", msg + "");
        }
    }

    public static void i(String msg) {
        if (ConfigString.DEBUG) {
            Log.i("", msg + "");
        }
    }

    public static void w(String msg) {
        if (ConfigString.DEBUG) {
            Log.w("", msg + "");
        }
    }
}
