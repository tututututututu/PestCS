package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.ProgressFragmentAdapter;
import com.tutu.pestcs.base.BaseActivity;

import butterknife.Bind;

/**
 * 数据统计
 */

public class DataStatistic extends BaseActivity {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    ProgressFragmentAdapter adapter;


    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        adapter = new ProgressFragmentAdapter(getSupportFragmentManager());
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
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_data_statistic;
    }
}
