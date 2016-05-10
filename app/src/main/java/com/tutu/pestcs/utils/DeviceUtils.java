package com.tutu.pestcs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

public final class DeviceUtils {

    private final static int tempLen = 32;

    public final static String EMPTY = "empty";

    private DeviceUtils() {

    }

    public static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static WifiManager getWifiManager(Context context) {
        return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public static PackageManager getPackageManager(Context context) {
        try {
            return context.getPackageManager();
        } catch (Exception e) {

        }
        return null;
    }

    public static NetworkInfo getActiveNetworkType(Context appContext) {
        ConnectivityManager connectivity = (ConnectivityManager) appContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return null;
        }

        NetworkInfo activeInfo = connectivity.getActiveNetworkInfo();
        if (activeInfo == null) {
            return null;
        }
        return activeInfo;
    }

    public static int getVersion(Context ctx) {
        try {
            return getPackageManager(ctx).getPackageInfo(ctx.getPackageName(),
                    0).versionCode;

        } catch (Exception e) {

        }
        return -1;
    }

    public static String getVersionName(Context ctx) {
        try {
            return getPackageManager(ctx).getPackageInfo(ctx.getPackageName(),
                    0).versionName;

        } catch (Exception e) {

        }
        return null;
    }

    public static String getBrand() {
        try {
            return (Build.BRAND.length() > tempLen ? Build.BRAND.substring(0,
                    tempLen) : Build.BRAND).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getModel() {
        try {
            return (Build.MODEL.length() > tempLen ? Build.MODEL.substring(0,
                    tempLen) : Build.MODEL).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getSdkVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getIMSI(Context context) {
        TelephonyManager tm = getTelephonyManager(context);
        String imsi = tm.getSubscriberId();
        if (TextUtils.isEmpty(imsi)) {
            return EMPTY;
        }
        return imsi;
    }

    public static String getIMEI(Context context) {
        String imei = getTelephonyManager(context).getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            return EMPTY;
        }
        if (imei.length() > 15) {
            return imei.substring(imei.length() - 15);
        }
        return imei;
    }

    private static GsmCellLocation getGsmCellLocation() {
        CellLocation cell = CellLocation.getEmpty();
        return (GsmCellLocation) cell;
    }

    public static char getLac() {
        try {
            return (char) getGsmCellLocation().getLac();
        } catch (Exception e) {
            return '0';
        }

    }

    public static char getCellID() {
        try {
            return (char) getGsmCellLocation().getCid();
        } catch (Exception e) {
            return 1234;
        }

    }

    public static String getMacAddress(Context context) {
        WifiManager wifiManager = getWifiManager(context);
        if (wifiManager != null) {
            WifiInfo info = wifiManager.getConnectionInfo();
            if (info != null) {
                return info.getMacAddress();
            }
        }
        return "";

    }

    public static int getMcc(Context context) {
        return context.getResources().getConfiguration().mcc;
    }

    public static int getMnc(Context context) {
        return context.getResources().getConfiguration().mnc;
    }

    public static int getWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getPhoneId(Context context) {
        return Secure
                .getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public static int getRamSize(Context context) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("/proc/meminfo");
            br = new BufferedReader(fr);
            String line = br.readLine();
            String[] arrayOfString = line.split("\\s+");
            return Integer.valueOf(arrayOfString[1]).intValue() / 1024;
        } catch (IOException e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }
        }
        return -1;
    }

    @SuppressWarnings("deprecation")
    public static int getMemSize(Context context) {
        try {
            File root = Environment.getDataDirectory();
            StatFs sf = new StatFs(root.getPath());
            return (int) ((long) sf.getBlockCount() * (long) sf.getBlockSize() / (1024 * 1024));

        } catch (Exception e) {

        }
        return -1;
    }

    public static int getCpuNums() {
        String fileName = "/proc/cpuinfo";// "/proc/cpuinfo";
        int cpuNums = 0;
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr, 8192);
            String line = br.readLine();
            while (line != null) {
                if ("processor".equals(line.split("\\s+")[0])) {
                    cpuNums++;
                }
                line = br.readLine();
            }
        } catch (IOException e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }
        return cpuNums < 1 ? 1 : cpuNums;
    }

}
