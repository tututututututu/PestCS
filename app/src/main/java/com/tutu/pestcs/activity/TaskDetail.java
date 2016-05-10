package com.tutu.pestcs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.event.TaskEvent;

import butterknife.Bind;
import butterknife.OnClick;

public class TaskDetail extends BaseActivity {
    @Bind(R.id.tv_code)
    TextView tv_code;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.tv_population)
    TextView tv_population;
    @Bind(R.id.tv_cheaker_name)
    TextView tv_cheaker_name;
    @Bind(R.id.tv_number)
    TextView tv_number;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_items)
    TextView tv_items;


    private TaskBean task;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        task = getIntent().getParcelableExtra(ActivityJumpParams.TASK_BEAN);
    }

    @Override
    public void initData() {
        if (task == null) {
            svProgressHUD.showErrorWithStatus("系统错误");
            tv_code.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1500);
            return;
        }

        tv_code.setText(task.getTaskCode());
        tv_city.setText(task.getCityName());
        tv_cheaker_name.setText(task.getExpertName());
        tv_number.setText(task.getGroups() + "");
        tv_population.setText(task.getPopulation() + "");
        tv_time.setText(task.getStartDate());
        StringBuilder builder = new StringBuilder();
        if (task.isShu()) {
            builder.append("鼠 ");
        }
        if (task.isWen()) {
            builder.append("蚊 ");
        }
        if (task.isYing()) {
            builder.append("蝇 ");
        }
        if (task.isZhang()) {
            builder.append("蟑螂 ");
        }
        tv_items.setText(builder.toString());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_task_detail;
    }

    @OnClick({R.id.btn_modify, R.id.btn_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_modify:
                Intent intent = new Intent(this, AddTask.class);
                intent.putExtra(ActivityJumpParams.TASK_BEAN, task);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_del:
                deleteTask();
                break;
        }

    }

    //删除考评任务
    private void deleteTask() {
        TaskDao.delete(task);
        RxBus.postEvent(new TaskEvent(task), TaskEvent.class);
        finish();
    }
}
