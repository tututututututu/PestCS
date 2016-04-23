package com.tutu.pestcs.fragment.main;

import android.os.Message;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.DataStatisticFragmentAdapter;
import com.tutu.pestcs.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 */
public class ProgressFragment extends BaseFragment {
	@Bind(R.id.tabs)
	PagerSlidingTabStrip tabs;
	@Bind(R.id.pager)
	ViewPager pager;
	DataStatisticFragmentAdapter adapter;


	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView() {
		adapter = new DataStatisticFragmentAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		tabs.setShouldExpand(true);
		tabs.setUnderlineColorResource(R.color.main_bg2);
		tabs.setIndicatorColorResource(R.color.colorPrimary);
		tabs.setIndicatorHeight((int) getResources().getDimension(R.dimen.dp2));
		tabs.setTextColor(R.color.declare_text);
		tabs.setTextSize((int) getResources().getDimension(R.dimen.sp14));
		tabs.setViewPager(pager);
		//tabs.setTabBackground(); //设置点击时的颜色变化
		tabs.setUnderlineHeight(2);
	}

	@Override
	public int getLayoutID() {
		return R.layout.progress_fragment;
	}
}
