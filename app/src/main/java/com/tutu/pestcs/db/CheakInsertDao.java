package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.CheakInsertBean;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class CheakInsertDao {
	public static void saveBindID(CheakInsertBean bean) {
		try {
			DBHelper.getDBManager().saveBindingId(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public static void update(CheakInsertBean bean) {
		try {
			if (TextUtils.isEmpty(bean.getTaskCode())) {
				LogUtil.e("xxxxxxxxxxxxxxxxxxxxx--->CheakInsertDao--->pdate--->bean" + bean.toString());
			}
			DBHelper.getDBManager().update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<CheakInsertBean> queryAll() {
		try {
			return DBHelper.getDBManager().findAll(CheakInsertBean.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void delete(CheakInsertBean bean) {
		try {
			DBHelper.getDBManager().delete(bean);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}



	public static CheakInsertBean queryByUnitID(String unitID) {
		try {
			CheakInsertBean bean = DBHelper.getDBManager().selector(CheakInsertBean.class).where("TaskCode", "=", unitID)
				.findFirst();
			return bean;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}


}
