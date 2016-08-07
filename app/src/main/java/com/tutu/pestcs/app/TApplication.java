package com.tutu.pestcs.app;

import android.app.Application;

import com.tutu.pestcs.bean.NoteBean;
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ConfigString;
import com.tutu.pestcs.widget.ToastUtils;

import org.xutils.x;


public class TApplication extends Application {
    private static TApplication app;
    public static boolean shu = false;
    public static boolean wen = false;
    public static boolean ying = false;
    public static boolean zhang = false;
    public static boolean note = true;

    public static ShuBean shuBean = null;
    public static WenBean wenBean = null;
    public static YingBean yingBean = null;
    public static ZhangBean zhangBean = null;
    public static NoteBean noteBean = null;



    public static boolean shuI = false;
    public static boolean wenI = false;
    public static boolean yingI = false;
    public static boolean zhangI = false;
    public static boolean noteI = true;

    public static ShuBean shuBeanI = null;
    public static WenBean wenBeanI = null;
    public static YingBean yingBeanI = null;
    public static ZhangBean zhangBeanI = null;
    public static NoteBean noteBeanI = null;


    /**
     * 用来标识是否保存成功之后finish掉activity
     */

    public static boolean insertFinish = false;
    public static boolean reviewFinish = false;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        app = this;
        //PhoneInfo.initPhoneInfo(getApplicationContext());
        x.Ext.init(this);
        x.Ext.setDebug(ConfigString.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        ToastUtils.initToast(this);
    }


    public static TApplication getInstance() {
        return app;
    }

}
