package com.tutu.pestcs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndrStatusUtils {
    public static boolean hasConnected(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if ((mobNetInfo == null || !mobNetInfo.isConnected())
                && (wifiNetInfo == null || !wifiNetInfo.isConnected())) {
            return false;
        }

        return true;
    }
}
