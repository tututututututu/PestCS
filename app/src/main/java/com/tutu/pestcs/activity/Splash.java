package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.AbsActivity;
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.bean.GuoBiaoSortUnitBean;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.db.GuoBiaoUnitDao;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.sp.SPUtils;

import org.xutils.common.util.LogUtil;

public class Splash extends AbsActivity {
	private boolean timeOver = false;
	private boolean taskOver = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		if (!SPUtils.getBooleanSP(SPUtils.IS_FRESH_APP)) {
			initUser();
			initUnitData();
			initUnitType();
		} else {
			taskOver = true;
		}


		Tasks.executeInBackground(this, new BackgroundWork<Void>() {
			@Override
			public Void doInBackground() throws Exception {
				Thread.sleep(3000);
				return null;
			}
		}, new Completion<Void>() {
			@Override
			public void onSuccess(Context context, Void result) {
				timeOver = true;
				LogUtil.e("timeOver");
				goOn();
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});

	}

	//初始化国标数据  人口对应的检查指标
	private void initUnitData() {
		GuoBiaoSortUnitBean guobiao = new GuoBiaoSortUnitBean("01", "shu_in", "餐饮店", 1, 80, 800, 60, 600, 40, 400, 20,
			200, 10, 100);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("02", "shu_in", "商场、超市", 2, 40, 400, 30, 300, 20, 200, 10, 100, 5, 50);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("03", "shu_in", "机关、企业单位", 3, 40, 400, 30, 300, 20, 200, 10, 100, 5, 50);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("04", "shu_in", "饭店宾馆", 4, 20, 200, 15, 150, 10, 100, 6, 60, 3, 30);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("05", "shu_in", "农贸市场", 5, 12, 120, 9, 90, 6, 60, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("06", "shu_in", "机场或车站", 6, 4, 40, 3, 30, 2, 20, 2, 15, 1, 10);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("07", "shu_in", "医院", 7, 10, 100, 7, 75, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("08", "shu_in", "学校", 8, 10, 100, 8, 80, 6, 60, 4, 40, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("09", "shu_in", "建筑拆迁工地", 9, 10, 100, 7, 75, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("10", "shu_in", "居（家）委会", 10, 8, 80, 5, 50, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("12", "shu_out", "公共绿地、公园或道路两侧", 12, 10, 1000, 8, 800, 5, 500, 4, 400, 2,
			200);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("13", "shu_out", "垃圾中转站或公共厕所", 13, 5, 500, 5, 500, 5, 500, 3, 300, 2, 200);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("14", "shu_out", "单位或居民区院内", 14, 10, 1000, 7, 700, 5, 500, 5, 500, 3, 300);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("15", "shu_out", "农贸市场、工地或车站", 15, 5, 500, 5, 500, 5, 500, 3, 300, 3, 300);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("17", "ying_in", "餐饮店", 17, 80, 800, 60, 600, 40, 400, 20, 200, 10, 100);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("18", "ying_in", "商场、超市", 18, 40, 400, 30, 300, 20, 200, 10, 100, 5, 50);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("19", "ying_in", "机关企业单位", 19, 40, 400, 30, 300, 20, 200, 10, 100, 5, 50);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("20", "ying_in", "饭店宾馆", 20, 20, 200, 15, 150, 10, 100, 6, 60, 3, 30);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("21", "ying_in", "农贸市场", 21, 12, 120, 9, 90, 6, 60, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("22", "ying_in", "机场或车站", 22, 4, 40, 3, 30, 2, 20, 2, 15, 1, 10);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("23", "ying_in", "医院", 23, 10, 100, 7, 75, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("24", "ying_in", "建筑拆迁工地", 24, 10, 100, 7, 75, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("26", "ying_out", "室外垃圾容器", 26, 0, 200, 0, 150, 0, 100, 0, 50, 0, 25);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("27", "ying_out", "垃圾中转站", 27, 0, 20, 0, 15, 0, 10, 0, 5, 0, 2);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("28", "ying_out", "外环境散在孳生地", 28, 40, 4000, 30, 3000, 20, 2000, 15, 1500,
			10, 1000);
		GuoBiaoUnitDao.saveBindID(guobiao);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("29", "ying_out", "公共厕所", 29, 0, 20, 0, 15, 0, 10, 0, 5, 0, 2);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("30", "zhang", "餐饮店", 30, 150, 1500, 100, 1000, 80, 800, 50, 500, 30, 300);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("31", "zhang", "商场、超市", 31, 15, 150, 10, 100, 8, 80, 5, 50, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("32", "zhang", "机关单位", 32, 75, 750, 50, 500, 40, 400, 30, 300, 15, 150);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("33", "zhang", "宾馆", 33, 30, 300, 20, 200, 15, 150, 10, 100, 5, 50);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("34", "zhang", "农贸市场", 34, 6, 60, 4, 40, 3, 30, 2, 20, 1, 10);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("35", "zhang", "机场或车站", 35, 3, 30, 3, 30, 2, 20, 1, 10, 1, 10);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("36", "zhang", "医院", 36, 8, 80, 6, 60, 4, 40, 3, 30, 1, 10);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("37", "zhang", "学校", 37, 15, 150, 12, 120, 8, 80, 6, 60, 3, 30);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("38", "zhang", "居（家）委会", 38, 8, 80, 5, 50, 5, 50, 3, 30, 2, 20);
		GuoBiaoUnitDao.saveBindID(guobiao);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("40", "wen01", "居委会", 40, 40, 5000, 30, 4000, 20, 3000, 10, 2000, 5, 1000);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("41", "wen01", "单位(有独立院落)", 41, 50, 5000, 35, 4000, 20, 3000, 15, 2000, 10,
		1000);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("42", "wen01", "建筑工地", 42, 20, 5000, 15, 4000, 12, 3000, 8, 2000, 3, 1000);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("43", "wen01", "道路（雨水井口)", 43, 0, 5000, 0, 4000, 0, 3000, 0, 2000, 0, 1000);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("45", "wen02", "大中型水体/个", 45, 20, 0, 15, 0, 10, 0, 5, 0, 3, 0);
		GuoBiaoUnitDao.saveBindID(guobiao);
		guobiao = new GuoBiaoSortUnitBean("46", "wen02", "特殊场所诱蚊/人次", 46, 15, 0, 10, 0, 8, 0, 5, 0, 3, 0);
		GuoBiaoUnitDao.saveBindID(guobiao);
	}

	private void initUnitType(){
		ExtendSortUnitBean bean = new ExtendSortUnitBean("01","餐饮店",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("02","商场超市",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("03","机关",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("04","饭店宾馆",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("05","农贸市场",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("06","机场车站",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("07","医院",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("08","学校",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("09","建筑拆迁工地",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("10","居(家)委会",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("11","食品加工企业",true);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("12","其他企业",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("13","道路",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("14","公园公告绿地",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("15","垃圾中转站",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("16","公共厕所",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("17","大中型水体",false);
		ExtendUnitDao.saveBindID(bean);
		bean = new ExtendSortUnitBean("18","特殊场所",false);
		ExtendUnitDao.saveBindID(bean);
	}

	private void initUser() {
		//创建对象，用来生成表
		User user = new User();
		user.setUserName("superadmin");
		user.setUserGrade("0");
		user.setPassWord("123456");

		User user1 = new User();
		user1.setUserName("admin");
		user1.setUserGrade("0");
		user1.setPassWord("123456");

		User user2 = new User();
		user2.setUserName("user");
		user2.setUserGrade("1");
		user2.setPassWord("123456");

		//创建表
		UserDao.saveOrUpdate(user);
		LogUtil.e("saveOrUpdate(user)");
		UserDao.saveOrUpdate(user1);
		LogUtil.e("saveOrUpdate(user1)");
		UserDao.saveOrUpdate(user2);
		LogUtil.e("saveOrUpdate(user2)");
		SPUtils.writeBooleanSP(SPUtils.IS_FRESH_APP, true);
		LogUtil.e("taskOver");
		taskOver = true;
	}

	private void goOn() {
		LogUtil.e("goOn" + " taskOver=" + taskOver + " timeOver=" + timeOver);
		if (taskOver && timeOver) {
			if (!TextUtils.isEmpty(SPUtils.getStringSP(SPUtils.USERNAME)) && !TextUtils.isEmpty(SPUtils.getStringSP
				(SPUtils.PASSWORD))) {
				LogUtil.e("has user");
				loginRequest();
			} else {
				LogUtil.e("no user");
				//没有用户处于登录状态
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}


	private void loginRequest() {
		Tasks.executeInBackground(this, new BackgroundWork<User>() {
			@Override
			public User doInBackground() throws Exception {
				LogUtil.e("SP-->username=" + SPUtils.getStringSP(SPUtils.USERNAME));
				LogUtil.e("SP-->psw=" + SPUtils.getStringSP(SPUtils.PASSWORD));
				return UserDao.queryByName(SPUtils.getStringSP(SPUtils.USERNAME));
			}
		}, new Completion<User>() {
			@Override
			public void onSuccess(Context context, User result) {

				if (result != null && result.getPassWord().equals(SPUtils.getStringSP(SPUtils.PASSWORD))) {
					Intent intent = new Intent(Splash.this, MainActivity.class);
					startActivity(intent);
					LogUtil.e("默认登录成功->user=" + result.toString());
				} else {
					Intent intent = new Intent(Splash.this, LoginActivity.class);
					startActivity(intent);
					LogUtil.e("默认登录失败");
				}
				finish();
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});
	}

}
