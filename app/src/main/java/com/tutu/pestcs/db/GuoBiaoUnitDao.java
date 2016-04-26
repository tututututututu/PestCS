package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.GuoBiaoSortUnitBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class GuoBiaoUnitDao {
	public static void saveBindID(GuoBiaoSortUnitBean bean) {
		try {
			LogUtil.e("GuoBiaoUnitDao saveBindID="+bean.toString());
			DBHelper.getDBManager().saveBindingId(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static List<GuoBiaoSortUnitBean> queryAll() {
		try {
			return DBHelper.getDBManager().findAll(GuoBiaoSortUnitBean.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
}
