package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.WenBean;

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
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void saveOrUpdate(WenBean bean) {
		try {
			DBHelper.getDBManager().saveOrUpdate(bean);
			LogUtil.e("WenDao saveOrUpdate 变化一条记录=" + bean.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void update(WenBean bean) {
		try {
			DBHelper.getDBManager().update(bean);
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


	public static void delete(WenBean bean) {
		try {
			DBHelper.getDBManager().delete(bean);
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


	public static int getHadCheakedRoomInCount(String unitType) {
		try {
			List<WenBean> beans = DBHelper.getDBManager().selector(WenBean.class).where("uniType","=",unitType).findAll();
			if (beans==null){
				return 0;
			}
			int count = 0;
			for (WenBean bean : beans){
				count += bean.getCheckDistance();
			}
			return count;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getHadCheakedUnitInCount(String unitType) {
		try {
			return (int)DBHelper.getDBManager().selector(WenBean.class).where("uniType","=",unitType).count();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
