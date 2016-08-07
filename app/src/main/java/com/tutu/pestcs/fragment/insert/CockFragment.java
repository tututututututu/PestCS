package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.app.ReviewDataCall;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.event.SaveInsertEvent;
import com.tutu.pestcs.widget.ContactDialog;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/7.
 * 蟑螂
 */
public class CockFragment extends BaseFragment {
    @Bind(R.id.et_jianchafangshu)
    EditText et_jianchafangshu;
    private int jianchafangshu = 0;
    @Bind(R.id.et_chengchongyangxingfangjianshu)
    EditText et_chengchongyangxingfangjianshu;
    private int chengchongyangxingfangjianshu = 0;
    @Bind(R.id.et_dalian)
    EditText et_dalian;
    private int dalian = 0;
    @Bind(R.id.et_xiaolian)
    EditText et_xiaolian;
    private int xiaolian = 0;
    @Bind(R.id.et_luanqiaoxiangxingfangjianshu)
    EditText et_luanqiaoxiangxingfangjianshu;
    private int luanqiaoxiangxingfangjianshu = 0;
    @Bind(R.id.et_chahuoluanqiaoshu)
    EditText et_chahuoluanqiaoshu;
    private int chahuoluanqiaoshu = 0;
    @Bind(R.id.et_zhangjiyangxingfangjianshu)
    EditText et_zhangjiyangxingfangjianshu;
    private int zhangjiyangxingfangjianshu = 0;
    @Bind(R.id.et_chongshi)
    EditText et_chongshi;
    private int chongshi = 0;
    @Bind(R.id.et_canpian)
    EditText et_canpian;
    private int canpian = 0;
    @Bind(R.id.et_tuipi)
    EditText et_tuipi;
    private int tuipi = 0;
    @Bind(R.id.et_kongluanqiaoke)
    EditText et_kongluanqiaoke;
    private int kongluanqiaoke = 0;
    @Bind(R.id.et_zhanglangfenbian)
    EditText et_zhanglangfenbian;
    private int zhanglangfenbian = 0;
    @Bind(R.id.ll_zhangjileixing)
    LinearLayout ll_zhangjileixing;


    private CheakInsertBean cheakInsertBean;
    private ZhangBean bean = new ZhangBean();


    @Override
    public int getLayoutID() {
        return R.layout.cock_insert_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);
        bean.setUnitCode(cheakInsertBean.getUnitCode());
        bean.setUniType(cheakInsertBean.getUnitClassID());
        bean.setAreaCode(cheakInsertBean.getAreaCode());
        bean.setTaskCode(cheakInsertBean.getTaskCode());
        bean.setKeyUnit(cheakInsertBean.isKeyUnit());
        bean.setExpertCode(cheakInsertBean.getExpertCode());

        et_zhangjiyangxingfangjianshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    ll_zhangjileixing.setVisibility(View.GONE);
                    return;
                }
                if (Integer.parseInt(s.toString().trim()) > 0) {
                    ll_zhangjileixing.setVisibility(View.VISIBLE);
                } else {
                    ll_zhangjileixing.setVisibility(View.GONE);
                }
            }
        });

        initSaveEvent();
    }

    private void initSaveEvent() {
        subscriptions.add(RxBus.obtainEvent(SaveInsertEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SaveInsertEvent>() {
                    @Override
                    public void call(SaveInsertEvent saveInsertEvent) {
                        saveDada();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                })

        );
    }


    @OnClick({R.id.save})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                RxBus.postEvent(new SaveInsertEvent(),SaveInsertEvent.class);
                break;
        }
    }

    private boolean saveDada() {
        if (((InsertActivity) getActivity()).canSave()) {
            formatData();

            if (verifyInput() == 2) {
                TApplication.zhangI = true;
                TApplication.zhangBeanI = bean;
                ReviewDataCall.saveInserData(getActivity(),false);
            } else if (verifyInput() == 1) {
                TApplication.zhangI = true;
                TApplication.zhangBeanI = null;
                ReviewDataCall.saveInserData(getActivity(),false);
            } else {
                TApplication.zhangI = false;
                TApplication.zhangBeanI = null;
            }
        } else {
            ToastUtils.showToast("请填写检查单位名称或地点");
        }
        return false;
    }

    private void formatData() {
        jianchafangshu = dealData(et_jianchafangshu);
        chengchongyangxingfangjianshu = dealData(et_chengchongyangxingfangjianshu);
        dalian = dealData(et_dalian);
        xiaolian = dealData(et_xiaolian);
        luanqiaoxiangxingfangjianshu = dealData(et_luanqiaoxiangxingfangjianshu);
        chahuoluanqiaoshu = dealData(et_chahuoluanqiaoshu);
        zhangjiyangxingfangjianshu = dealData(et_zhangjiyangxingfangjianshu);
        chongshi = dealData(et_chongshi);
        canpian = dealData(et_canpian);
        kongluanqiaoke = dealData(et_kongluanqiaoke);
        zhanglangfenbian = dealData(et_zhanglangfenbian);
        tuipi = dealData(et_tuipi);


        bean.setCheckRoom(jianchafangshu);
        bean.setChengCongRoom(chengchongyangxingfangjianshu);
        bean.setDaLianNum(dalian);
        bean.setXiaoLianNuml(xiaolian);
        bean.setLuanQiaoRoom(luanqiaoxiangxingfangjianshu);
        bean.setLuanQiaoNum(chahuoluanqiaoshu);
        bean.setZhangJiRoom(zhangjiyangxingfangjianshu);
        bean.setChongShi(chongshi);
        bean.setCanPian(canpian);
        bean.setKongKe(kongluanqiaoke);
        bean.setFenBian(zhanglangfenbian);
        bean.setTuiPi(tuipi);

    }


    private int dealData(EditText et) {
        return Integer.valueOf(TextUtils.isEmpty(et.getText().toString().trim()) ? "0" :
                et.getText().toString().trim());
    }

    private int verifyInput() {

        if (TextUtils.isEmpty(bean.getUnitCode())) {
            ContactDialog.show(getActivity(), getClass().getSimpleName() + "\n" + "verifyInput()" + "TextUtils" +
                    ".isEmpty(bean.getUnitCode()) is empty");
            return 0;
        }

        if (bean.getCheckRoom() < 1) {
            return 1;
        }

        if (cheakInsertBean.getUnitClassID().equals("17")) {
            ToastUtils.showToast("当前类型为大中型水体，只保存蚊类检查数据");
            return 1;
        }

        if (chengchongyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showWarningToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) != 0) {
            ToastUtils.showWarningToast("蟑螂 <蟑迹阳性房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu > 0 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) <
                zhangjiyangxingfangjianshu) {
            ToastUtils.showWarningToast("蟑螂 <蟑迹阳性房间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu > 0 && dalian + xiaolian < 1) {
            ToastUtils.showWarningToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu == 0 && (dalian + xiaolian) > 0) {
            ToastUtils.showWarningToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu > 0 && ((dalian + xiaolian) < chengchongyangxingfangjianshu)) {
            ToastUtils.showWarningToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (luanqiaoxiangxingfangjianshu > jianchafangshu) {
            ToastUtils.showWarningToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showWarningToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }


        if (luanqiaoxiangxingfangjianshu == 0 && chahuoluanqiaoshu > 0) {
            ToastUtils.showWarningToast("蟑螂 <查获卵鞘数填写>不合法");
            return 0;
        }

        if (luanqiaoxiangxingfangjianshu > 0 && chahuoluanqiaoshu < luanqiaoxiangxingfangjianshu) {
            ToastUtils.showWarningToast("蟑螂 <查获卵鞘数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + kongluanqiaoke + zhanglangfenbian + tuipi) > 0) {
            ToastUtils.showWarningToast("蟑螂 <蟑螂阳性房间数填写>不合法");
            return 0;
        }


        return 2;
    }
}
