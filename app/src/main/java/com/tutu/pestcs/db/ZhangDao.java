package com.tutu.pestcs.db;

import android.text.TextUtils;

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


    public static int getHadCheakedRoomInCount(String unitType) {
        try {
            List<ZhangBean> beans = DBHelper.getDBManager().selector(ZhangBean.class).where("uniType", "=", unitType).findAll();
            if (beans == null) {
                return 0;
            }
            int count = 0;
            for (ZhangBean bean : beans) {
                count += bean.getCheckRoom();
            }
            return count;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getHadCheakedUnitInCount(String unitType) {
        try {
            return (int) DBHelper.getDBManager().selector(ZhangBean.class).where("uniType", "=", unitType).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
