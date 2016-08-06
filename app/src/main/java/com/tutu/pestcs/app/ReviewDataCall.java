package com.tutu.pestcs.app;

import android.content.Context;
import android.text.TextUtils;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.db.NoteDao;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.widget.ToastUtils;

/**
 * Created by 47066 on 2016/7/31.
 */
public class ReviewDataCall {
    public static synchronized void saveReviewData(Context activity) {
        if (TApplication.shu && TApplication.ying && TApplication.zhang && TApplication.wen && TApplication.note) {
            Tasks.executeInBackground(activity, new BackgroundWork<String>() {
                @Override
                public String doInBackground() throws Exception {
                    if (TApplication.shuBean != null) {
                        ShuDao.saveOrUpdate(TApplication.shuBean);

                    }

                    if (TApplication.yingBean != null) {
                        YingDao.saveOrUpdate(TApplication.yingBean);

                    }

                    if (TApplication.wenBean != null) {
                        WenDao.saveOrUpdate(TApplication.wenBean);

                    }

                    if (TApplication.zhangBean != null) {
                        ZhangDao.saveOrUpdate(TApplication.zhangBean);

                    }

                    if (TApplication.noteBean != null) {
                        NoteDao.saveOrUpdate(TApplication.noteBean);

                    }


                    return null;
                }
            }, new Completion<String>() {
                @Override
                public void onSuccess(Context context, String result) {
                    ToastUtils.showToast("所有数据保存成功");
                    TApplication.note = true;
                    TApplication.wen = false;
                    TApplication.ying = false;
                    TApplication.zhang = false;
                    TApplication.shu = false;

                    TApplication.shuBean = null;
                    TApplication.wenBean = null;
                    TApplication.yingBean = null;
                    TApplication.zhangBean = null;
                    TApplication.noteBean = null;

                }

                @Override
                public void onError(Context context, Exception e) {

                }
            });
        }
    }


    public static synchronized void saveInserData(final Context activity) {
        if (TApplication.shuI && TApplication.yingI && TApplication.zhangI && TApplication.wenI && TApplication.noteI) {
            Tasks.executeInBackground(activity, new BackgroundWork<String>() {
                @Override
                public String doInBackground() throws Exception {

                    if (TApplication.shuBeanI == null && TApplication.yingBeanI == null && TApplication.wenBeanI == null
                            && TApplication.zhangBeanI == null && TApplication.noteBeanI == null) {
                        return null;
                    }

                    if (TApplication.shuBeanI != null) {
                        ShuDao.saveOrUpdate(TApplication.shuBeanI);
                    }

                    if (TApplication.yingBeanI != null) {
                        YingDao.saveOrUpdate(TApplication.yingBeanI);

                    }

                    if (TApplication.wenBeanI != null) {
                        WenDao.saveOrUpdate(TApplication.wenBeanI);

                    }

                    if (TApplication.zhangBeanI != null) {
                        ZhangDao.saveOrUpdate(TApplication.zhangBeanI);

                    }

                    if (TApplication.noteBeanI != null) {
                        NoteDao.saveOrUpdate(TApplication.noteBeanI);

                    }


                    return "ok";
                }
            }, new Completion<String>() {
                @Override
                public void onSuccess(Context context, String result) {
                    if (TextUtils.isEmpty(result)) {
                        ToastUtils.showErrorToast("所有数据都没有达到保存条件");
                        return;
                    }
                    ToastUtils.showToast("所有数据保存成功");
                    TApplication.noteI = true;
                    TApplication.wenI = false;
                    TApplication.yingI = false;
                    TApplication.zhangI = false;
                    TApplication.shuI = false;

                    TApplication.shuBeanI = null;
                    TApplication.wenBeanI = null;
                    TApplication.yingBeanI = null;
                    TApplication.zhangBeanI = null;
                    TApplication.noteBeanI = null;
                    ((InsertActivity) activity).finish();
                }

                @Override
                public void onError(Context context, Exception e) {

                }
            });
        }
    }
}
