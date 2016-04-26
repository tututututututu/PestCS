package com.tutu.pestcs.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.SetCurrenTask;
import com.tutu.pestcs.adapter.InsertFragmentAdapter;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.CheakInsertDao;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.event.SetCurrentTaskEvent;
import com.tutu.pestcs.utils.DateHelper;
import com.tutu.pestcs.widget.UnitTypeDialog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/7.
 */
public class InsertFragment extends BaseFragment {
	@Bind(R.id.tabs)
	PagerSlidingTabStrip tabs;
	@Bind(R.id.pager)
	ViewPager pager;
	@Bind(R.id.et_unit_type)
	TextView etUnitType;
	@Bind(R.id.cb_zhongdian)
	CheckBox cbZhongdian;
	@Bind(R.id.et_name)
	EditText etName;
	@Bind(R.id.base_layout)
	LinearLayout baseLayout;
	@Bind(R.id.ll_no_current_task)
	LinearLayout ll_no_current_task;

	private InsertFragmentAdapter adapter;
	private ExtendSortUnitBean extendSortUnitBean = new ExtendSortUnitBean();
	private CheakInsertBean cheakInsertBean = new CheakInsertBean();
	//当前任务(可能为空)
	private TaskBean CurrentTaskBean;
	private boolean[] cheakItems = {true, true, true, true};

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public void initView() {
		addSetCurrentTaskLister();
		queryCurrentTask();
		addSetCurrentTaskLister();
		addTextWather();
	}

	private void addTextWather() {
		etName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (TextUtils.isEmpty(s.toString().trim())){
					//do nothing
				}else{
					insertCheakRecode();
				}
			}
		});
	}


	private void insertCheakRecode() {
		cheakInsertBean.setUnitClassID(extendSortUnitBean.getUnitID());
		cheakInsertBean.setKeyUnit(cbZhongdian.isChecked());
		cheakInsertBean.setNamePlace(etName.getText().toString().trim());
		//生成一条记录到数据库
		CheakInsertDao.saveOrUpdate(cheakInsertBean);
	}


	private void initUI() {

		if (CurrentTaskBean.isShu()) {
			cheakItems[0] = true;
		} else {
			cheakItems[0] = false;
		}
		if (CurrentTaskBean.isYing()) {
			cheakItems[1] = true;
		} else {
			cheakItems[1] = false;
		}
		if (CurrentTaskBean.isWen()) {
			cheakItems[2] = true;
		} else {
			cheakItems[2] = false;
		}
		if (CurrentTaskBean.isZhang()) {
			cheakItems[3] = true;
		} else {
			cheakItems[3] = false;
		}
		adapter = new InsertFragmentAdapter(getChildFragmentManager(), cheakItems, cheakInsertBean);
		pager.setAdapter(adapter);
		tabs.setShouldExpand(true);
		tabs.setUnderlineColorResource(R.color.main_bg2);
		tabs.setIndicatorColorResource(R.color.colorPrimary);
		tabs.setIndicatorHeight((int) getResources().getDimension(R.dimen.dp2));
		tabs.setTextColor(R.color.declare_text);
		tabs.setTextSize((int) getResources().getDimension(R.dimen.sp12));
		tabs.setViewPager(pager);
		//tabs.setTabBackground(); //设置点击时的颜色变化
		tabs.setUnderlineHeight(2);

		insertCheakRecode();

	}

	private void queryCurrentTask() {
		Tasks.executeInBackground(mActivityContext, new BackgroundWork<TaskBean>() {
			@Override
			public TaskBean doInBackground() throws Exception {
				return TaskDao.queryCurrent();
			}
		}, new Completion<TaskBean>() {
			@Override
			public void onSuccess(Context context, TaskBean result) {
				if (result == null) {
					ll_no_current_task.setVisibility(View.VISIBLE);
				} else {
					CurrentTaskBean = result;
					cheakInsertBean.setTaskCode(CurrentTaskBean.getTaskCode());
					cheakInsertBean.setExpertCode(CurrentTaskBean.getExpertCode());
					cheakInsertBean.setAreaCode(CurrentTaskBean.getAreaCode());
					cheakInsertBean.setChkDateTime(System.currentTimeMillis());
					cheakInsertBean.setUnitCode(DateHelper.getNowTimeSecond() + CurrentTaskBean.getExpertCode());
					initUI();
				}
			}

			@Override
			public void onError(Context context, Exception e) {

			}
		});


	}

	@Override
	public int getLayoutID() {
		return R.layout.insert_fragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

	@OnClick({R.id.et_unit_type, R.id.ll_camara, R.id.tv_set_current_task})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.et_unit_type:
				showUnitTypeDialog();
				break;
			case R.id.ll_camara:
				break;
			case R.id.tv_set_current_task:
				Intent intent = new Intent(mActivityContext, SetCurrenTask.class);
				startActivity(intent);
				break;
		}
	}

	private void showUnitTypeDialog() {
		UnitTypeDialog.getInstace(mActivityContext, new UnitTypeDialog.onDialogClick() {
			@Override
			public void onCofirm(String cheakIndex, String cheakString) {
				etUnitType.setText(cheakString);
				extendSortUnitBean = ExtendUnitDao.queryByUnitID(cheakIndex);
				cbZhongdian.setChecked(extendSortUnitBean.iskeyClass());
			}

			@Override
			public void onCancle() {

			}
		}).show();
	}


	private void addSetCurrentTaskLister() {
		subscriptions = new ArrayList<>();
		subscriptions.add(RxBus.obtainEvent(SetCurrentTaskEvent.class).
			observeOn(AndroidSchedulers.mainThread()).
			subscribe(new Action1<SetCurrentTaskEvent>() {
				@Override
				public void call(SetCurrentTaskEvent taskEvent) {
					ll_no_current_task.setVisibility(View.GONE);
					queryCurrentTask();
				}
			}, new Action1<Throwable>() {
				@Override
				public void call(Throwable throwable) {

				}
			}));
	}
}

