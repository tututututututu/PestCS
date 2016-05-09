package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.adapter.FocusTypeGVAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.event.ChangeFocusTypeEvent;
import com.tutu.pestcs.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class FocusTypeActivity extends BaseActivity {
    @Bind(R.id.gv_focus)
    NoScrollGridView gv_focus;
    @Bind(R.id.gv_normal)
    NoScrollGridView gv_normal;

    private FocusTypeGVAdapter focusAdapter;
    private FocusTypeGVAdapter normalAadapter;

    private List<ExtendSortUnitBean> focusData = new ArrayList<>();
    private List<ExtendSortUnitBean> normalData = new ArrayList<>();

    //两个异步任务 查询重点单位 和非重点单位  两个都完成之后再刷新界面
    private boolean task1 = false;
    private boolean task2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        readData();
        registChangeFocusLister();
    }

    @Override
    public void initData() {

    }

    private void upDateUI() {
        if (task2 && task1) {
            focusAdapter = new FocusTypeGVAdapter(this, focusData);
            normalAadapter = new FocusTypeGVAdapter(this, normalData);

            gv_focus.setAdapter(focusAdapter);
            gv_normal.setAdapter(normalAadapter);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_focus_type;
    }

    private void registChangeFocusLister() {
        subscriptions.add(RxBus.obtainEvent(ChangeFocusTypeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ChangeFocusTypeEvent>() {
                    @Override
                    public void call(ChangeFocusTypeEvent taskEvent) {
                        readData();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }

    private void readData() {

        //读取重点类型
        Tasks.executeInBackground(this, new BackgroundWork<List<ExtendSortUnitBean>>() {
            @Override
            public List<ExtendSortUnitBean> doInBackground() throws Exception {
                return ExtendUnitDao.queryFocusType();
            }
        }, new Completion<List<ExtendSortUnitBean>>() {
            @Override
            public void onSuccess(Context context, List<ExtendSortUnitBean> result) {
                focusData = result;
                task1 = true;
                upDateUI();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

        //读取非重点类型
        Tasks.executeInBackground(this, new BackgroundWork<List<ExtendSortUnitBean>>() {
            @Override
            public List<ExtendSortUnitBean> doInBackground() throws Exception {
                return ExtendUnitDao.queryNoFocusType();
            }
        }, new Completion<List<ExtendSortUnitBean>>() {
            @Override
            public void onSuccess(Context context, List<ExtendSortUnitBean> result) {
                normalData = result;
                task2 = true;
                upDateUI();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }

    @OnClick({R.id.btn_change, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                Intent intent = new Intent(this, ChangeFocusType.class);
                startActivity(intent);
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }


}
