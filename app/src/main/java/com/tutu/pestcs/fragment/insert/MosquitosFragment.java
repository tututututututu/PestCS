package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.MainActivity;
import com.tutu.pestcs.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 * 蚊子
 */
public class MosquitosFragment extends BaseFragment {
	@Bind(R.id.et_jianchalujing)
	EditText et_jianchalujing;
	@Bind(R.id.et_chajianmiewendeng)
	EditText et_chajianmiewendeng;
	@Bind(R.id.et_chajianxiaoxingjishuiyangxing)
	EditText et_chajianxiaoxingjishuiyangxing;
	@Bind(R.id.et_rongqijishui)
	EditText et_rongqijishui;
	@Bind(R.id.et_rongqijishuiyangxing)
	EditText et_rongqijishuiyangxing;
	@Bind(R.id.et_kengwajishui)
	EditText et_kengwajishui;
	@Bind(R.id.et_kengwajishuiyangxing)
	EditText et_kengwajishuiyangxing;
	@Bind(R.id.et_jingguanchi)
	EditText et_jingguanchi;
	@Bind(R.id.et_jingguanchiyangxing)
	EditText et_jingguanchiyangxing;
	@Bind(R.id.et_paishuijinkoujishui)
	EditText et_paishuijinkoujishui;
	@Bind(R.id.et_paishuijinkoujishuiyangxing)
	EditText et_paishuijinkoujishuiyangxing;
	@Bind(R.id.et_dixiashijishui)
	EditText et_dixiashijishui;
	@Bind(R.id.et_dixiashijishuiyangxing)
	EditText et_dixiashijishuiyangxing;
	@Bind(R.id.et_luntaijishui)
	EditText et_luntaijishui;
	@Bind(R.id.et_luntaijishuiyangxing)
	EditText et_luntaijishuiyangxing;
	@Bind(R.id.et_qita)
	EditText et_qita;
	@Bind(R.id.et_youwenrenci)
	EditText et_youwenrenci;
	@Bind(R.id.et_wenchongtingluocishu)
	EditText et_wenchongtingluocishu;
	@Bind(R.id.et_caiyanggong)
	EditText et_caiyanggong;
	@Bind(R.id.et_yangxinggong)
	EditText et_yangxinggong;
	@Bind(R.id.et_wenyouchongheyonggong)
	EditText et_wenyouchongheyonggong;
	@Bind(R.id.rb_hupo)
	RadioButton rb_hupo;
	@Bind(R.id.rb_heliu)
	RadioButton rb_heliu;
	@Bind(R.id.ll_xiaoxingjishui)
	LinearLayout ll_xiaoxingjishui;


	@Override
	public void handleMessage(Message msg) {

	}


	@Override
	public void initView() {
		et_chajianxiaoxingjishuiyangxing.addTextChangedListener(new TextWatcher() {
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
						ll_xiaoxingjishui.setVisibility(View.VISIBLE);
					} else {
						ll_xiaoxingjishui.setVisibility(View.GONE);
					}
				} else {
					ll_xiaoxingjishui.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	public int getLayoutID() {
		return R.layout.mosquitos_insert_fragment;
	}


	private void initData() {

	}

	@OnClick({R.id.btn_save, R.id.btn_exit})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.btn_exit:
				//回首页
				((MainActivity) getActivity()).toFragment(0);
				break;
			case R.id.btn_save:
				// TODO: 16/4/17 存入表中
				break;
		}
	}
}
