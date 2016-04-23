package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.SetCurrentTaskAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.TaskDao;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SetCurrenTask extends BaseActivity {
	@Bind(R.id.ll_back)
	LinearLayout llBack;
	@Bind(R.id.iv_img)
	ImageView ivImg;
	@Bind(R.id.tv_city)
	TextView tvCity;
	@Bind(R.id.tv_task_code)
	TextView tvTaskCode;
	@Bind(R.id.tv_cheaker_name)
	TextView tvCheakerName;
	@Bind(R.id.tv_time)
	TextView tvTime;
	@Bind(R.id.lv_task)
	ListView lvTask;
	@Bind(R.id.base_layout)
	LinearLayout baseLayout;
	@Bind(R.id.emptyView)
	View emptyView;
	private SetCurrentTaskAdapter adapter;
	private List<TaskBean> data;
	private TaskBean mCurrentTask;


	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		readData();

	}

	private void readData() {
		Tasks.executeInBackground(this, new BackgroundWork<List<TaskBean>>() {
			@Override
			public List<TaskBean> doInBackground() throws Exception {

				return TaskDao.queryAllExceptCurrent();
			}
		}, new Completion<List<TaskBean>>() {
			@Override
			public void onSuccess(Context context, List<TaskBean> result) {
				if (result == null || result.isEmpty()) {
					//没有数据
					lvTask.setEmptyView(emptyView);
				} else {
					data = result;
					updateTaskUI();
				}

			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});


		Tasks.executeInBackground(this, new BackgroundWork<TaskBean>() {
			@Override
			public TaskBean doInBackground() throws Exception {

				return TaskDao.queryCurrent();
			}
		}, new Completion<TaskBean>() {
			@Override
			public void onSuccess(Context context, TaskBean result) {
				mCurrentTask = result;
				updateCurrent();
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});
	}

	private void updateTaskUI() {
		adapter = new SetCurrentTaskAdapter(data, this, R.layout.set_current_task_item);
		lvTask.setAdapter(adapter);
		lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

				AlertDialog.Builder builder = new AlertDialog.Builder(SetCurrenTask.this);
				builder.setMessage("确定设置为当前任务?");
				builder.setTitle("提示");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//设置
						TaskBean task = data.get(position);
						task.setCurrent(true);
						TaskDao.resetCurrent();
						TaskDao.update(task);
						readData();
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//取消
						dialog.cancel();
					}
				});
				builder.create().show();


				return false;
			}
		});
	}

	private void updateCurrent() {
		if (mCurrentTask == null) {
			emptyView.setVisibility(View.VISIBLE);
		} else {
			emptyView.setVisibility(View.GONE);
			tvCheakerName.setText(mCurrentTask.getExpertName());
			tvCity.setText(mCurrentTask.getCityName());
			tvTaskCode.setText(mCurrentTask.getTaskCode());
			tvTime.setText(mCurrentTask.getStartDate());
		}
	}

	@Override
	public void initData() {

	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_set_curren_task;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@OnClick(R.id.ll_back)
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ll_back:
				finish();
				break;
		}
	}
}
