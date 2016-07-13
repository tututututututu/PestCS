package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
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
import com.tutu.pestcs.adapter.InsertFragmentAdapter;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ExtendSortUnitBean;
import com.tutu.pestcs.bean.PhotoBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.CheakInsertDao;
import com.tutu.pestcs.db.ExtendUnitDao;
import com.tutu.pestcs.db.PhotoDao;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.event.SetCurrentTaskEvent;
import com.tutu.pestcs.event.UnityTypeChangeEvent;
import com.tutu.pestcs.utils.DateHelper;
import com.tutu.pestcs.utils.PhotosStore;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.UnitTypeDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/7.
 */
public class InsertActivity extends BaseActivity {
    private static final int TAKE_PHOTO = 100;

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
    @Bind(R.id.ll_no_current_task)
    LinearLayout ll_no_current_task;

    private InsertFragmentAdapter adapter;
    private ExtendSortUnitBean extendSortUnitBean = new ExtendSortUnitBean();
    private CheakInsertBean cheakInsertBean = new CheakInsertBean();
    //当前任务(可能为空)
    private TaskBean CurrentTaskBean;
    private boolean[] cheakItems = {true, true, true, true};

    //任务的单位类型
    private String mUnitType;

    //照片名字
    private String imgName;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否开始录入?");
        builder.setPositiveButton("开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                showUnitTypeDialog();


                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setCancelable(false);

        builder.create().show();


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
                if (TextUtils.isEmpty(s.toString().trim())) {
                    //do nothing
                } else {
                    updateCheakRecode();
                }
            }
        });
    }

    private void updateCheakRecode() {
        cheakInsertBean.setNamePlace(etName.getText().toString().trim());
        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                CheakInsertDao.saveOrUpdate(cheakInsertBean);
                return "";
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
            }

            @Override
            public void onError(Context context, Exception e) {
                ToastUtils.showToast("系统错误");
            }
        });
    }


    private void insertCheakRecode() {
        cheakInsertBean.setUnitClassID(extendSortUnitBean.getUnitID());
        cheakInsertBean.setKeyUnit(cbZhongdian.isChecked());

        cheakInsertBean.setNamePlace(etName.getText().toString().trim());
        //生成一条记录到数据库
        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                CheakInsertDao.saveOrUpdate(cheakInsertBean);
                return "";
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
                initUI();
            }

            @Override
            public void onError(Context context, Exception e) {
                ToastUtils.showToast("系统错误");
            }
        });


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


        adapter = new InsertFragmentAdapter(getSupportFragmentManager(), cheakItems, cheakInsertBean);
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

    private void queryCurrentTask() {
        Tasks.executeInBackground(this, new BackgroundWork<TaskBean>() {
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
                    cheakInsertBean.setCityName(CurrentTaskBean.getCityName());
                    cheakInsertBean.setExpertName(CurrentTaskBean.getExpertName());
                    insertCheakRecode();

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


    @OnClick({R.id.et_unit_type, R.id.ll_camara, R.id.tv_set_current_task})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.et_unit_type:
                showUnitTypeDialog();
                break;
            case R.id.ll_camara:
                File destDir = new File(PhotosStore.getPhotosDir());
                if (!destDir.exists()) {
                    if (!destDir.mkdirs()) {
                        svProgressHUD.showErrorWithStatus("请给程序读取SD卡权限");
                    }
                }

                imgName = cheakInsertBean.getUnitCode() + DateHelper.getNowTimeSecond() + ".jpg";
                String sFileFullPath = PhotosStore.getPhotosDir() + File.separator + imgName;


                File file = new File(sFileFullPath);
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.tv_set_current_task:
                intent = new Intent(this, TaskActivity.class);
                startActivity(intent);
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
                mUnitType = cheakIndex;
                cbZhongdian.setChecked(extendSortUnitBean.iskeyClass());

                addSetCurrentTaskLister();
                queryCurrentTask();
                addSetCurrentTaskLister();
                addTextWather();
                cheakInsertBean.setUnitClassID(cheakIndex);
                RxBus.postEvent(new UnityTypeChangeEvent(cheakIndex), UnityTypeChangeEvent.class);
            }

            @Override
            public void onCancle() {

            }
        }, false).show();
    }


    private void addSetCurrentTaskLister() {
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

    public String getUnitType() {
        return mUnitType;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                final PhotoBean photoBean = new PhotoBean();
                photoBean.setTaskCode(cheakInsertBean.getTaskCode());
                photoBean.setPictureName(imgName);
                photoBean.setUnitCode(cheakInsertBean.getUnitCode());
                Tasks.executeInBackground(this, new BackgroundWork<String>() {
                    @Override
                    public String doInBackground() throws Exception {
                        PhotoDao.saveBindID(photoBean);
                        return "";
                    }
                }, new Completion<String>() {
                    @Override
                    public void onSuccess(Context context, String result) {
                        ToastUtils.showToast("照片保存成功");
                    }

                    @Override
                    public void onError(Context context, Exception e) {

                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        ToastUtils.showToast("请点击退出来关闭页面");
        return;
    }
}

