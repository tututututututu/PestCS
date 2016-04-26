package com.tutu.pestcs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tutu.pestcs.fragment.insert.CockFragment;
import com.tutu.pestcs.fragment.insert.FliesFragment;
import com.tutu.pestcs.fragment.insert.MosquitosFragment;
import com.tutu.pestcs.fragment.insert.MouseFragment;
import com.tutu.pestcs.fragment.insert.NoteFragment;

import java.util.ArrayList;

/**
 * Created by tutu on 16/4/8.
 */
public class InsertFragmentAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments;

	private ArrayList<String> TITLES = new ArrayList<>();//{"鼠", "蝇", "蚊", "蟑螂", "备注"};
	private boolean[] cheakItems = {true, true, true, true};

	public InsertFragmentAdapter(FragmentManager fm, boolean[] cheakItems) {
		super(fm);
		this.cheakItems = cheakItems;
		initFragment();
	}

	private void initFragment() {
		fragments = new ArrayList<>();
		if (cheakItems[0]) {
			fragments.add(new MouseFragment());
			TITLES.add("鼠");
		}
		if (cheakItems[1]) {
			fragments.add(new FliesFragment());
			TITLES.add("蝇");
		}
		if (cheakItems[2]) {
			fragments.add(new CockFragment());
			TITLES.add("蚊");
		}
		if (cheakItems[3]) {
			fragments.add(new MosquitosFragment());
			TITLES.add("蟑螂");
		}

		fragments.add(new NoteFragment());
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