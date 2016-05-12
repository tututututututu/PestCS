package com.tutu.pestcs.fragment.statistic;

import android.os.Message;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.utils.ColorPhrase;

import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 数检查数据统计老鼠
 */
public class StatisticShuFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<WenBean> wendata;

    private TaskBean taskBean;

    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        taskBean = getArguments().getParcelable(ActivityJumpParams.TASK_BEAN);
        readData();
    }

    private void readData() {
        // TODO: 16/4/19 读取数据库数据
        updateUI();
    }

    private void updateUI(){
        CharSequence formatted = ColorPhrase.from("I'm {Chinese}, I love {China}")
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();

        tv_content.setText(formatted);
    }

    @Override
    public int getLayoutID() {
        return R.layout.statistic_shu_fragment;
    }

    private void initData() {

    }
}
