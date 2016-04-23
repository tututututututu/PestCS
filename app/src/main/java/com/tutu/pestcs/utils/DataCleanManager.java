package com.tutu.pestcs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

import com.tutu.pestcs.sp.SPUtils;

import java.io.File;

/**
 * 本应用数据清除管理器
 */
public class DataCleanManager {
	/**
	 * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context
	 */
	public static void cleanInternalCache(Context context) {

		deleteFilesByDirectory(context.getCacheDir());
	}

	/**
	 * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context
	 */
	public static void cleanDatabases(Context context, String user) {

		deleteFilesDatabase(new File("/data/data/" + context.getPackageName()
			+ "/databases"), user);
	}

	/**
	 * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
	 * context
	 */
	public static void cleanSharedPreference(SharedPreferences sp) {
		Editor dEditor = sp.edit();
		dEditor.clear();
		dEditor.commit();
	}

	/**
	 * 按名字清除本应用数据库 * * @param context * @param dbName
	 */
	public static void cleanDatabaseByName(Context context, String dbName) {
		context.deleteDatabase(dbName);
	}

	/**
	 * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context
	 */
	public static void cleanFiles(Context context) {
		deleteFilesByDirectory(context.getFilesDir());
	}

	/**
	 * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
	 * context
	 */
	public static void cleanExternalCache(Context context) {

		if (Environment.getExternalStorageState().equals(
			Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir());
		}
	}

	/**
	 * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath
	 */
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(new File(filePath));
	}

	/**
	 * 清除本应用所有的数据 * * @param context * @param filepath
	 */
	public static void cleanApplicationData(Context context,
	                                        SharedPreferences sp) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		String user = SPUtils
			.getStringSP(SPUtils.SP_NAME);
		cleanDatabases(context, user);
		cleanSharedPreference(sp);
		cleanFiles(context);
	}

	/**
	 * 清除本应用所有的数据 和指定路径文件* * @param context * @param filepath
	 */
	public static void cleanApplicationDataAndMore(Context context,
	                                               SharedPreferences sp, String... filepath) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		String user = SPUtils
			.getStringSP(SPUtils.SP_NAME);
		cleanDatabases(context, user);
		cleanSharedPreference(sp);
		cleanFiles(context);
		for (String filePath : filepath) {
			cleanCustomCache(filePath);
		}
	}

	/**
	 * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
	 */
	private static void deleteFilesByDirectory(File directory) {

		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				boolean ret = item.delete();
				IBlueLog.i("删除应用程序数据 " + ret + item.getAbsolutePath());
			}
		}
	}

	private static void deleteFilesDatabase(File directory, String username) {

		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				String name = item.getAbsoluteFile().getName();
				if (((username != null) && name
					.contains((username + "_evlib.db")))
					|| "myicon.png".equals(name)) {
					boolean ret = item.delete();
					IBlueLog.i("删除应用程序数据库 " + ret + item.getAbsolutePath());
				}
			}
		}
	}
}
