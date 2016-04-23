package com.tutu.pestcs.adapter;

import android.content.Context;

import com.tutu.pestcs.R;
import com.tutu.pestcs.bean.TaskBean;
import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperHolder;

import java.util.List;

/**
 * Created by tutu on 16/4/12.
 */
public class SetCurrentTaskAdapter extends HelperAdapter<TaskBean> {

	public SetCurrentTaskAdapter(List<TaskBean> mList, Context context, int... layoutIds) {
		super(mList, context, layoutIds);
	}

	@Override
	public void HelpConvert(HelperHolder viewHolder, int position, TaskBean s) {
		viewHolder.setText(R.id.tv_city, s.getCityName());
		viewHolder.setText(R.id.tv_cheaker_name, s.getExpertName());
		viewHolder.setText(R.id.tv_task_code, s.getTaskCode());
		viewHolder.setText(R.id.tv_time, s.getStartDate());
	}


}
