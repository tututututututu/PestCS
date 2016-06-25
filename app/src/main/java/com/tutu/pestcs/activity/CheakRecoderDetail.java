package com.tutu.pestcs.activity;

import android.content.Context;
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
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.adapter.ReviewFragmentAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.PhotoBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.CheakInsertDao;
import com.tutu.pestcs.db.PhotoDao;
import com.tutu.pestcs.event.ModifyModeEvent;
import com.tutu.pestcs.event.PhotoChangeEvent;
import com.tutu.pestcs.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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


    private boolean[] cheakItems = {true, true, true, true};

    private CheakInsertBean cheakInsertBean;

    private boolean editeable = false;

    private List<PhotoBean> photosNameList = new ArrayList<>();


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


        if (TextUtils.isEmpty(unitycode)) {
            svProgressHUD.showErrorWithStatus("系统错误");
            return;
        }
        initUI();

        queryPhotos();

        subscriptions.add(RxBus.obtainEvent(PhotoChangeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<PhotoChangeEvent>() {
                    @Override
                    public void call(PhotoChangeEvent taskEvent) {

                        queryPhotos();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }

    private void queryPhotos() {
        Tasks.executeInBackground(this, new BackgroundWork<CheakInsertBean>() {
            @Override
            public CheakInsertBean doInBackground() throws Exception {
                photosNameList = PhotoDao.queryByUnitID(unitycode);
                return CheakInsertDao.queryByUnitID(unitycode);
            }
        }, new Completion<CheakInsertBean>() {
            @Override
            public void onSuccess(Context context, CheakInsertBean result) {
                if (result == null) {
                    ToastUtils.showToast("系统内部错误");
                    return;
                }
                cheakInsertBean = result;
                initHeadView();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }

    private void initHeadView() {
        tvCity.setText(cheakInsertBean.getCityName());
        tvCheakerName.setText(cheakInsertBean.getExpertName());

        switch (cheakInsertBean.getUnitClassID()) {
            case "01":
                tvUnityType.setText("餐饮店");
                break;
            case "02":
                tvUnityType.setText("商场超市");
                break;
            case "03":
                tvUnityType.setText("机关");
                break;
            case "04":
                tvUnityType.setText("饭店宾馆");
                break;
            case "05":
                tvUnityType.setText("农贸市场");
                break;
            case "06":
                tvUnityType.setText("机场车站");
                break;
            case "07":
                tvUnityType.setText("医院");
                break;
            case "08":
                tvUnityType.setText("学校");
                break;
            case "09":
                tvUnityType.setText("建筑拆迁工地");
                break;
            case "10":
                tvUnityType.setText("居(家)委会");
                break;
            case "11":
                tvUnityType.setText("食品加工企业");
                break;
            case "12":
                tvUnityType.setText("其他企业");
                break;
            case "13":
                tvUnityType.setText("道路");
                break;
            case "14":
                tvUnityType.setText("公园公告绿地");
                break;
            case "15":
                tvUnityType.setText("垃圾中转站");
                break;
            case "16":
                tvUnityType.setText("公共厕所");
                break;
            case "17":
                tvUnityType.setText("大中型水体");
                break;
            case "18":
                tvUnityType.setText("特殊场所");
                break;
        }


        if (cheakInsertBean.isKeyUnit()) {
            tvZhongdian.setText("是");
        } else {
            tvZhongdian.setText("否");
        }

        tvAddress.setText(cheakInsertBean.getNamePlace());
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    private void initUI() {
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
        pager.setOffscreenPageLimit(5);
    }


    @OnClick({R.id.et_unit_type, R.id.btn_save, R.id.btn_exit, R.id.btn_photos})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.et_unit_type:
                break;
            case R.id.btn_save:
                editeable = !editeable;
                RxBus.postEvent(new ModifyModeEvent(unitycode, editeable), ModifyModeEvent.class);
                if (editeable) {
                    btnSave.setText("保存");
                } else {
                    btnSave.setText("修改");
                }

                break;
            case R.id.btn_exit:
                finish();
                break;
            case R.id.btn_photos:
                // TODO: 2016/6/19  照片浏览
                toPhotoActivity();
                break;
        }
    }

    private void toPhotoActivity() {
        if (photosNameList == null || photosNameList.size() == 0) {
            ToastUtils.showToast("可能没有照片");
            return;
        }
        ImagePagerActivity.startImagePagerActivity(this, photosNameList,
                0);
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
