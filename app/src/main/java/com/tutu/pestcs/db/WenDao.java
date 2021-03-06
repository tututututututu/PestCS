package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.event.ProgressChangeEvent;

import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class WenDao {
    public static void saveBindID(WenBean bean) {
        try {
            DBHelper.getDBManager().saveBindingId(bean);
            RxBus.postEvent(new ProgressChangeEvent(), ProgressChangeEvent.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrUpdate(WenBean bean) {
        try {
            DBHelper.getDBManager().saveOrUpdate(bean);
            RxBus.postEvent(new ProgressChangeEvent(), ProgressChangeEvent.class);
            LogUtil.e("WenDao saveOrUpdate 变化一条记录=" + bean.toString());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void update(WenBean bean) {
        try {
            DBHelper.getDBManager().update(bean);
            RxBus.postEvent(new ProgressChangeEvent(), ProgressChangeEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<WenBean> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(WenBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<WenBean> queryCurrentTask() {
        try {
            TaskBean taskBean = TaskDao.queryCurrent();
            if (taskBean == null) {
                return null;
            }
            return DBHelper.getDBManager().selector(WenBean.class).where("TaskCode", "=", taskBean.getTaskCode())
                    .findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(WenBean bean) {
        try {
            DBHelper.getDBManager().delete(bean);
            RxBus.postEvent(new ProgressChangeEvent(), ProgressChangeEvent.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static WenBean queryByUnitID(String unitID) {
        try {
            WenBean bean = DBHelper.getDBManager().selector(WenBean.class).where("UnitCode", "=",
                    unitID)
                    .findFirst();
            return bean;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    // xiaoxing          1.不限 2.阴性   3.阳性
    // teshu             1.不限 2.否     3.是
    // dazhongxing       1.不限 2.阴性    3.阳性
    public static WenBean queryByUnitIDWithConditon(String unitID, int xiaoxing, int teshu, int dazhongxing) {
        try {
            WenBean beans;
            WhereBuilder xiaoxingBuilder = null;
            WhereBuilder teshuBuilder = null;
            WhereBuilder dazhongxingBuilder = null;
            WhereBuilder builder = WhereBuilder.b();
            switch (xiaoxing) {
                case 3:
                    xiaoxingBuilder = WhereBuilder.b("YangXinWater", ">", "0");
                    break;
                case 2:
                    xiaoxingBuilder = WhereBuilder.b("YangXinWater", "<", "1");
                    break;
            }

            switch (teshu) {
                case 2:
                    teshuBuilder = WhereBuilder.b("YouWenRenCi", "<", "1");
                    break;
                case 3:
                    teshuBuilder = WhereBuilder.b("YouWenRenCi", ">", "0");
                    break;
            }

            switch (dazhongxing) {
                case 2:
                    dazhongxingBuilder = WhereBuilder.b("YangXinShaoNum", "<", "1");
                    break;
                case 3:
                    dazhongxingBuilder = WhereBuilder.b("YangXinShaoNum", ">", "0");
                    break;

            }


            if (xiaoxingBuilder != null) {
                builder.and(xiaoxingBuilder);
            }

            if (teshuBuilder != null) {
                builder.and(teshuBuilder);
            }

            if (dazhongxingBuilder != null) {
                builder.and(dazhongxingBuilder);
            }

            if (!TextUtils.isEmpty(builder.toString())) {
                beans = DBHelper.getDBManager().selector(WenBean.class).where("UnitCode", "=",
                        unitID).and(builder)
                        .findFirst();
            } else {
                beans = DBHelper.getDBManager().selector(WenBean.class).where("UnitCode", "=",
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
                WenBean shubena = DBHelper.getDBManager().selector(WenBean.class).where("UnitCode", "=", bean
                        .getUnitCode())
                        .findFirst();
                if (shubena != null) {
                    count+=shubena.getCheckDistance();
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
            WenBean shu = WenDao.queryByUnitID(bean.getUnitCode());
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
            if (cheakInsertList == null || cheakInsertList.size() < 1) {
                return 0;
            }

            for (CheakInsertBean bean : cheakInsertList) {
                WenBean shubena = DBHelper.getDBManager().selector(WenBean.class).where("UnitCode", "=", bean
                        .getUnitCode())
                        .findFirst();
                if (shubena != null) {
                    count+=shubena.getCheckDistance();
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
            WenBean shu = WenDao.queryByUnitID(bean.getUnitCode());
            if (shu != null && shu.getYouWenRenCi() > 0) {
                count++;
            }
        }

        return count;
    }


    public static void deleteByUnicode(String unicode) {
        try {
            DBHelper.getDBManager().delete(WenBean.class, WhereBuilder.b("UnitCode", "=", unicode));
            RxBus.postEvent(new ProgressChangeEvent(), ProgressChangeEvent.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static int dropTable() {
        try {
            DBHelper.getDBManager().dropTable(WenBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
