package com.tutu.pestcs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.GridView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.FocusTypeGVAdapter;
import com.tutu.pestcs.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class FocusTypeActivity extends BaseActivity {
	@Bind(R.id.gv_focus)
	GridView gv_focus;
	@Bind(R.id.gv_normal)
	GridView gv_normal;

	private FocusTypeGVAdapter focusAdapter;
	private FocusTypeGVAdapter normalAadapter;

	private List<String> focusData = new ArrayList<>();
	private List<String> normalData = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {

	}

	@Override
	public void initData() {
		readData();


		focusAdapter = new FocusTypeGVAdapter(this, focusData);
		normalAadapter = new FocusTypeGVAdapter(this, normalData);

		gv_focus.setAdapter(focusAdapter);
		gv_normal.setAdapter(normalAadapter);
	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_focus_type;
	}

	private void readData() {
		// TODO: 16/4/12 读取数据库的重点单位类型数据 填充上面的list集合
	}

	@OnClick(R.id.btn_change)
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_change:
				Intent intent = new Intent(this, ChangeFocusType.class);
				startActivity(intent);
				break;
		}
	}


}
