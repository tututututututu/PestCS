package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.MainActivity;
import com.tutu.pestcs.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/17.
 */
public class NoteFragment extends BaseFragment {
	@Bind(R.id.et_note)
	EditText et_note;


	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView() {

	}

	@Override
	public int getLayoutID() {
		return R.layout.note_fragment;
	}


	@OnClick({R.id.btn_exit, R.id.btn_save})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.btn_exit:
				((MainActivity) getActivity()).toFragment(0);
				break;
			case R.id.btn_save:
				// TODO: 16/4/17 插入表
				saveData();
				break;
		}
	}

	private void saveData() {
		String note = et_note.getText().toString().trim();
		if (TextUtils.isEmpty(note)) {
			svProgressHUD.showErrorWithStatus("请输入内容");
		} else {
			//保存
		}
	}
}
