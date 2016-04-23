package com.tutu.pestcs.fragment.statistic;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
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
		switch (msg.what) {
			case 1:  //成功
				break;
			case -1:  //失败
				break;
		}
	}


	@Override
	public void initView() {
		Tasks.executeInBackground(getActivity(), new BackgroundWork<List<ZhangBean>>() {
			@Override
			public List<ZhangBean> doInBackground() throws Exception {
				return readData();
			}
		}, new Completion<List<ZhangBean>>() {
			@Override
			public void onSuccess(Context context, List<ZhangBean> result) {
				Message msg = new Message();
				msg.what = 1;
				sendMessage(msg);
			}

			@Override
			public void onError(Context context, Exception e) {
				Message msg = new Message();
				msg.what = -1;
				sendMessage(msg);
			}
		});

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
