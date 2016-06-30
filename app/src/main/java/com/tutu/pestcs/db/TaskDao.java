package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.TaskBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class TaskDao {
    public static void saveBindID(TaskBean taskBean) {
        try {
            DBHelper.getDBManager().saveBindingId(taskBean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(TaskBean task) {
        try {
            if (TextUtils.isEmpty(task.getTaskCode())) {
                LogUtil.e("xxxxxxxxxxxxxxxxxxxxx--->TaskDao--->pdate--->task" + task);
            }
            DBHelper.getDBManager().saveOrUpdate(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<TaskBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(TaskBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TaskBean> queryAllExceptCurrent() {
        try {
            return DBHelper.getDBManager().selector(TaskBean.class).where("IsCurrent", "=", false).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TaskBean queryCurrent() {
        try {
            return DBHelper.getDBManager().selector(TaskBean.class).where("IsCurrent", "=", true).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(TaskBean task) {
        try {
            DBHelper.getDBManager().delete(task);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void resetCurrent() {
        try {
            TaskBean bean = queryCurrent();
            if (bean != null) {
                bean.setCurrent(false);
                DBHelper.getDBManager().update(bean);
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static TaskBean queryByTaskCode(String taskCode) {
        try {
            TaskBean user1 = DBHelper.getDBManager().selector(TaskBean.class).where("TaskCode", "=", taskCode)
                    .findFirst();
            return user1;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(TaskBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
