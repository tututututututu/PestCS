package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.bean.PhotoBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.CheakInsertDao;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.db.NoteDao;
import com.tutu.pestcs.db.PhotoDao;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.event.ModifyModeEvent;
import com.tutu.pestcs.event.PhotoChangeEvent;
import com.tutu.pestcs.event.RecodeDeleteEvent;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.UnitTypeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CheakRecoderDetail extends BaseActivity {


    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.et_unit_type)
    TextView etUnitType;
    @Bind(R.id.cb_zhongdian)
    CheckBox cbZhongdian;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.ll_modify)
    LinearLayout llModify;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.btn_photos)
    Button btnPhotos;
    @Bind(R.id.tv_set_current_task)
    TextView tvSetCurrentTask;
    @Bind(R.id.ll_no_current_task)
    LinearLayout llNoCurrentTask;
    @Bind(R.id.base_layout)
    FrameLayout baseLayout;
    private ReviewFragmentAdapter adapter;
    private String unitycode;


    private boolean[] cheakItems = {true, true, true, true};

    private CheakInsertBean cheakInsertBean;

    private boolean editeable = false;

    private List<PhotoBean> photosNameList = new ArrayList<>();

    private ExtendSortUnitBean extendSortUnitBean = new ExtendSortUnitBean();


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

        switch (cheakInsertBean.getUnitClassID()) {
            case "01":
                etUnitType.setText("餐饮店");
                break;
            case "02":
                etUnitType.setText("商场超市");
                break;
            case "03":
                etUnitType.setText("机关");
                break;
            case "04":
                etUnitType.setText("饭店宾馆");
                break;
            case "05":
                etUnitType.setText("农贸市场");
                break;
            case "06":
                etUnitType.setText("机场车站");
                break;
            case "07":
                etUnitType.setText("医院");
                break;
            case "08":
                etUnitType.setText("学校");
                break;
            case "09":
                etUnitType.setText("建筑拆迁工地");
                break;
            case "10":
                etUnitType.setText("居(家)委会");
                break;
            case "11":
                etUnitType.setText("食品加工企业");
                break;
            case "12":
                etUnitType.setText("其他企业");
                break;
            case "13":
                etUnitType.setText("道路");
                break;
            case "14":
                etUnitType.setText("公园公告绿地");
                break;
            case "15":
                etUnitType.setText("垃圾中转站");
                break;
            case "16":
                etUnitType.setText("公共厕所");
                break;
            case "17":
                etUnitType.setText("大中型水体");
                break;
            case "18":
                etUnitType.setText("特殊场所");
                break;
        }


        if (cheakInsertBean.isKeyUnit()) {
            cbZhongdian.setChecked(true);
        } else {
            cbZhongdian.setChecked(false);
        }

        etAddress.setText(cheakInsertBean.getNamePlace());
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


    @OnClick({R.id.et_unit_type, R.id.btn_save, R.id.btn_exit, R.id.btn_photos, R.id.ll_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.et_unit_type:
                showUnitTypeDialog();
                break;
            case R.id.btn_save:
//                editeable = !editeable;
//                if (editeable) {
//                    btnSave.setText("保存");
//                    etUnitType.setEnabled(true);
//                    etAddress.setEnabled(true);
//                    cbZhongdian.setEnabled(true);
//                    RxBus.postEvent(new ModifyModeEvent(unitycode, editeable), ModifyModeEvent.class);
//                } else {
//                    etUnitType.setEnabled(false);
//                    btnSave.setText("修改");
//                    etAddress.setEnabled(false);
//                    cbZhongdian.setEnabled(false);
//                    saveHeadData();//保存头部一些数据
//                }

                RxBus.postEvent(new ModifyModeEvent(unitycode, editeable), ModifyModeEvent.class);
                saveHeadData();

                break;
            case R.id.btn_exit:
                deleteRecodeDialog();
                break;
            case R.id.btn_photos:
                // TODO: 2016/6/19  照片浏览
                toPhotoActivity();
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }


    private void showUnitTypeDialog() {
        UnitTypeDialog.getInstace(this, new UnitTypeDialog.onDialogClick() {
            @Override
            public void onCofirm(String cheakIndex, String cheakString) {
                etUnitType.setText(cheakString);
                extendSortUnitBean = ExtendUnitDao.queryByUnitID(cheakIndex);
                cbZhongdian.setChecked(extendSortUnitBean.iskeyClass());
                cheakInsertBean.setUnitClassID(cheakIndex);
            }

            @Override
            public void onCancle() {

            }
        }, false).show();
    }


    private void saveHeadData() {
        String address = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showToast("单位或地址不能为空");
            return;
        }
        cheakInsertBean.setNamePlace(address);
        cheakInsertBean.setKeyUnit(cbZhongdian.isChecked());

        RxBus.postEvent(new ModifyModeEvent(unitycode, editeable), ModifyModeEvent.class);
        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                CheakInsertDao.saveOrUpdate(cheakInsertBean);
                return null;
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {

            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

    }

    private void deleteRecodeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告")
                .setMessage("删除后不能恢复，是否删除该单位检查数据？")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRecode();
            }
        });
        builder.create().show();
    }

    private void deleteRecode() {
        // TODO: 2016/7/13  删除整条记录
        svProgressHUD.showWithStatus("删除中...");
        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                CheakInsertDao.delete(cheakInsertBean);
                ShuDao.deleteByUnicode(unitycode);
                YingDao.deleteByUnicode(unitycode);
                WenDao.deleteByUnicode(unitycode);
                ZhangDao.deleteByUnicode(unitycode);
                NoteDao.deleteByUnicode(unitycode);
                return null;
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
                svProgressHUD.dismissImmediately();
                ToastUtils.showToast("删除成功");
                RxBus.postEvent(new RecodeDeleteEvent(), RecodeDeleteEvent.class);
                finish();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
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
        return etAddress.getText().toString().trim();
    }

    public boolean canSave() {
        if (!TextUtils.isEmpty(etAddress.getText().toString().trim()) && !TextUtils.isEmpty(etUnitType.getText()
                .toString().trim())) {
            return true;
        }
        return false;
    }
}
