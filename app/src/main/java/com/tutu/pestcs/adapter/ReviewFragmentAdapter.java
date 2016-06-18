package com.tutu.pestcs.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.fragment.review.CockFragment;
import com.tutu.pestcs.fragment.review.FliesFragment;
import com.tutu.pestcs.fragment.review.MosquitosFragment;
import com.tutu.pestcs.fragment.review.MouseFragment;
import com.tutu.pestcs.fragment.review.NoteFragment;

import java.util.ArrayList;

/**
 * Created by tutu on 16/4/8.
 */
public class ReviewFragmentAdapter extends TFragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    private ArrayList<String> TITLES = new ArrayList<>();//{"鼠", "蝇", "蚊", "蟑螂", "备注"};
    private boolean[] cheakItems = {true, true, true, true};
    private String uniticode;

    public ReviewFragmentAdapter(FragmentManager fm, boolean[] cheakItems, String uniticode) {
        super(fm);
        this.cheakItems = cheakItems;
        this.uniticode = uniticode;
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putString(ActivityJumpParams.UNITYCODE, uniticode);
        if (cheakItems[0]) {
            MouseFragment mouseFragment = new MouseFragment();
            mouseFragment.setArguments(bundle);
            fragments.add(mouseFragment);
            TITLES.add("鼠");
        }
        if (cheakItems[1]) {
            FliesFragment fliesFragment = new FliesFragment();
            fliesFragment.setArguments(bundle);
            fragments.add(fliesFragment);
            TITLES.add("蝇");
        }
        if (cheakItems[2]) {
            MosquitosFragment mosquitosFragment = new MosquitosFragment();
            mosquitosFragment.setArguments(bundle);
            fragments.add(mosquitosFragment);
            TITLES.add("蚊");
        }
        if (cheakItems[3]) {
            CockFragment cockFragment = new CockFragment();
            cockFragment.setArguments(bundle);
            fragments.add(cockFragment);
            TITLES.add("蟑螂");
        }

        NoteFragment noteFragment = new NoteFragment();
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