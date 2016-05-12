package com.tutu.pestcs.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.fragment.statistic.StatisticShuFragment;
import com.tutu.pestcs.fragment.statistic.StatisticWenFragment;
import com.tutu.pestcs.fragment.statistic.StatisticYingFragment;
import com.tutu.pestcs.fragment.statistic.StatisticZhangFragment;

import java.util.ArrayList;

/**
 * Created by tutu on 16/4/8.
 */
public class RealDataStatisticFragmentAdapter extends TFragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    private final String[] TITLES = {"鼠", "蝇", "蟑螂", "蚊"};

    public RealDataStatisticFragmentAdapter(FragmentManager fm,TaskBean bean) {
        super(fm);
        initFragment(bean);
    }

    private void initFragment(TaskBean bean) {
        fragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActivityJumpParams.TASK_BEAN,bean);
        StatisticShuFragment statisticShuFragment = new StatisticShuFragment();
        statisticShuFragment.setArguments(bundle);
        StatisticYingFragment statisticYingFragment = new StatisticYingFragment();
        statisticYingFragment.setArguments(bundle);
        StatisticZhangFragment statisticZhangFragment = new StatisticZhangFragment();
        statisticZhangFragment.setArguments(bundle);
        StatisticWenFragment statisticWenFragment = new StatisticWenFragment();
        statisticWenFragment.setArguments(bundle);
        fragments.add(statisticShuFragment);
        fragments.add(statisticYingFragment);
        fragments.add(statisticZhangFragment);
        fragments.add(statisticWenFragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}