package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.GuoBiaoSortUnitBean;
import com.tutu.pestcs.bean.KeyValueDataBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class GuoBiaoUnitDao {
    public static void saveBindID(GuoBiaoSortUnitBean bean) {
        try {
            LogUtil.e("GuoBiaoUnitDao saveBindID=" + bean.toString());
            DBHelper.getDBManager().saveOrUpdate(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static List<GuoBiaoSortUnitBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(GuoBiaoSortUnitBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<KeyValueDataBean> getToCheak(String item, int population) {
        List<GuoBiaoSortUnitBean> beans = new ArrayList<>();
        try {
            beans = DBHelper.getDBManager().selector(GuoBiaoSortUnitBean.class).where
                    ("Item", "=", item).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }

        if (beans.size() > 0) {
            List<KeyValueDataBean> data = new ArrayList();
            if (population <= 10) {
                for (GuoBiaoSortUnitBean bean : beans) {
                    data.add(new KeyValueDataBean(bean.getP01Unit() + "", bean.getP01Room() + ""));
                }
            } else if (population > 10 && population <= 50) {
                for (GuoBiaoSortUnitBean bean : beans) {
                    data.add(new KeyValueDataBean(bean.getP10Unit() + "", bean.getP10Room() + ""));
                }
            } else if (population > 50 && population <= 100) {
                for (GuoBiaoSortUnitBean bean : beans) {
                    data.add(new KeyValueDataBean(bean.getP50Unit() + "", bean.getP50Room() + ""));
                }
            } else if (population > 100 && population <= 200) {
                for (GuoBiaoSortUnitBean bean : beans) {
                    data.add(new KeyValueDataBean(bean.getP100Unit() + "", bean.getP100Room() + ""));
                }
            } else if (population > 200) {
                for (GuoBiaoSortUnitBean bean : beans) {
                    data.add(new KeyValueDataBean(bean.getP200Unit() + "", bean.getP200Room() + ""));
                }
            }
            return data;
        }
        return null;
    }
}
