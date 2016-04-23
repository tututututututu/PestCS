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

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 * 苍蝇
 */
public class FliesFragment extends BaseFragment {
	@Bind(R.id.et_jianchafangshu)
	EditText et_jianchafangshu;
	@Bind(R.id.et_yangxingfangshu)
	EditText et_yangxingfangshu;
	@Bind(R.id.et_chengyingzshu)
	EditText et_chengyingzshu;
	@Bind(R.id.et_jianchachangsuoshu)
	EditText et_jianchachangsuoshu;
	@Bind(R.id.et_buhegechangsuoshu)
	EditText et_buhegechangsuoshu;
	@Bind(R.id.et_shiwairumenkou)
	EditText et_shiwairumenkou;
	@Bind(R.id.et_tongshiwaichuangkou)
	EditText et_tongshiwaichuangkou;
	@Bind(R.id.et_chufangmen)
	EditText et_chufangmen;
	@Bind(R.id.et_shushijian)
	EditText et_shushijian;
	@Bind(R.id.et_zhijierukoushipinchugui)
	EditText et_zhijierukoushipinchugui;
	@Bind(R.id.et_liangcaijian)
	EditText et_liangcaijian;
	@Bind(R.id.et_zhijierukoushipintandian)
	EditText et_zhijierukoushipintandian;
	@Bind(R.id.et_qita)
	EditText et_qita;
	@Bind(R.id.et_scxszjrkspdcs)
	EditText et_scxszjrkspdcs;
	@Bind(R.id.et_qizhongyouyingchangsuo)
	EditText et_qizhongyouyingchangsuo;
	@Bind(R.id.et_shineimieyingdeng)
	EditText et_shineimieyingdeng;
	@Bind(R.id.et_fangzhibuzhengqueshu)
	EditText et_fangzhibuzhengqueshu;
	@Bind(R.id.et_shineiyingleizishengdi)
	EditText et_shineiyingleizishengdi;
	@Bind(R.id.et_yangxing)
	EditText et_yangxing;
	@Bind(R.id.et_shiwailajirongqi)
	EditText et_shiwailajirongqi;
	@Bind(R.id.et_shiwailajiyangxing)
	EditText et_shiwailajiyangxing;
	@Bind(R.id.et_gonggongcesuo)
	EditText et_gonggongcesuo;
	@Bind(R.id.et_gonggongcesuoyangxing)
	EditText et_gonggongcesuoyangxing;
	@Bind(R.id.et_lajizhongzhuanzhan)
	EditText et_lajizhongzhuanzhan;
	@Bind(R.id.et_lajizhongzhuanzhanyangxing)
	EditText et_lajizhongzhuanzhanyangxing;
	@Bind(R.id.et_jianchalujing)
	EditText et_jianchalujing;
	@Bind(R.id.et_sanzaizishendi)
	EditText et_sanzaizishendi;
	@Bind(R.id.et_sanzaizishendiyangxing)
	EditText et_sanzaizishendiyangxing;

	@Bind(R.id.ll_fangyingsheshibuhegebuwei)
	LinearLayout ll_fangyingsheshibuhegebuwei;


	@Override
	public void handleMessage(Message msg) {
	}


	@Override
	public void initView() {
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


	private void initData() {
	}

	@OnClick({R.id.btn_save, R.id.btn_exit})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.btn_save:
				// TODO: 16/4/17 保存到表
				break;
			case R.id.btn_exit:
				((MainActivity) getActivity()).toFragment(0);
				break;
		}
	}
}
