package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.PhotoBean;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class PhotoDao {
    public static void saveBindID(PhotoBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(PhotoBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(PhotoBean bean) {
        try {
            DBHelper.getDBManager().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<PhotoBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(PhotoBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(PhotoBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static PhotoBean queryByUnitID(String unitID) {
        try {
            PhotoBean bean = DBHelper.getDBManager().selector(PhotoBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


}
