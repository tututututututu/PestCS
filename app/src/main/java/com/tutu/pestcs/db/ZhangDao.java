package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.bean.ZhangBean;

import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class ZhangDao {
    public static void saveBindID(ZhangBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(ZhangBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
            LogUtil.e("ZhangDao saveOrUpdate 变化一条记录=" + bean.toString());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(ZhangBean bean) {
        try {
            DBHelper.getDBManager().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ZhangBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(ZhangBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ZhangBean> queryCurrentTask() {
        try {
            TaskBean taskBean = TaskDao.queryCurrent();
            if (taskBean == null) {
                return null;
            }
            return DBHelper.getDBManager().selector(ZhangBean.class).where("TaskCode", "=", taskBean.getTaskCode()).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void delete(ZhangBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static ZhangBean queryByUnitID(String unitID) {
        try {
            ZhangBean bean = DBHelper.getDBManager().selector(ZhangBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    // chengchong           1.不限 2.阴性   3.阳性
    // luanqiao             1.不限 2.阴性   3.阳性
    // zhangji              1.不限 2.阴性    3.阳性
    public static ZhangBean queryByUnitIDWithConditon(String unitID, int chengchong, int luanqiao, int zhangji) {
        try {
            ZhangBean beans;
            WhereBuilder chengchongBuilder = null;
            WhereBuilder luanqiaoBuilder = null;
            WhereBuilder zhangjiBuilder = null;
            WhereBuilder builder = WhereBuilder.b();
            switch (chengchong) {
                case 3:
                    chengchongBuilder = WhereBuilder.b("ChengCongRoom", ">", "0");
                    break;
                case 2:
                    chengchongBuilder = WhereBuilder.b("ChengCongRoom", "<", "1");
                    break;
            }

            switch (luanqiao) {
                case 2:
                    luanqiaoBuilder = WhereBuilder.b("LuanQiaoRoom", "<", "1");
                    break;
                case 3:
                    luanqiaoBuilder = WhereBuilder.b("LuanQiaoRoom", ">", "0");
                    break;
            }

            switch (zhangji) {
                case 2:
                    zhangjiBuilder = WhereBuilder.b("ZhangJiRoom", "<", "1");
                    break;
                case 3:
                    zhangjiBuilder = WhereBuilder.b("ZhangJiRoom", ">", "0");
                    break;

            }


            if (chengchongBuilder != null) {
                builder.and(chengchongBuilder);
            }

            if (luanqiaoBuilder != null) {
                builder.and(luanqiaoBuilder);
            }

            if (zhangjiBuilder != null) {
                builder.and(zhangjiBuilder);
            }

            if (!TextUtils.isEmpty(builder.toString())) {
                beans = DBHelper.getDBManager().selector(ZhangBean.class).where("UnitCode", "=",
                        unitID).and(builder)
                        .findFirst();
            } else {
                beans = DBHelper.getDBManager().selector(ZhangBean.class).where("UnitCode", "=",
                        unitID)
                        .findFirst();
            }


            return beans;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int getHadCheakedRoomInCount(String unityTyppe) {
        int count = 0;
        try {
            List<CheakInsertBean> cheakInsertList = CheakInsertDao.queryCurrentTaskUnitCode(unityTyppe);
            if (cheakInsertList == null || cheakInsertList.size() < 1) {
                return 0;
            }
            for (CheakInsertBean bean : cheakInsertList) {
                ZhangBean shubena = DBHelper.getDBManager().selector(ZhangBean.class).where("UnitCode", "=", bean
                        .getUnitCode())
                        .findFirst();
                if (shubena != null) {
                    count += shubena.getCheckRoom();
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int getHadCheakedUnitInCount(String unitType) {

        List<CheakInsertBean> list = CheakInsertDao.queryCurrentTaskUnitCode(unitType);
        if (list == null || list.size() < 1) {
            return 0;
        }
        int count = 0;
        for (CheakInsertBean bean : list) {
            ZhangBean shu = ZhangDao.queryByUnitID(bean.getUnitCode());
            if (shu != null) {
                count++;
            }
        }

        return count;

    }


    public static void deleteByUnicode(String unicode){
        try {
            DBHelper.getDBManager().delete(ZhangBean.class,WhereBuilder.b("UnitCode","=",unicode));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(ZhangBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
