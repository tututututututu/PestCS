package com.tutu.pestcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.bean.ExtendSortUnitBean;

import java.util.List;

/**
 * Created by tutu on 16/4/8.
 */
public class FocusTypeGVAdapter extends BaseAdapter {
	private List<ExtendSortUnitBean> data;
	private Context mContext;


	public FocusTypeGVAdapter(Context context, List<ExtendSortUnitBean> data) {
		this.data = data;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.table_tv, null);
			holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_text.setText(data.get(position).getClassName());

		return convertView;
	}

	static class ViewHolder {
		TextView tv_text;
	}
}
