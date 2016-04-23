package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.adapter.TaskAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.event.TaskEvent;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TaskActivity extends BaseActivity {
	@Bind(R.id.lv_task)
	ListView lv_task;
	@Bind(R.id.ll_back)
	LinearLayout llBack;
	@Bind(R.id.ll_add)
	LinearLayout llAdd;
	@Bind(R.id.tv_empty)
	TextView tvEmpty;
	TaskAdapter adapter;
	private List<Subscription> subscriptions;


	private List<TaskBean> data = new ArrayList<>();

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		readData();
		RegistAddTaskEvent();
	}

	private void readData() {
		Tasks.executeInBackground(this, new BackgroundWork<List<TaskBean>>() {
			@Override
			public List<TaskBean> doInBackground() throws Exception {
				return TaskDao.queryAll();
			}
		}, new Completion<List<TaskBean>>() {
			@Override
			public void onSuccess(Context context, List<TaskBean> result) {
				if (result == null || result.isEmpty()) {
					lv_task.setEmptyView(tvEmpty);
				} else {
					data = result;
					updateUI();
				}
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});
	}

	private synchronized void updateUI() {
		LogUtil.e("query all task = " + data.toString());
		adapter = new TaskAdapter(data, this, R.layout.set_current_task_item);
		lv_task.setAdapter(adapter);
		lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(TaskActivity.this, TaskDetail.class);
				intent.putExtra(ActivityJumpParams.TASK_BEAN, data.get(position));
				startActivity(intent);
			}
		});

	}


	private void RegistAddTaskEvent() {
		subscriptions = new ArrayList<>();
		subscriptions.add(RxBus.obtainEvent(TaskEvent.class).
			observeOn(AndroidSchedulers.mainThread()).
			subscribe(new Action1<TaskEvent>() {
				@Override
				public void call(TaskEvent taskEvent) {

					readData();

				}
			}, new Action1<Throwable>() {
				@Override
				public void call(Throwable throwable) {

				}
			}));
	}

	@Override
	public void initData() {

	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_task;
	}

	@OnClick({R.id.ll_back, R.id.ll_add})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.ll_back:
				finish();
				break;
			case R.id.ll_add:
				Intent intent = new Intent(this, AddTask.class);
				startActivity(intent);
				break;
		}
	}

}
