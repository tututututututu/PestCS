package com.tutu.pestcs.fragment.statistic;

import android.os.Message;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.ZhangBean;

import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 数检查数据统计老鼠
 */
public class StatisticZhangFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<ZhangBean> zhangdata;

    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {


    }

    private List<ZhangBean> readData() {
        // TODO: 16/4/19 读取数据库数据
        return null;
    }

    @Override
    public int getLayoutID() {
        return R.layout.statistic_zhang_fragment;
    }

    private void initData() {

    }
}
