package com.tutu.pestcs.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
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
	private CheakInsertBean cheakInsertBean;

	public InsertFragmentAdapter(FragmentManager fm, boolean[] cheakItems, CheakInsertBean cheakInsertBean) {
		super(fm);
		this.cheakItems = cheakItems;
		this.cheakInsertBean = cheakInsertBean;
		initFragment();
	}

	private void initFragment() {
		fragments = new ArrayList<>();
		Bundle bundle = new Bundle();
		bundle.putParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN, cheakInsertBean);
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
			CockFragment cockFragment = new CockFragment();
			cockFragment.setArguments(bundle);
			fragments.add(cockFragment);
			TITLES.add("蚊");
		}
		if (cheakItems[3]) {
			MosquitosFragment mosquitosFragment = new MosquitosFragment();
			mosquitosFragment.setArguments(bundle);
			fragments.add(mosquitosFragment);
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