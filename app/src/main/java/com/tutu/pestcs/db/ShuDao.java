package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.ShuBean;

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


    public static void delete(ShuBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
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


    public static int getHadCheakedRoomInCount(String unitType) {
        try {
            List<ShuBean> beans = DBHelper.getDBManager().selector(ShuBean.class).where("uniType", "=", unitType)
                    .findAll();
            if (beans == null) {
                return 0;
            }
            int count = 0;
            for (ShuBean bean : beans) {
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
            return (int) DBHelper.getDBManager().selector(ShuBean.class).where("uniType", "=", unitType).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
