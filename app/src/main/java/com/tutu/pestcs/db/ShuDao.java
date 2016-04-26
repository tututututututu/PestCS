package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.ShuBean;

import org.xutils.common.util.LogUtil;
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
		if (bean.getId() == 0) {
			LogUtil.e("CheakInsertDao 插入一条记录=" + bean.toString());
			saveBindID(bean);
		} else {
			LogUtil.e("CheakInsertDao 更新一条记录=" + bean.toString());
			update(bean);
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
			ShuBean bean = DBHelper.getDBManager().selector(ShuBean.class).where("TaskCode", "=",
				unitID)
				.findFirst();
			return bean;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


}
