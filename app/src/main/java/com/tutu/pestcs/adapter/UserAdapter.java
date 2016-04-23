package com.tutu.pestcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.bean.User;

import java.util.List;

/**
 * Created by tutu on 16/4/14.
 */
public class UserAdapter extends BaseAdapter {
	private List<User> data;
	private Context context;

	public UserAdapter(List<User> data, Context context) {
		this.data = data;
		this.context = context;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.user_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.type = (TextView) convertView.findViewById(R.id.tv_type);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(data.get(position).getUserName());
		holder.type.setText(data.get(position).getUserGrade().equals("0") ? "管理员" : "普通用户");
		return convertView;
	}


	class ViewHolder {
		ImageView imageView;
		TextView name;
		TextView type;
	}
}
