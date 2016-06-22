package com.tutu.pestcs.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by 47066 on 2016/6/20.
 */
public class PhotosStore {
    public static String getPhotosDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "tutu/imgs" + File.separator;
    }
}
