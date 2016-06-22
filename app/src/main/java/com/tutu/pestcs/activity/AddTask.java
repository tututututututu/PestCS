package com.tutu.pestcs.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.event.TaskEvent;
import com.tutu.pestcs.widget.DateTimePickDialogUtil;

import org.xutils.common.util.LogUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class AddTask extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_code)
    EditText tvCode;
    @Bind(R.id.et_city)
    EditText etCity;
    @Bind(R.id.et_city_code)
    EditText etCityCode;
    @Bind(R.id.et_population)
    EditText etPopulation;
    @Bind(R.id.et_cheaker_name)
    EditText etCheakerName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_group_number)
    EditText etGroupNumber;
    @Bind(R.id.et_time)
    TextView etTime;
    @Bind(R.id.cb_shu)
    CheckBox cbShu;
    @Bind(R.id.cb_wen)
    CheckBox cbWen;
    @Bind(R.id.cb_ying)
    CheckBox cbYing;
    @Bind(R.id.cb_zhang)
    CheckBox cbZhang;
    @Bind(R.id.base_layout)
    LinearLayout baseLayout;
    private TaskBean task;

    //1.来自添加 2来自修改
    private int mComeFrom = 0;

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_task;
    }


    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //从修改任务跳转则有值 从添加任务跳转则为null
        task = getIntent().getParcelableExtra(ActivityJumpParams.TASK_BEAN);

        if (task != null) {
            mComeFrom = 2;
            updateUI();
        }
    }

    private void updateUI() {
        //填充不可修改的字段
        //修改某些控件enable = false;
        tvCode.setEnabled(false);
        tvTitle.setText("修改考评任务");
        tvCode.setText(task.getTaskCode());
        etTime.setText(task.getStartDate());
        etNumber.setText(task.getExpertCode());
        etGroupNumber.setText(task.getGroups() + "");
        etCheakerName.setText(task.getExpertName());
        etPopulation.setText(task.getPopulation() + "");
        etCity.setText(task.getCityName());
        etCityCode.setText(task.getAreaCode());
        cbShu.setChecked(task.isShu());
        cbYing.setChecked(task.isYing());
        cbZhang.setChecked(task.isZhang());
        cbWen.setChecked(task.isWen());

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ll_back, R.id.ll_confirm, R.id.et_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_confirm:
                updateData();
                break;
            case R.id.et_time:
                showTimePick();
                break;
        }
    }

    private void showTimePick() {
        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                AddTask.this, null);
        dateTimePicKDialog.dateTimePicKDialog(etTime);

    }

    //修改或新增数据库表
    private void updateData() {
        String groups = etGroupNumber.getText().toString().trim();
        String taskCode = tvCode.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String citycode = etCityCode.getText().toString().trim();
        String population = (etPopulation.getText().toString().trim());
        String expertName = etCheakerName.getText().toString().trim();
        String expertCode = etNumber.getText().toString().trim();
        String startDate = etTime.getText().toString().trim();


        if (TextUtils.isEmpty(groups) || TextUtils.isEmpty(taskCode) || TextUtils.isEmpty(city) || TextUtils.isEmpty
                (citycode) || TextUtils.isEmpty(population) || TextUtils.isEmpty(expertCode) || TextUtils.isEmpty
                (expertName) || TextUtils.isEmpty(startDate)) {
            svProgressHUD.showErrorWithStatus("请填写完整");
            return;
        }

        if (taskCode.length()!=14){
            svProgressHUD.showErrorWithStatus("任务代码填写不正确");
            return;
        }

        if (citycode.length()<6){
            svProgressHUD.showErrorWithStatus("城市代码填写不正确");
            return;
        }

        if(Integer.parseInt(population)<1){
            svProgressHUD.showErrorWithStatus("人口数必须大于1");
            return;
        }


        if (mComeFrom != 2) {
            //新增
            task = new TaskBean();
            task.setTaskCode(taskCode);
            task.setCityName(city);
            task.setAreaCode(citycode);
            task.setPopulation(Integer.valueOf(population));
            task.setExpertName(expertName);
            task.setExpertCode(expertCode);
            task.setGroups(Integer.valueOf(groups));
            task.setStartDate(startDate);
            task.setWen(cbWen.isChecked());
            task.setShu(cbShu.isChecked());
            task.setZhang(cbZhang.isChecked());
            task.setYing(cbYing.isChecked());
            LogUtil.e("add task=" + task.toString());
            TaskDao.saveBindID(task);
        } else {
            //修改
            task.setTaskCode(taskCode);
            task.setCityName(city);
            task.setAreaCode(citycode);
            task.setPopulation(Integer.valueOf(population));
            task.setExpertName(expertName);
            task.setExpertCode(expertCode);
            task.setGroups(Integer.valueOf(groups));
            task.setStartDate(startDate);
            task.setWen(cbWen.isChecked());
            task.setShu(cbShu.isChecked());
            task.setZhang(cbZhang.isChecked());
            task.setYing(cbYing.isChecked());
            LogUtil.e("update task=" + task.toString());
            TaskDao.update(task);
        }
        RxBus.postEvent(new TaskEvent(task), TaskEvent.class);
        finish();
    }



}
