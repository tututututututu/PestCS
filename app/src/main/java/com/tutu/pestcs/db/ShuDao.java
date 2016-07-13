package com.tutu.pestcs.db;

import android.database.Cursor;
import android.text.TextUtils;

import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.bean.TaskBean;

import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class ShuDao {
    public static void saveBindID(ShuBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(ShuBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
            LogUtil.e("ShuDao saveOrUpdate 变化一条记录=" + bean.toString());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(ShuBean bean) {
        try {
            DBHelper.getDBManager().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ShuBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(ShuBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ShuBean> queryCurrentTask() {
        try {
            TaskBean taskBean = TaskDao.queryCurrent();
            if (taskBean == null) {
                return null;
            }
            return DBHelper.getDBManager().selector(ShuBean.class).where("TaskCode", "=", taskBean.getTaskCode()).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ShuBean> queryByTask(String taskID) {
        try {
            return DBHelper.getDBManager().selector(ShuBean.class).where("TaskCode","=",taskID).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(ShuBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByUnicode(String unicode){
        try {
            DBHelper.getDBManager().delete(ShuBean.class,WhereBuilder.b("UnitCode","=",unicode));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static ShuBean queryByUnitID(String unitID) {
        try {
            ShuBean bean = DBHelper.getDBManager().selector(ShuBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    // shuji           1.不限 2.阴性   3.阳性
    // fangshusheshi   1.不限 2.合格   3.不合格
    // waihuanjinshuji 1.不限 2.阴性    3.阳性
    public static ShuBean queryByUnitIDWithConditon(String unitID, int shuji, int fangshusheshi, int waihuanjinshuji) {
        try {
            ShuBean beans;
            WhereBuilder shujiBuilder = null;
            WhereBuilder fangshusheshiBuilder = null;
            WhereBuilder waihuanjinshujiBuilder = null;
            WhereBuilder builder = WhereBuilder.b();
            switch (shuji) {
                case 3:
                    shujiBuilder = WhereBuilder.b("ShuRoom", ">", "0");
                    break;
                case 2:
                    shujiBuilder = WhereBuilder.b("ShuRoom", "<", "1");
                    break;
            }

            switch (fangshusheshi) {
                case 2:
                    fangshusheshiBuilder = WhereBuilder.b("FangShuBadRoom", "<", "1");
                    break;
                case 3:
                    fangshusheshiBuilder = WhereBuilder.b("FangShuBadRoom", ">", "0");
                    break;
            }

            switch (waihuanjinshuji) {
                case 3:
                    waihuanjinshujiBuilder = WhereBuilder.b("ShuJiNum", ">", "0");
                    break;
                case 2:
                    waihuanjinshujiBuilder = WhereBuilder.b("ShuJiNum", "<", "1");
                    break;
            }


            if (shujiBuilder != null) {
                builder.and(shujiBuilder);
            }

            if (fangshusheshiBuilder != null) {
                builder.and(fangshusheshiBuilder);
            }

            if (waihuanjinshujiBuilder != null) {
                builder.and(waihuanjinshujiBuilder);
            }

            if (!TextUtils.isEmpty(builder.toString())) {
                beans = DBHelper.getDBManager().selector(ShuBean.class).where("UnitCode", "=",
                        unitID).and(builder)
                        .findFirst();
            } else {
                beans = DBHelper.getDBManager().selector(ShuBean.class).where("UnitCode", "=",
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
                ShuBean shubena = DBHelper.getDBManager().selector(ShuBean.class).where("UnitCode", "=", bean
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
            ShuBean shu = ShuDao.queryByUnitID(bean.getUnitCode());
            if (shu != null) {
                count++;
            }
        }

        return count;
    }


    public static int getHadCheakedRoomInCountWai(String unityTyppe) {
        int count = 0;
        try {
            List<CheakInsertBean> cheakInsertList = CheakInsertDao.queryCurrentTaskUnitCode(unityTyppe);
            if (cheakInsertList==null||cheakInsertList.size()<1){
                return 0;
            }

            for (CheakInsertBean bean : cheakInsertList) {
                ShuBean shubena = DBHelper.getDBManager().selector(ShuBean.class).where("UnitCode", "=", bean
                        .getUnitCode())
                        .findFirst();
                if (shubena != null) {
                    count += shubena.getCheckDistance();
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int getHadCheakedUnitInCountWai(String unitType) {
        List<CheakInsertBean> list = CheakInsertDao.queryCurrentTaskUnitCode(unitType);
        if (list == null || list.size() < 1) {
            return 0;
        }
        int count = 0;
        for (CheakInsertBean bean : list) {
            ShuBean shu = ShuDao.queryByUnitID(bean.getUnitCode());
            if (shu != null && shu.getCheckDistance() > 0) {
                count++;
            }
        }

        return count;
    }


    public static long queryCount() {
        try {
            return DBHelper.getDBManager().selector(ShuBean.class).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int CheakRoom(String unitCode) {

        try {
            Cursor cursor = DBHelper.getDBManager().execQuery("select sum(CheckRoom) from T_ShuRecord");
            if (cursor.moveToNext()) {
                cursor.getInt(0);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(ShuBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
