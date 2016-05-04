package com.tutu.pestcs.db;

import android.text.TextUtils;

import com.tutu.pestcs.bean.YingBean;

import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.WhereBuilder;
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


	// shileichengying           1.不限 2.阳性   3.阴性
	// fangyingsheshi            1.合格 2.不合格  3.不限
	// shiwaiyingleizishendi     1.不限 2.阳性    3.阴性
	public static YingBean queryByUnitIDWithConditon(String unitID, int shileichengying, int fangyingsheshi, int shiwaiyingleizishendi) {
		try {
			YingBean beans;
			WhereBuilder shujiBuilder = null;
			WhereBuilder fangshusheshiBuilder = null;
			WhereBuilder waihuanjinshujiBuilder = null;
			WhereBuilder builder = WhereBuilder.b();
			switch (shileichengying) {
				case 3:
					shujiBuilder = WhereBuilder.b("YingNum", ">", "0");
					break;
				case 2:
					shujiBuilder = WhereBuilder.b("YingNum", "<", "1");
					break;
			}

			switch (fangyingsheshi) {
				case 1:
					fangshusheshiBuilder = WhereBuilder.b("FangYingBadPlace", "<", "1");
					break;
				case 2:
					fangshusheshiBuilder = WhereBuilder.b("FangYingBadPlace", ">", "0");
					break;
			}

			switch (shiwaiyingleizishendi) {
				case 2:
					waihuanjinshujiBuilder = WhereBuilder.b("SanZaiYangXinNum", ">", "0");
					break;
				case 3:
					waihuanjinshujiBuilder = WhereBuilder.b("SanZaiYangXinNum", "<", "1");
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
				beans = DBHelper.getDBManager().selector(YingBean.class).where("UnitCode", "=",
						unitID).and(builder)
						.findFirst();
			} else {
				beans = DBHelper.getDBManager().selector(YingBean.class).where("UnitCode", "=",
						unitID)
						.findFirst();
			}


			return beans;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}

}
