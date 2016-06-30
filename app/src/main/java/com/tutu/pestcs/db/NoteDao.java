package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.NoteBean;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class NoteDao {
    public static void saveBindID(NoteBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(NoteBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(NoteBean bean) {
        try {
            DBHelper.getDBManager().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<NoteBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(NoteBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(NoteBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static NoteBean queryByUnitID(String unitID) {
        try {
            NoteBean bean = DBHelper.getDBManager().selector(NoteBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(NoteBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
