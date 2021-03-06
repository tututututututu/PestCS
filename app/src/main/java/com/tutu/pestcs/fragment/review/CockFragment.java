package com.tutu.pestcs.fragment.review;

import android.content.Context;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.CheakRecoderDetail;
import com.tutu.pestcs.app.ReviewDataCall;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.event.ModifyModeEvent;
import com.tutu.pestcs.event.SaveInsertEvent;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

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
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;
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


    private String unitycode;
    private ZhangBean bean = new ZhangBean();


    @Override
    public int getLayoutID() {
        return R.layout.review_cock_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }

    @OnClick({R.id.btn_save,R.id.btn_exit,R.id.btn_photos})
    public void onClick(View v){

        CheakRecoderDetail cheakRecoderDetail =  (CheakRecoderDetail)getActivity();
        switch (v.getId()){
            case R.id.btn_save:
                RxBus.postEvent(new SaveInsertEvent(),SaveInsertEvent.class);
                cheakRecoderDetail.saveHeadData();
                break;
            case R.id.btn_exit:
                cheakRecoderDetail.deleteRecodeDialog();
                break;
            case R.id.btn_photos:
                cheakRecoderDetail.toPhotoActivity();
                break;
        }
    }


    @Override
    public void initView() {
        //tbase.setChildEnable(tbase, false);

        unitycode = getArguments().getString(ActivityJumpParams.UNITYCODE);
        if (unitycode == null) {
            ToastUtils.showToast("非法记录查询");
            return;
        }

        // TODO: 2016/6/18 查询蟑螂详情 根据unitycode
        Tasks.executeInBackground(getActivity(), new BackgroundWork<ZhangBean>() {
            @Override
            public ZhangBean doInBackground() throws Exception {
                return ZhangDao.queryByUnitID(unitycode);
            }
        }, new Completion<ZhangBean>() {
            @Override
            public void onSuccess(Context context, ZhangBean result) {
                addTextChangelistener();
                if (result == null) {
                    return;
                }
                bean = result;
                initReviewData();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

        registModifyEvent();
    }

    private void addTextChangelistener() {
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
    }

    private void registModifyEvent() {
        subscriptions.add(RxBus.obtainEvent(ModifyModeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ModifyModeEvent>() {
                    @Override
                    public void call(ModifyModeEvent Event) {
//                        if (Event.isEditable()) {
//                            tbase.setChildEnable(tbase, true);
//                        } else {
//                            tbase.setChildEnable(tbase, false);
//                            onSave();
//                        }
                        onSave();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }

    private void initReviewData() {
        et_jianchafangshu.setText(bean.getCheckRoom() + "");
        et_chengchongyangxingfangjianshu.setText(bean.getChengCongRoom() + "");
        et_dalian.setText(bean.getDaLianNum() + "");
        et_xiaolian.setText(bean.getXiaoLianNuml() + "");
        et_luanqiaoxiangxingfangjianshu.setText(bean.getLuanQiaoRoom() + "");
        et_chahuoluanqiaoshu.setText(bean.getLuanQiaoNum() + "");
        et_zhangjiyangxingfangjianshu.setText(bean.getZhangJiRoom() + "");
        et_chongshi.setText(bean.getChongShi() + "");
        et_canpian.setText(bean.getCanPian() + "");
        et_kongluanqiaoke.setText(bean.getKongKe() + "");
        et_zhanglangfenbian.setText(bean.getFenBian() + "");
        et_tuipi.setText(bean.getTuiPi() + "");
    }


    private void onSave() {
        formatData();

        if (verifyInput() == 2) {
            TApplication.zhang = true;
            TApplication.zhangBean = bean;
            ReviewDataCall.saveReviewData(getActivity());
        } else if (verifyInput() == 1) {
            TApplication.zhang = true;
            TApplication.zhangBean = null;
            ReviewDataCall.saveReviewData(getActivity());
        } else {
            TApplication.zhang = false;
            TApplication.zhangBean = null;
        }

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

        if (bean.getCheckRoom() < 1) {
            return 1;
        }

        if (TextUtils.isEmpty(bean.getUnitCode())) {
            CheakInsertBean cheakInsertBean = ((CheakRecoderDetail) getActivity()).getCheakInsertBean();
            bean.setUnitCode(cheakInsertBean.getUnitCode());
            bean.setUniType(cheakInsertBean.getUnitClassID());
            bean.setTaskCode(cheakInsertBean.getTaskCode());
            bean.setAreaCode(cheakInsertBean.getAreaCode());
            bean.setKeyUnit(cheakInsertBean.isKeyUnit());
            bean.setExpertCode(cheakInsertBean.getExpertCode());
        }

        if("17".equals(bean.getUniType())){
            return 1;
        }


        if (chengchongyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showErrorToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) != 0) {
            ToastUtils.showErrorToast("蟑螂 <蟑迹阳性房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu > 0 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) <
                zhangjiyangxingfangjianshu) {
            ToastUtils.showErrorToast("蟑螂 <蟑迹阳性房间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu > 0 && dalian + xiaolian < 1) {
            ToastUtils.showErrorToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu == 0 && (dalian + xiaolian) > 0) {
            ToastUtils.showErrorToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (chengchongyangxingfangjianshu > 0 && ((dalian + xiaolian) < chengchongyangxingfangjianshu)) {
            ToastUtils.showErrorToast("蟑螂 <成若虫阳性间数填写>不合法");
            return 0;
        }

        if (luanqiaoxiangxingfangjianshu > jianchafangshu) {
            ToastUtils.showErrorToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showErrorToast("蟑螂 <检查房间数填写>不合法");
            return 0;
        }


        if (luanqiaoxiangxingfangjianshu == 0 && chahuoluanqiaoshu > 0) {
            ToastUtils.showErrorToast("蟑螂 <查获卵鞘数填写>不合法");
            return 0;
        }

        if (luanqiaoxiangxingfangjianshu > 0 && chahuoluanqiaoshu < luanqiaoxiangxingfangjianshu) {
            ToastUtils.showErrorToast("蟑螂 <查获卵鞘数填写>不合法");
            return 0;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + kongluanqiaoke + zhanglangfenbian + tuipi) > 0) {
            ToastUtils.showErrorToast("蟑螂 <蟑螂阳性房间数填写>不合法");
            return 0;
        }

        return 2;
    }
}
