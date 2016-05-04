package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.fragment.main.HelpFragment;
import com.tutu.pestcs.fragment.main.IndexFragment;
import com.tutu.pestcs.fragment.main.InsertFragment;
import com.tutu.pestcs.fragment.main.ProgressFragment;
import com.tutu.pestcs.fragment.main.QueryFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    public static final String indexFragmentTag = "indexFragment";
    public static final String progressFragmentTag = "progressFragment";
    public static final String insertFragmentTag = "insertFragment";
    public static final String queryFragmentTag = "queryFragment";
    public static final String helpFragmentTag = "helpFragment";

    @Bind(R.id.rg_tab)
    RadioGroup rg_tab;

    @Bind(R.id.fl_main)
    FrameLayout fl_main;
    ArrayList<Fragment> fragmentList;

    private int currentIndex = 0;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;


    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragmentList = new ArrayList<>();
        IndexFragment indexFragment = new IndexFragment();
        ProgressFragment progressFragment = new ProgressFragment();
        InsertFragment insertFragment = new InsertFragment();
        QueryFragment queryFragment = new QueryFragment();
        HelpFragment helpFragment = new HelpFragment();

        fragmentList.add(indexFragment);
        fragmentList.add(progressFragment);
        fragmentList.add(insertFragment);
        fragmentList.add(queryFragment);
        fragmentList.add(helpFragment);

        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.rb_index:
                        changeFragment(fragmentList.get(currentIndex), fragmentList.get(0));
                        currentIndex = 0;
                        break;
                    case R.id.rb_progress:
                        changeFragment(fragmentList.get(currentIndex), fragmentList.get(1));
                        currentIndex = 1;
                        break;
                    case R.id.rb_insert:
                        changeFragment(fragmentList.get(currentIndex), fragmentList.get(2));
                        currentIndex = 2;
                        break;
                    case R.id.rb_query:
                        changeFragment(fragmentList.get(currentIndex), fragmentList.get(3));
                        currentIndex = 3;
                        break;
                    case R.id.rb_help:
                        changeFragment(fragmentList.get(currentIndex), fragmentList.get(4));
                        currentIndex = 4;
                        break;
                }

            }
        });

        fragmentTransaction = fragmentManager.beginTransaction();
        rg_tab.check(R.id.rb_index);
        fragmentTransaction.add(R.id.fl_main, fragmentList.get(0)).commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void changeFragment(Fragment from, Fragment to) {

        fragmentTransaction.replace(R.id.fl_main, to).commit();
//        if (!to.isAdded()) {    // 先判断是否被add过
//            fragmentTransaction.hide(from).add(R.id.fl_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//        } else {
//            fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//        }


    }

    public void toFragment(int i) {
        switch (i) {
            case 0:
                rg_tab.check(R.id.rb_index);
                break;
            case 1:
                rg_tab.check(R.id.rb_progress);
                break;
            case 2:
                rg_tab.check(R.id.rb_insert);
                break;
            case 3:
                rg_tab.check(R.id.rb_query);
                break;
            case 4:
                rg_tab.check(R.id.rb_help);
                break;
        }
    }
}
