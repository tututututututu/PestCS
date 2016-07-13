package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.PhotoBean;

import org.xutils.db.sqlite.WhereBuilder;
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


    public static List<PhotoBean> queryByUnitID(String unitID) {
        try {
            return DBHelper.getDBManager().selector(PhotoBean.class).where("UnitCode", "=",
                    unitID)
                    .findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(PhotoBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void deleteByUnicode(String unicode){
        try {
            DBHelper.getDBManager().delete(PhotoBean.class, WhereBuilder.b("UnitCode","=",unicode));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
