package com.tutu.pestcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutu.pestcs.R;
import com.tutu.pestcs.bean.IndexGVBean;

import java.util.List;

/**
 * Created by tutu on 16/4/8.
 */
public class IndexGVAdapter extends BaseAdapter {
    private List<IndexGVBean> data;
    private Context mContext;


    public IndexGVAdapter(Context context, List<IndexGVBean> data) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.index_gv_item, null);
            holder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_text.setText(data.get(position).getName());
        Glide.with(mContext.getApplicationContext()).load(data.get(position).getResID()).into(holder.iv_img
        );

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_img;
        TextView tv_text;
    }
}
