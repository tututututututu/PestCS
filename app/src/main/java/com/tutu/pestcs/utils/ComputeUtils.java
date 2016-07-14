package com.tutu.pestcs.utils;

/**
 * Created by 47066 on 2016/7/14.
 */
public class ComputeUtils {
    public static int floatUp(int x, int y) {
        if (x % y > 0) {
            return x / y + 1;
        } else {
            return x / y;
        }
    }
}
