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
 */
public class MouseFragment extends BaseFragment {
	@Bind(R.id.et_jianchashu)
	EditText et_jianchashu;
	int jianchashu = 0;
	@Bind(R.id.et_yangxing)
	EditText et_yangxing;
	int yangxing = 0;
	@Bind(R.id.et_shufen)
	EditText et_shufen;
	int shufen = 0;
	@Bind(R.id.et_shudong)
	EditText et_shudong;
	int shudong = 0;
	@Bind(R.id.et_shudao)
	EditText et_shudao;
	int shudao = 0;
	@Bind(R.id.et_yaoheng)
	EditText et_yaoheng;
	int yaoheng = 0;
	@Bind(R.id.et_shuzhua)
	EditText et_shuzhua;
	int shuzhua = 0;
	@Bind(R.id.et_shushi)
	EditText et_shushi;
	int shushi = 0;
	@Bind(R.id.et_huoshu)
	EditText et_huoshu;
	int huoshu = 0;
	@Bind(R.id.et_sheshi_rooms)
	EditText et_sheshi_rooms;
	int sheshi_rooms = 0;
	@Bind(R.id.et_sheshi_buhege)
	EditText et_sheshi_buhege;
	int sheshi_buhege = 0;
	@Bind(R.id.et_chushuikou)
	EditText et_chushuikou;
	int chushuikou = 0;
	@Bind(R.id.et_paishuigou)
	EditText et_paishuigou;
	int paishuigou = 0;
	@Bind(R.id.et_dilou)
	EditText et_dilou;
	int dilou = 0;
	@Bind(R.id.et_paifengshan)
	EditText et_paifengshan;
	int paifengshan = 0;
	@Bind(R.id.et_chuanghu)
	EditText et_chuanghu;
	int chuanghu = 0;
	@Bind(R.id.et_menfeng)
	EditText et_menfeng;
	int menfeng = 0;
	@Bind(R.id.et_tongfengkou)
	EditText et_tongfengkou;
	int tongfengkou = 0;
	@Bind(R.id.et_kongdong)
	EditText et_kongdong;
	int kongdong = 0;
	@Bind(R.id.et_mumen)
	EditText et_mumen;
	int mumen = 0;
	@Bind(R.id.et_dangshuban)
	EditText et_dangshuban;
	int dangshuban = 0;
	@Bind(R.id.et_jianchalujing)
	EditText et_jianchalujing;
	int jianchalujing = 0;
	@Bind(R.id.et_shujiyangxing)
	EditText et_shujiyangxing;
	int shujiyangxing = 0;
	@Bind(R.id.et_wai_shufen)
	EditText et_wai_shufen;
	int wai_shufen = 0;
	@Bind(R.id.et_wai_shudong)
	EditText et_wai_shudong;
	int wai_shudong = 0;
	@Bind(R.id.et_wai_shudao)
	EditText et_wai_shudao;
	int wai_shudao = 0;
	@Bind(R.id.et_wai_yaoheng)
	EditText et_wai_yaoheng;
	int wai_yaoheng = 0;
	@Bind(R.id.et_wai_daotu)
	EditText et_wai_daotu;
	int wai_daotu = 0;
	@Bind(R.id.et_wai_shushi)
	EditText et_wai_shushi;
	int wai_shushi = 0;
	@Bind(R.id.et_wai_huoshu)
	EditText et_wai_huoshu;
	int wai_huoshu = 0;
	@Bind(R.id.et_mieshuzhan)
	EditText et_mieshuzhan;
	int mieshuzhan = 0;
	@Bind(R.id.et_wushuyao)
	EditText et_wushuyao;
	int wushuyao = 0;
	@Bind(R.id.et_shuyaowuxiao)
	EditText et_shuyaowuxiao;
	int shuyaowuxiao = 0;
	@Bind(R.id.et_fangzhibuzhengque)
	EditText et_fangzhibuzhengque;
	int fangzhibuzhengque = 0;
	@Bind(R.id.et_wujinshipai)
	EditText et_wujinshipai;
	int wujinshipai = 0;

	@Bind(R.id.ll_shuji)
	LinearLayout ll_shuji;
	@Bind(R.id.ll_sheshi_buhege)
	LinearLayout ll_sheshi_buhege;
	@Bind(R.id.ll_waihuanjingshuji)
	LinearLayout ll_waihuanjingshuji;
	@Bind(R.id.ll_qizhong)
	LinearLayout ll_qizhong;


	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView() {
		et_yangxing.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(et_yangxing.getText().toString().trim())) {
					yangxing = Integer.valueOf(s.toString().trim());
					if (yangxing > 0) {
						ll_shuji.setVisibility(View.VISIBLE);
					} else {
						ll_shuji.setVisibility(View.GONE);
					}
				} else {
					ll_shuji.setVisibility(View.GONE);
				}


			}
		});

		et_sheshi_buhege.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(et_sheshi_buhege.getText().toString().trim())) {
					if (Integer.valueOf(et_sheshi_buhege.getText().toString().trim()) > 0) {
						ll_sheshi_buhege.setVisibility(View.VISIBLE);
					} else {
						ll_sheshi_buhege.setVisibility(View.GONE);
					}
				} else {
					ll_sheshi_buhege.setVisibility(View.GONE);
				}
			}
		});

		et_shujiyangxing.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(et_shujiyangxing.getText().toString().trim())) {
					if (Integer.valueOf(et_shujiyangxing.getText().toString().trim()) > 0) {
						ll_waihuanjingshuji.setVisibility(View.VISIBLE);
					} else {
						ll_waihuanjingshuji.setVisibility(View.GONE);
					}
				} else {
					ll_waihuanjingshuji.setVisibility(View.GONE);
				}
			}
		});

		et_mieshuzhan.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(et_mieshuzhan.getText().toString().trim())) {
					if (Integer.valueOf(et_mieshuzhan.getText().toString().trim()) > 0) {
						ll_qizhong.setVisibility(View.VISIBLE);
					} else {
						ll_qizhong.setVisibility(View.GONE);
					}
				}
			}
		});
	}

	@Override
	public int getLayoutID() {
		return R.layout.insert_mouse_fragment;
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
