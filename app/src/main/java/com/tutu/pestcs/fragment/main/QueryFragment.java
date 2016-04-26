package com.tutu.pestcs.fragment.main;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.widget.UnitTypeDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 */
public class QueryFragment extends BaseFragment {


	@Bind(R.id.et_danweileixing)
	TextView etDanweileixing;
	@Bind(R.id.rb_zhongdian_buxian)
	RadioButton rbZhongdianBuxian;
	@Bind(R.id.rb_zhongdian_shi)
	RadioButton rbZhongdianShi;
	@Bind(R.id.rb_zhongdian_fou)
	RadioButton rbZhongdianFou;
	@Bind(R.id.rg_zhongdian)
	RadioGroup rgZhongdian;
	@Bind(R.id.rb_xiangmu_shu)
	RadioButton rbXiangmuShu;
	@Bind(R.id.rb_xiangmu_ying)
	RadioButton rbXiangmuYing;
	@Bind(R.id.rb_xiangmu_zhang)
	RadioButton rbXiangmuZhang;
	@Bind(R.id.rb_xiangmu_wen)
	RadioButton rbXiangmuWen;
	@Bind(R.id.rg_xiangmu)
	RadioGroup rgXiangmu;
	@Bind(R.id.tv_tv_3)
	TextView tvTv3;
	@Bind(R.id.rb_3_1)
	RadioButton rb31;
	@Bind(R.id.rb_3_2)
	RadioButton rb32;
	@Bind(R.id.rb_3_3)
	RadioButton rb33;
	@Bind(R.id.rg_3)
	RadioGroup rg3;
	@Bind(R.id.tv_tv_4)
	TextView tvTv4;
	@Bind(R.id.rb_4_1)
	RadioButton rb41;
	@Bind(R.id.rb_4_2)
	RadioButton rb42;
	@Bind(R.id.rb_4_3)
	RadioButton rb43;
	@Bind(R.id.rg_4)
	RadioGroup rg4;
	@Bind(R.id.tv_tv_5)
	TextView tvTv5;
	@Bind(R.id.rb_5_1)
	RadioButton rb51;
	@Bind(R.id.rb_5_2)
	RadioButton rb52;
	@Bind(R.id.rb_5_3)
	RadioButton rb53;
	@Bind(R.id.rg_5)
	RadioGroup rg5;
	@Bind(R.id.btn_query)
	Button btnQuery;

	@Override
	public void handleMessage(Message msg) {

	}


	@Override
	public void initView() {
		rgXiangmu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.rb_xiangmu_shu:
						tvTv3.setText("室内鼠迹");
						tvTv4.setText("防鼠设施");
						tvTv5.setText("外环境鼠迹");
						rb41.setText("合格");
						rb42.setText("不合格");
						rb43.setText("不限");
						break;
					case R.id.rb_xiangmu_ying:
						tvTv3.setText("室内成蝇");
						tvTv4.setText("防蝇设施");
						tvTv5.setText("室外蝇类滋生地");
						rb41.setText("合格");
						rb42.setText("不合格");
						rb43.setText("不限");
						break;
					case R.id.rb_xiangmu_zhang:
						tvTv3.setText("蟑螂成虫");
						tvTv4.setText("蟑螂卵鞘");
						tvTv5.setText("蟑迹");
						rb41.setText("阳性");
						rb42.setText("阴性");
						rb43.setText("不限");
						break;
					case R.id.rb_xiangmu_wen:
						tvTv3.setText("小型积水蚊幼");
						tvTv4.setText("特殊场所人诱蚊");
						tvTv5.setText("大中型水体采样");
						rb41.setText("阳性");
						rb42.setText("阴性");
						rb43.setText("不限");
						break;
				}
			}
		});

		rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.rb_3_1:
						break;
					case R.id.rb_3_2:
						break;
					case R.id.rb_3_3:
						break;
				}
			}
		});

		rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

			}
		});

		rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

			}
		});
	}

	@Override
	public int getLayoutID() {
		return R.layout.query_fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}


	@OnClick({R.id.et_danweileixing, R.id.btn_query})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.et_danweileixing:
				//单位类型的dialog
				query();
				break;
			case R.id.btn_query:
				query();
				break;
		}
	}

	private void query() {
		UnitTypeDialog.getInstace(mActivityContext, new UnitTypeDialog.onDialogClick() {
			@Override
			public void onCofirm(String cheakIndex,String cheakString) {
				Toast.makeText(getActivity(), "" + cheakIndex, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onCancle() {

			}
		}).show();
	}
}

