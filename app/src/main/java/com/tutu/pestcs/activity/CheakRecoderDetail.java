package com.tutu.pestcs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.tutu.pestcs.R;
import com.tutu.pestcs.adapter.ReviewFragmentAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;

import butterknife.Bind;
import butterknife.OnClick;

public class CheakRecoderDetail extends BaseActivity {
    @Bind(R.id.ll_modify)
    LinearLayout llModify;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_cheaker_name)
    TextView tvCheakerName;
    @Bind(R.id.tv_unity_type)
    TextView tvUnityType;
    @Bind(R.id.tv_zhongdian)
    TextView tvZhongdian;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.ll_review)
    LinearLayout llReview;
    @Bind(R.id.tv_set_current_task)
    TextView tvSetCurrentTask;
    @Bind(R.id.ll_no_current_task)
    LinearLayout llNoCurrentTask;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.btn_photos)
    Button btnPhotos;
    @Bind(R.id.base_layout)
    FrameLayout baseLayout;


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

    private ReviewFragmentAdapter adapter;
    private String unitycode;

    //当前任务(可能为空)
    private TaskBean CurrentTaskBean;
    private boolean[] cheakItems = {true, true, true, true};


    @Override
    public int getLayoutID() {
        return R.layout.activity_cheak_recoder_detail;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initData() {
        // TODO: 2016/6/18 赋值unitycode
        unitycode = getIntent().getStringExtra(ActivityJumpParams.UNITYCODE);
        if (TextUtils.isEmpty(unitycode)){
            svProgressHUD.showErrorWithStatus("系统错误");
            return;
        }
        initUI();
    }

    @Override
    public void initView(Bundle savedInstanceState) {

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
        adapter = new ReviewFragmentAdapter(getSupportFragmentManager(), cheakItems, unitycode);
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

    }




    @OnClick({R.id.et_unit_type})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.et_unit_type:
                break;
        }
    }


    public String getUnitName() {
        return etName.getText().toString().trim();
    }

    public boolean canSave() {
        if (!TextUtils.isEmpty(etName.getText().toString().trim()) && !TextUtils.isEmpty(etUnitType.getText()
                .toString().trim())) {
            return true;
        }
        return false;
    }

}
