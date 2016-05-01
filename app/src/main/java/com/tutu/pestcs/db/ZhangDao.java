package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.bean.ZhangBean;

import org.xutils.common.util.LogUtil;
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
		if (bean.getId() == 0) {
			LogUtil.e("CheakInsertDao 插入一条记录=" + bean.toString());
			saveBindID(bean);
		} else {
			LogUtil.e("CheakInsertDao 更新一条记录=" + bean.toString());
			update(bean);
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


}
