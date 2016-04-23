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

	private final String[] TITLES = {"鼠", "蝇", "蚊", "蟑螂", "备注"};

	public InsertFragmentAdapter(FragmentManager fm) {
		super(fm);
		initFragment();
	}

	private void initFragment() {
		fragments = new ArrayList<>();
		fragments.add(new MouseFragment());
		fragments.add(new FliesFragment());
		fragments.add(new CockFragment());
		fragments.add(new MosquitosFragment());
		fragments.add(new NoteFragment());
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