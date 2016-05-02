package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.bean.YingBean;

import org.xutils.common.util.LogUtil;
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


}
