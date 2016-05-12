package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.RealDataStatisticFragmentAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.TaskDao;

import butterknife.Bind;

public class StatisticsActivity extends BaseActivity {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    RealDataStatisticFragmentAdapter adapter;
    @Bind(R.id.tv_task_code)
    TextView tvTaskCode;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_cheaker_name)
    TextView tvCheakerName;
    @Bind(R.id.base_layout)
    LinearLayout baseLayout;

    TaskBean taskBean;

    @Override
    public int getLayoutID() {
        return R.layout.activity_statistics;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        taskBean =  TaskDao.queryCurrent();
        if (taskBean==null){
            svProgressHUD.showErrorWithStatus("您还没有设置当前任务!");

            pager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    svProgressHUD.dismissImmediately();
                    finish();
                }
            },2000);
        }else{
            InitUI();
        }
    }

    private void InitUI() {
        tvTaskCode.setText("考评任务编号:"+taskBean.getTaskCode());
        tvCheakerName.setText("检查人员:"+taskBean.getExpertName());
        tvCity.setText("考评城市:"+taskBean.getCityName());
        tvTime.setText("考评开始时间:"+taskBean.getStartDate());
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RealDataStatisticFragmentAdapter(getSupportFragmentManager(),taskBean);
        pager.setAdapter(adapter);
        tabs.setShouldExpand(true);
        tabs.setUnderlineColorResource(R.color.main_bg2);
        tabs.setIndicatorColorResource(R.color.colorPrimary);
        tabs.setIndicatorHeight((int) getResources().getDimension(R.dimen.dp2));
        tabs.setTextColor(R.color.declare_text);
        tabs.setTextSize((int) getResources().getDimension(R.dimen.sp12));
        tabs.setViewPager(pager);
        //tabs.setTabBackground(); //设置点击时的颜色变化
        tabs.setUnderlineHeight(2);
    }
}
