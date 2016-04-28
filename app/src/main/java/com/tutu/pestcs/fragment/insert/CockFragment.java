package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.MainActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.widget.AlertDialogUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 * 蟑螂
 */
public class CockFragment extends BaseFragment {
	@Bind(R.id.et_jianchafangshu)
	EditText et_jianchafangshu;
	@Bind(R.id.et_chengchongyangxingfangjianshu)
	EditText et_chengchongyangxingfangjianshu;
	@Bind(R.id.et_dalian)
	EditText et_dalian;
	@Bind(R.id.et_xiaolian)
	EditText et_xiaolian;
	@Bind(R.id.et_luanqiaoxiangxingfangjianshu)
	EditText et_luanqiaoxiangxingfangjianshu;
	@Bind(R.id.et_chahuoluanqiaoshu)
	EditText et_chahuoluanqiaoshu;
	@Bind(R.id.et_zhangjiyangxingfangjianshu)
	EditText et_zhangjiyangxingfangjianshu;
	@Bind(R.id.et_chongshi)
	EditText et_chongshi;
	@Bind(R.id.et_canpian)
	EditText et_canpian;
	@Bind(R.id.et_tuipi)
	EditText et_tuipi;
	@Bind(R.id.et_kongluanqiaoke)
	EditText et_kongluanqiaoke;
	@Bind(R.id.et_zhanglangfenbian)
	EditText et_zhanglangfenbian;


	private CheakInsertBean cheakInsertBean;
	private WenBean wenBean = new WenBean();


	@Override
	public void handleMessage(Message msg) {

	}


	@Override
	public void initView() {
		cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);

		wenBean.setUnitCode(cheakInsertBean.getUnitCode());

	}

	@Override
	public int getLayoutID() {
		return R.layout.cock_insert_fragment;
	}


	private void initData() {

	}

	@OnClick({R.id.btn_exit, R.id.btn_save})
	public void OnClick(View view) {
		switch (view.getId()) {
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
			case R.id.btn_save:
				formatData();
				if (verifyInput()) {
					WenDao.saveOrUpdate(wenBean);
				}
				break;
		}
	}

	private void formatData() {

	}

	private int dealData(EditText et) {
		return Integer.valueOf(TextUtils.isEmpty(et.getText().toString().trim()) ? "0" :
			et.getText().toString().trim());
	}

	private boolean verifyInput() {
		return true;
	}
}
