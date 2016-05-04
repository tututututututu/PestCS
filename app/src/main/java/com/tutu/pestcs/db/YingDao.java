package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.YingBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class YingDao {
	public static void saveBindID(YingBean bean) {
		try {
			DBHelper.getDBManager().saveBindingId(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void saveOrUpdate(YingBean bean) {
		try {
			DBHelper.getDBManager().saveOrUpdate(bean);
			LogUtil.e("YingDao saveOrUpdate 变化一条记录=" + bean.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void update(YingBean bean) {
		try {
			DBHelper.getDBManager().update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<YingBean> queryAll() {
		try {
			return DBHelper.getDBManager().findAll(YingBean.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void delete(YingBean bean) {
		try {
			DBHelper.getDBManager().delete(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}


	public static YingBean queryByUnitID(String unitID) {
		try {
			YingBean bean = DBHelper.getDBManager().selector(YingBean.class).where("UnitCode", "=",
				unitID)
				.findFirst();
			return bean;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


}
