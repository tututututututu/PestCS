package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.MainActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 * 苍蝇
 */
public class FliesFragment extends BaseFragment {
	@Bind(R.id.et_jianchafangshu)
	EditText et_jianchafangshu;
	int jianchafangshu = 0;
	@Bind(R.id.et_yangxingfangshu)
	EditText et_yangxingfangshu;
	int yangxingfangshu = 0;
	@Bind(R.id.et_chengyingzshu)
	EditText et_chengyingzshu;
	int chengyingzshu = 0;
	@Bind(R.id.et_jianchachangsuoshu)
	EditText et_jianchachangsuoshu;
	int jianchachangsuoshu = 0;
	@Bind(R.id.et_buhegechangsuoshu)
	EditText et_buhegechangsuoshu;
	int buhegechangsuoshu = 0;
	@Bind(R.id.et_shiwairumenkou)
	EditText et_shiwairumenkou;
	int shiwairumenkou = 0;
	@Bind(R.id.et_tongshiwaichuangkou)
	EditText et_tongshiwaichuangkou;
	int tongshiwaichuangkou = 0;
	@Bind(R.id.et_chufangmen)
	EditText et_chufangmen;
	int chufangmen = 0;
	@Bind(R.id.et_shushijian)
	EditText et_shushijian;
	int shushijian = 0;
	@Bind(R.id.et_zhijierukoushipinchugui)
	EditText et_zhijierukoushipinchugui;
	int zhijierukoushipinchugui = 0;
	@Bind(R.id.et_liangcaijian)
	EditText et_liangcaijian;
	int liangcaijian = 0;
	@Bind(R.id.et_zhijierukoushipintandian)
	EditText et_zhijierukoushipintandian;
	int zhijierukoushipintandian = 0;
	@Bind(R.id.et_qita)
	EditText et_qita;
	int qita = 0;
	@Bind(R.id.et_scxszjrkspdcs)
	EditText et_scxszjrkspdcs;
	int scxszjrkspdcs = 0;
	@Bind(R.id.et_qizhongyouyingchangsuo)
	EditText et_qizhongyouyingchangsuo;
	int qizhongyouyingchangsuo = 0;
	@Bind(R.id.et_shineimieyingdeng)
	EditText et_shineimieyingdeng;
	int shineimieyingdeng = 0;
	@Bind(R.id.et_fangzhibuzhengqueshu)
	EditText et_fangzhibuzhengqueshu;
	int fangzhibuzhengqueshu = 0;
	@Bind(R.id.et_shineiyingleizishengdi)
	EditText et_shineiyingleizishengdi;
	int shineiyingleizishengdi = 0;
	@Bind(R.id.et_yangxing)
	EditText et_yangxing;
	int yangxing = 0;
	@Bind(R.id.et_shiwailajirongqi)
	EditText et_shiwailajirongqi;
	int shiwailajirongqi = 0;
	@Bind(R.id.et_shiwailajiyangxing)
	EditText et_shiwailajiyangxing;
	int shiwailajiyangxing = 0;
	@Bind(R.id.et_gonggongcesuo)
	EditText et_gonggongcesuo;
	int gonggongcesuo = 0;
	@Bind(R.id.et_gonggongcesuoyangxing)
	EditText et_gonggongcesuoyangxing;
	int gonggongcesuoyangxing = 0;
	@Bind(R.id.et_lajizhongzhuanzhan)
	EditText et_lajizhongzhuanzhan;
	int lajizhongzhuanzhan = 0;
	@Bind(R.id.et_lajizhongzhuanzhanyangxing)
	EditText et_lajizhongzhuanzhanyangxing;
	int lajizhongzhuanzhanyangxing = 0;
	@Bind(R.id.et_jianchalujing)
	EditText et_jianchalujing;
	int jianchalujing = 0;
	@Bind(R.id.et_sanzaizishendi)
	EditText et_sanzaizishendi;
	int sanzaizishendi = 0;
	@Bind(R.id.et_sanzaizishendiyangxing)
	EditText et_sanzaizishendiyangxing;
	int sanzaizishendiyangxing = 0;
	@Bind(R.id.ll_fangyingsheshibuhegebuwei)
	LinearLayout ll_fangyingsheshibuhegebuwei;

	private CheakInsertBean cheakInsertBean;
	private YingBean yingBean = new YingBean();


	@Override
	public void handleMessage(Message msg) {
	}


	@Override
	public void initView() {
		cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);

		yingBean.setUnitCode(cheakInsertBean.getUnitCode());

