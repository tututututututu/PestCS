package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.TaskBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class CheakInsertDao {
    public static void saveBindID(CheakInsertBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(CheakInsertBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(CheakInsertBean bean) {
        try {
            if (TextUtils.isEmpty(bean.getTaskCode())) {
                LogUtil.e("xxxxxxxxxxxxxxxxxxxxx--->CheakInsertDao--->pdate--->bean" + bean.toString());
            }
            DBHelper.getDBManager().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<CheakInsertBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(CheakInsertBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(CheakInsertBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static CheakInsertBean queryByUnitID(String unitID) {
        try {
            CheakInsertBean bean = DBHelper.getDBManager().selector(CheakInsertBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    //0.不限 1.是 2.不是
    public static List<CheakInsertBean> queryByUnitTypeAndIsKeyClass(String UnitClassID, int isKeyClass, String
            taskCode) {
        try {
            List<CheakInsertBean> beans;
            if (TextUtils.isEmpty(UnitClassID)) {
                if (isKeyClass == 1 || isKeyClass == 2) {
                    beans = DBHelper.getDBManager().selector(CheakInsertBean.class).where("IsKeyUnit", "=",
                            isKeyClass == 1 ? true : false).and("TaskCode", "=", taskCode).findAll();
                } else {
                    beans = DBHelper.getDBManager().selector(CheakInsertBean.class).where("TaskCode", "=", taskCode)
                            .findAll();

                }
            } else {
                if (isKeyClass == 1 || isKeyClass == 2) {
                    beans = DBHelper.getDBManager().selector(CheakInsertBean.class).where("UnitClassID", "=",

                            UnitClassID).and("IsKeyUnit", "=", isKeyClass == 1 ? true : false).and("TaskCode", "=",
                            taskCode).findAll();
                } else {
                    beans = DBHelper.getDBManager().selector(CheakInsertBean.class).where("UnitClassID", "=",

                            UnitClassID).and("TaskCode", "=", taskCode).findAll();

                }
            }


            return beans;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    //当前任务->list<CheakInsertBean> -> UnitClassID = unityType


    //查询当前任务的单位代码
    public static List<CheakInsertBean> queryCurrentTaskUnitCode(String unityTyppe) {
        try {
            TaskBean task = TaskDao.queryCurrent();
            return DBHelper.getDBManager().selector(CheakInsertBean.class).where("TaskCode", "=",
                    task.getTaskCode()).and("UnitClassID", "=", unityTyppe).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


}
