package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RadioButton;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.UnitsType;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangeFocusType extends BaseActivity {
	@Bind(R.id.rb1)
	RadioButton rb1;
	@Bind(R.id.rb2)
	RadioButton rb2;
	@Bind(R.id.rb3)
	RadioButton rb3;
	@Bind(R.id.rb4)
	RadioButton rb4;
	@Bind(R.id.rb5)
	RadioButton rb5;
	@Bind(R.id.rb6)
	RadioButton rb6;
	@Bind(R.id.rb7)
	RadioButton rb7;
	@Bind(R.id.rb8)
	RadioButton rb8;
	@Bind(R.id.rb9)
	RadioButton rb9;
	@Bind(R.id.rb10)
	RadioButton rb10;
	@Bind(R.id.rb11)
	RadioButton rb11;
	@Bind(R.id.rb12)
	RadioButton rb12;
	@Bind(R.id.rb13)
	RadioButton rb13;
	@Bind(R.id.rb14)
	RadioButton rb14;
	@Bind(R.id.rb15)
	RadioButton rb15;
	@Bind(R.id.rb16)
	RadioButton rb16;
	@Bind(R.id.rb17)
	RadioButton rb17;
	@Bind(R.id.rb18)
	RadioButton rb18;

	private List<UnitsType> data;

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		data = readTypeData();
	}

	private List<UnitsType> readTypeData() {
		// TODO: 16/4/12 读取单位类型  重点非重点等
		return null;
	}

	@Override
	public void initData() {

	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_change_focus_type;
	}

	@OnClick({R.id.ll_back, R.id.ll_confirm})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ll_back:
				break;
			case R.id.ll_confirm:
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
