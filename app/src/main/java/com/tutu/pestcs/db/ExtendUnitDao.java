package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.ExtendSortUnitBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class ExtendUnitDao {
	public static void saveBindID(ExtendSortUnitBean bean) {
		try {
			LogUtil.e("ExtendUnitDao saveBindID=" + bean.toString());
			DBHelper.getDBManager().saveBindingId(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void upData(ExtendSortUnitBean bean) {
		try {
			DBHelper.getDBManager().update(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}


	public static List<ExtendSortUnitBean> queryAll() {
		try {
			return DBHelper.getDBManager().findAll(ExtendSortUnitBean.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ExtendSortUnitBean queryByUnitID(String unitID) {
		try {
			return DBHelper.getDBManager().selector(ExtendSortUnitBean.class).where("unitID", "=", unitID)
				.findFirst();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


}
