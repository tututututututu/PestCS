package com.tutu.pestcs.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.fragment.insert.CockFragment;
import com.tutu.pestcs.fragment.insert.FliesFragment;
import com.tutu.pestcs.fragment.insert.MosquitosFragment;
import com.tutu.pestcs.fragment.insert.MouseFragment;
import com.tutu.pestcs.fragment.insert.NoteFragment;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by tutu on 16/4/8.
 */
public class InsertFragmentAdapter extends TFragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    private ArrayList<String> TITLES = new ArrayList<>();//{"鼠", "蝇", "蚊", "蟑螂", "备注"};
    private boolean[] cheakItems = {true, true, true, true};
    private CheakInsertBean cheakInsertBean;

    public InsertFragmentAdapter(FragmentManager fm, boolean[] cheakItems, CheakInsertBean cheakInsertBean) {
        super(fm);
        this.cheakItems = cheakItems;
        this.cheakInsertBean = cheakInsertBean;
        LogUtil.e("InsertFragmentAdapter 中"+cheakInsertBean.toString());
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN, cheakInsertBean);
        if (cheakItems[0]) {
            MouseFragment mouseFragment = new MouseFragment();
            LogUtil.e("MouseFragment 中"+cheakInsertBean.toString());
            mouseFragment.setArguments(bundle);
            fragments.add(mouseFragment);
            TITLES.add("鼠");
        }
        if (cheakItems[1]) {
            FliesFragment fliesFragment = new FliesFragment();
            LogUtil.e("FliesFragment 中"+cheakInsertBean.toString());
            fliesFragment.setArguments(bundle);
            fragments.add(fliesFragment);
            TITLES.add("蝇");
        }
        if (cheakItems[2]) {
            MosquitosFragment mosquitosFragment = new MosquitosFragment();
            LogUtil.e("MosquitosFragment 中"+cheakInsertBean.toString());
            mosquitosFragment.setArguments(bundle);
            fragments.add(mosquitosFragment);
            TITLES.add("蚊");
        }
        if (cheakItems[3]) {
            CockFragment cockFragment = new CockFragment();
            LogUtil.e("cockFragment 中"+cheakInsertBean.toString());
            cockFragment.setArguments(bundle);
            fragments.add(cockFragment);
            TITLES.add("蟑螂");
        }

        NoteFragment noteFragment = new NoteFragment();
        LogUtil.e("NoteFragment 中"+cheakInsertBean.toString());
        noteFragment.setArguments(bundle);
        fragments.add(noteFragment);
        TITLES.add("备注");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES.get(position);
    }

    @Override
    public int getCount() {
        return TITLES.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}