package com.tutu.pestcs.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.ChangePsw;
import com.tutu.pestcs.activity.DataManageActivitay;
import com.tutu.pestcs.activity.FocusTypeActivity;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.activity.LoginActivity;
import com.tutu.pestcs.activity.SetCurrenTask;
import com.tutu.pestcs.activity.StatisticsActivity;
import com.tutu.pestcs.activity.TaskActivity;
import com.tutu.pestcs.activity.UserActivity;
import com.tutu.pestcs.adapter.IndexGVAdapter;
import com.tutu.pestcs.app.AppUtils;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.IndexGVBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 */
public class IndexFragment extends BaseFragment {
    @Bind(R.id.gv)
    GridView gridView;

    private IndexGVAdapter adapter;
    private String[] text = {"重点类型", "当前任务", "任务设置", "数据录入", "数据统计", "数据管理", "用户管理", "修改密码", "退出系统"};
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R
            .mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap
            .ic_launcher, R
            .mipmap.ic_launcher};

    @Override
    public void handleMessage(Message msg) {
    }

    @Override
    public void initView() {
        initGVdata();
        initAction();
    }

    private void initAction() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(mActivityContext, FocusTypeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mActivityContext, SetCurrenTask.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mActivityContext, TaskActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mActivityContext, InsertActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(mActivityContext, StatisticsActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(mActivityContext, DataManageActivitay.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(mActivityContext, UserActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(mActivityContext, ChangePsw.class);
                        startActivity(intent);
                        break;
                    case 8:
                        logout(mActivityContext);
                        break;
                }
            }
        });
    }

    private void logout(Context context) {
        AppUtils.Logout(context);
        Intent intent = new Intent(mActivityContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.index_fragment;
    }

    private void initGVdata() {
        List<IndexGVBean> data = new ArrayList<>();

        for (int i = 0; i < text.length; i++) {
            data.add(new IndexGVBean(text[i], img[i]));
        }

        adapter = new IndexGVAdapter(mActivityContext, data);
        gridView.setAdapter(adapter);
    }
}
