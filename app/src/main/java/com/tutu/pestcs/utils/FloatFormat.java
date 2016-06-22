package com.tutu.pestcs.utils;

import java.text.DecimalFormat;

/**
 * Created by 47066 on 2016/6/20.
 */
public class FloatFormat {

    //将folat类型格式化为两位小数
    public static String format(float num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(num);//format 返回的是字符串
        return p;
    }

}