		et_buhegechangsuoshu.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(s.toString().trim())) {
					if (Integer.valueOf(s.toString().trim()) > 0) {
						ll_fangyingsheshibuhegebuwei.setVisibility(View.VISIBLE);
					} else {
						ll_fangyingsheshibuhegebuwei.setVisibility(View.GONE);
					}
				} else {
					ll_fangyingsheshibuhegebuwei.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	public int getLayoutID() {
		return R.layout.flies_insert_fragment;
	}


	@OnClick({R.id.btn_save, R.id.btn_exit})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.btn_save:
				formatData();
				if (verifyInput()) {
					YingDao.saveOrUpdate(yingBean);
				}
				break;
			case R.id.btn_exit:
				AlertDialogUtil.showDialog(mActivityContext, new IOnConfirmOrCancel() {
					@Override
					public void OnConfrim() {
						((MainActivity) getActivity()).toFragment(0);
					}

					@Override
					public void OnCancel() {

					}
				});
				break;
		}
	}

	private void formatData() {
		jianchafangshu = dealData(et_jianchafangshu);
		yangxingfangshu = dealData(et_yangxingfangshu);
		chengyingzshu = dealData(et_chengyingzshu);
		jianchachangsuoshu = dealData(et_jianchachangsuoshu);
		buhegechangsuoshu = dealData(et_buhegechangsuoshu);
		shiwairumenkou = dealData(et_shiwairumenkou);
		tongshiwaichuangkou = dealData(et_tongshiwaichuangkou);
		chufangmen = dealData(et_chufangmen);
		shushijian = dealData(et_shushijian);
		zhijierukoushipinchugui = dealData(et_zhijierukoushipinchugui);
		liangcaijian = dealData(et_liangcaijian);
		zhijierukoushipintandian = dealData(et_zhijierukoushipintandian);
		qita = dealData(et_qita);
		scxszjrkspdcs = dealData(et_scxszjrkspdcs);
		qizhongyouyingchangsuo = dealData(et_qizhongyouyingchangsuo);
		shineimieyingdeng = dealData(et_shineimieyingdeng);
		fangzhibuzhengqueshu = dealData(et_fangzhibuzhengqueshu);
		shineiyingleizishengdi = dealData(et_shineiyingleizishengdi);
		yangxing = dealData(et_yangxing);
		shiwailajirongqi = dealData(et_shiwailajirongqi);
		shiwailajiyangxing = dealData(et_shiwailajiyangxing);
		gonggongcesuo = dealData(et_gonggongcesuo);
		gonggongcesuoyangxing = dealData(et_gonggongcesuoyangxing);
		lajizhongzhuanzhan = dealData(et_lajizhongzhuanzhan);
		lajizhongzhuanzhanyangxing = dealData(et_lajizhongzhuanzhanyangxing);
		jianchalujing = dealData(et_jianchalujing);
		sanzaizishendi = dealData(et_sanzaizishendi);
		sanzaizishendiyangxing = dealData(et_sanzaizishendiyangxing);

		yingBean.setCheckDistance(jianchalujing);
		yingBean.setYingRoom(yangxingfangshu);
		yingBean.setYingNum(chengyingzshu);
		yingBean.setYangXinRongQi(lajizhongzhuanzhanyangxing);
		yingBean.setWindow_FangYing(tongshiwaichuangkou);
		yingBean.setToiletNum(gonggongcesuo);
		yingBean.setToilet_Ying(gonggongcesuoyangxing);
		yingBean.setTandian(zhijierukoushipintandian);
		yingBean.setStation_Ying(lajizhongzhuanzhanyangxing);
		yingBean.setShushiRoom(shushijian);
		yingBean.setSanZaiYangXinNum(sanzaizishendiyangxing);
		yingBean.setYangXinRongQi(shiwailajiyangxing);
		yingBean.setSanZaiLaJiNum(sanzaizishendi);
		yingBean.setCheckRoom(jianchafangshu);
		yingBean.setDoor_FangYing(chufangmen);
		yingBean.setChuGui_FangYing(zhijierukoushipinchugui);
		yingBean.setFoodPlaceFly(qizhongyouyingchangsuo);
		yingBean.setFangYingBadPlace(buhegechangsuoshu);
		yingBean.setFoodPlaceNum(scxszjrkspdcs);
		yingBean.setGate_FangYing(shiwairumenkou);
		yingBean.setFangYingPlace(jianchachangsuoshu);
		yingBean.setLiangcaiRoom(liangcaijian);
		yingBean.setQiTa_FangYing(qita);
		yingBean.setInnerZhiShengDi(shineiyingleizishengdi);
		yingBean.setInnerYangXin(yangxing);
		yingBean.setLampNum(shineimieyingdeng);
		yingBean.setLampBadPlaceNum(fangzhibuzhengqueshu);
		yingBean.setLaJiRongQiNum(shiwailajirongqi);
		yingBean.setLaJiStation(lajizhongzhuanzhan);
	}

	private int dealData(EditText et) {
		return Integer.valueOf(TextUtils.isEmpty(et.getText().toString().trim()) ? "0" :
			et.getText().toString().trim());
	}

	private boolean verifyInput() {
		if (yangxingfangshu > jianchafangshu) {
			ToastUtils.showToast("<阳性房间数填写>不合法");
			return false;
		}

		if (buhegechangsuoshu > jianchachangsuoshu) {
			ToastUtils.showToast("<不合格场所数填写>不合法");
			return false;
		}

		if (qizhongyouyingchangsuo > scxszjrkspdcs) {
			ToastUtils.showToast("<其中:有蝇场所数填写>不合法");
			return false;
		}

		if (fangzhibuzhengqueshu > shineimieyingdeng) {
			ToastUtils.showToast("<灭蝇灯放置不正确数填写>不合法");
			return false;
		}

		if (shiwailajiyangxing > shiwailajirongqi) {
			ToastUtils.showToast("<室外垃圾容器数填写>不合法");
			return false;
		}

		if (gonggongcesuoyangxing > gonggongcesuo) {
			ToastUtils.showToast("<公共厕所数填写>不合法");
			return false;
		}

		if (lajizhongzhuanzhanyangxing > lajizhongzhuanzhan) {
			ToastUtils.showToast("<垃圾中转站数填写>不合法");
			return false;
		}

		if (sanzaizishendiyangxing > sanzaizishendi) {
			ToastUtils.showToast("<散在孳生地数填写>不合法");
			return false;
		}

		return true;
	}
}
