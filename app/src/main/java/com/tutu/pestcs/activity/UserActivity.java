package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.adapter.UserAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.User;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.UserDao;
import com.tutu.pestcs.event.AddUserEvent;
import com.tutu.pestcs.event.UserEidteEvent;

import org.xutils.common.util.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class UserActivity extends BaseActivity {
	@Bind(R.id.lv_users)
	ListView lv_users;
	@Bind(R.id.tv_empty)
	View empty;
	private UserAdapter adapter;
	private List<User> users;

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		readData();
		RegistAddUserEvent();
		registUserEditeEvent();
	}

	private void RegistAddUserEvent() {
		subscriptions.add(RxBus.obtainEvent(AddUserEvent.class).
			observeOn(AndroidSchedulers.mainThread()).
			subscribe(new Action1<AddUserEvent>() {
				@Override
				public void call(AddUserEvent changeAvatarEvent) {

					readData();

				}
			}, new Action1<Throwable>() {
				@Override
				public void call(Throwable throwable) {

				}
			}));
	}

	private void registUserEditeEvent(){
		subscriptions.add(RxBus.obtainEvent(UserEidteEvent.class).
				observeOn(AndroidSchedulers.mainThread()).
				subscribe(new Action1<UserEidteEvent>() {
					@Override
					public void call(UserEidteEvent Event) {

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
		if (users == null) {
			lv_users.setEmptyView(empty);
			return;
		}
		adapter = new UserAdapter(users, this);
		lv_users.setAdapter(adapter);
		lv_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(UserActivity.this, EditUser.class);
				intent.putExtra(ActivityJumpParams.USER_BEAN, users.get(position));
				startActivity(intent);
			}
		});

		LogUtil.e(users.toString());
	}

	private void readData() {
		Tasks.executeInBackground(this, new BackgroundWork<List<User>>() {
			@Override
			public List<User> doInBackground() throws Exception {
				return UserDao.queryAll();
			}
		}, new Completion<List<User>>() {
			@Override
			public void onSuccess(Context context, List<User> result) {
				users = result;
				initData();
			}

			@Override
			public void onError(Context context, Exception e) {
				svProgressHUD.showErrorWithStatus("读取用户列表失败");
				lv_users.postDelayed(new Runnable() {
					@Override
					public void run() {
						finish();
					}
				}, 1500);
			}
		});
	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_user;
	}

	@OnClick({R.id.ll_back, R.id.ll_add})
	public void OnClick(View view) {
		switch (view.getId()) {

			case R.id.ll_back:
				finish();
				break;
			case R.id.ll_add:
				Intent intent = new Intent(this, AddUser.class);
				startActivity(intent);
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
