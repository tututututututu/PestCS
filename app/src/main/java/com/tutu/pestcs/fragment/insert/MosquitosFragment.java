package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.app.ReviewDataCall;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.event.SaveInsertEvent;
import com.tutu.pestcs.event.UnityTypeChangeEvent;
import com.tutu.pestcs.widget.ContactDialog;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/7.
 * 蚊子
 */
public class MosquitosFragment extends BaseFragment {
    @Bind(R.id.et_jianchalujing)
    EditText etJianchalujing;
    int Jianchalujing = 0;
    @Bind(R.id.et_chajianmiewendeng)
    EditText etChajianmiewendeng;
    int chajianmiewendeng = 0;
    @Bind(R.id.et_chanjianxiaoxingjishui)
    EditText etChanjianxiaoxingjishui;
    int Chanjianxiaoxingjishui = 0;
    @Bind(R.id.et_chajianxiaoxingjishuiyangxing)
    EditText etChajianxiaoxingjishuiyangxing;
    int Chajianxiaoxingjishuiyangxing = 0;
    @Bind(R.id.et_rongqijishui)
    EditText etRongqijishui;
    int rongqijishui = 0;
    @Bind(R.id.et_rongqijishuiyangxing)
    EditText etRongqijishuiyangxing;
    int Rongqijishuiyangxing = 0;
    @Bind(R.id.et_kengwajishui)
    EditText etKengwajishui;
    int kengwajishui = 0;
    @Bind(R.id.et_kengwajishuiyangxing)
    EditText etKengwajishuiyangxing;
    int Kengwajishuiyangxing = 0;
    @Bind(R.id.et_jingguanchi)
    EditText etJingguanchi;
    int Jingguanchi = 0;

    @Bind(R.id.et_gouqujishui)
    EditText etGouqujishui;
    int Gouqujishui = 0;
    @Bind(R.id.et_gouqujishuiyangxing)
    EditText etGouqujishuiyangxing;
    int Gouqujishuiyangxing = 0;

    @Bind(R.id.et_jingguanchiyangxing)
    EditText etJingguanchiyangxing;
    int Jingguanchiyangxing = 0;
    @Bind(R.id.et_paishuijinkoujishui)
    EditText etPaishuijinkoujishui;
    int Paishuijinkoujishui = 0;
    @Bind(R.id.et_paishuijinkoujishuiyangxing)
    EditText etPaishuijinkoujishuiyangxing;
    int Paishuijinkoujishuiyangxing = 0;
    @Bind(R.id.et_dixiashijishui)
    EditText etDixiashijishui;
    int Dixiashijishui = 0;
    @Bind(R.id.et_dixiashijishuiyangxing)
    EditText etDixiashijishuiyangxing;
    int Dixiashijishuiyangxing = 0;
    @Bind(R.id.et_luntaijishui)
    EditText etLuntaijishui;
    int Luntaijishui = 0;
    @Bind(R.id.et_luntaijishuiyangxing)
    EditText etLuntaijishuiyangxing;
    int Luntaijishuiyangxing = 0;
    @Bind(R.id.et_qita)
    EditText etQita;
    int Qita = 0;
    @Bind(R.id.et_qitayangxing)
    EditText etQitayangxing;
    int Qitayangxing = 0;
    @Bind(R.id.et_youwenrenci)
    EditText etYouwenrenci;
    int Youwenrenci = 0;
    @Bind(R.id.et_wenchongtingluocishu)
    EditText etWenchongtingluocishu;
    int Wenchongtingluocishu = 0;
    @Bind(R.id.et_caiyanggong)
    EditText etCaiyanggong;
    int Caiyanggong = 0;
    @Bind(R.id.et_yangxinggong)
    EditText etYangxinggong;
    int Yangxinggong = 0;
    @Bind(R.id.et_wenyouchongheyonggong)
    EditText etWenyouchongheyonggong;
    int Wenyouchongheyonggong = 0;
    @Bind(R.id.ll_shuiti)
    LinearLayout ll_shuiti;
    @Bind(R.id.rg_shuiTiType)
    RadioGroup rgShuiTiType;

    @Bind(R.id.rb_hupo)
    RadioButton rbHupo;
    @Bind(R.id.rb_heliu)
    RadioButton rbHeliu;
    @Bind(R.id.rb_rengonghu)
    RadioButton rbRengonghu;
    @Bind(R.id.rb_jingguanchi)
    RadioButton rbJingguanchi;
    @Bind(R.id.rb_chitang)
    RadioButton rbChitang;
    @Bind(R.id.rb_gouqu)
    RadioButton rbGouqu;
    @Bind(R.id.rb_qita)
    RadioButton rbQita;
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;

    @Bind(R.id.tv_shuitileixing)
    TextView tvShuitileixing;
    @Bind(R.id.ll_xiaoxingjishui_wai)
    LinearLayout llXiaoxingjishuiWai;
    @Bind(R.id.ll_youwen)
    LinearLayout llYouwen;
    @Bind(R.id.ll_xiaoxingjishui)
    LinearLayout llXiaoxingjishui;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;
    @Bind(R.id.ll_xiaoxingjishuitou)
    LinearLayout llXiaoxingjishuitou;



    private CheakInsertBean cheakInsertBean;
    private WenBean bean = new WenBean();

    @Override
    public int getLayoutID() {
        return R.layout.mosquitos_insert_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }

    @OnClick({R.id.save})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                RxBus.postEvent(new SaveInsertEvent(),SaveInsertEvent.class);
                break;
        }
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


        rgShuiTiType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hupo:
                        bean.setShuiTiType(1);
                        break;
                    case R.id.rb_heliu:
                        bean.setShuiTiType(2);
                        break;
                    case R.id.rb_rengonghu:
                        bean.setShuiTiType(3);
                        break;
                    case R.id.rb_jingguanchi:
                        bean.setShuiTiType(4);
                        break;
                    case R.id.rb_chitang:
                        bean.setShuiTiType(5);
                        break;
                    case R.id.rb_gouqu:
                        bean.setShuiTiType(6);
                        break;
                    case R.id.rb_qita:
                        bean.setShuiTiType(7);
                        break;
                }
            }
        });


        updateVisiableView(cheakInsertBean.getUnitClassID());


        etRongqijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etKengwajishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etJingguanchiyangxing.addTextChangedListener(new MyTextWatcher());
        etPaishuijinkoujishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etDixiashijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etGouqujishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etLuntaijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etQitayangxing.addTextChangedListener(new MyTextWatcher());


        etRongqijishui.addTextChangedListener(new XXJSTextWatcher());
        etKengwajishui.addTextChangedListener(new XXJSTextWatcher());
        etJingguanchi.addTextChangedListener(new XXJSTextWatcher());
        etPaishuijinkoujishui.addTextChangedListener(new XXJSTextWatcher());
        etDixiashijishui.addTextChangedListener(new XXJSTextWatcher());
        etGouqujishui.addTextChangedListener(new XXJSTextWatcher());
        etLuntaijishui.addTextChangedListener(new XXJSTextWatcher());
        etQita.addTextChangedListener(new XXJSTextWatcher());


        registUnityTypeChangeEvent();
        initSaveEvent();
    }


    private void initSaveEvent() {
        subscriptions.add(RxBus.obtainEvent(SaveInsertEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SaveInsertEvent>() {
                    @Override
                    public void call(SaveInsertEvent saveInsertEvent) {
                        saveData();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                })

        );
    }


    private void updateVisiableView(String type) {
        //大中型水体
        if (type.equals("17")) {
            ll_shuiti.setVisibility(View.VISIBLE);
            tvShuitileixing.setVisibility(View.VISIBLE);
            llXiaoxingjishuiWai.setVisibility(View.GONE);
            llYouwen.setVisibility(View.GONE);
            rbHupo.setChecked(true);
            llXiaoxingjishui.setVisibility(View.GONE);
            llXiaoxingjishuitou.setVisibility(View.GONE);
        } else {
            ll_shuiti.setVisibility(View.GONE);
            tvShuitileixing.setVisibility(View.GONE);
            llXiaoxingjishuiWai.setVisibility(View.VISIBLE);
            llYouwen.setVisibility(View.VISIBLE);
            llXiaoxingjishui.setVisibility(View.VISIBLE);
            llXiaoxingjishuitou.setVisibility(View.VISIBLE);
        }
    }

    private void registUnityTypeChangeEvent() {
        subscriptions.add(RxBus.obtainEvent(UnityTypeChangeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<UnityTypeChangeEvent>() {
                    @Override
                    public void call(UnityTypeChangeEvent changeAvatarEvent) {

                        updateVisiableView(changeAvatarEvent.getType());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }


    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int count = Integer.parseInt(0 + etRongqijishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etKengwajishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etJingguanchiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etPaishuijinkoujishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etDixiashijishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etGouqujishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etLuntaijishuiyangxing.getText().toString()) +
                    Integer.parseInt(0 + etQitayangxing.getText().toString());

            etChajianxiaoxingjishuiyangxing.setText(count + "");
        }
    }


    private class XXJSTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int count = Integer.parseInt(0 + etRongqijishui.getText().toString()) +
                    Integer.parseInt(0 + etKengwajishui.getText().toString()) +
                    Integer.parseInt(0 + etJingguanchi.getText().toString()) +
                    Integer.parseInt(0 + etPaishuijinkoujishui.getText().toString()) +
                    Integer.parseInt(0 + etDixiashijishui.getText().toString()) +
                    Integer.parseInt(0 + etGouqujishui.getText().toString()) +
                    Integer.parseInt(0 + etLuntaijishui.getText().toString()) +
                    Integer.parseInt(0 + etQita.getText().toString());

            etChanjianxiaoxingjishui.setText(count + "");
        }
    }


    private boolean saveData() {
        if (((InsertActivity) getActivity()).canSave()) {
            formatData();
            if (verifyInput() == 2) {
                TApplication.wenI = true;
                TApplication.wenBeanI = bean;
                ReviewDataCall.saveInserData(getActivity(),false);
            } else if (verifyInput() == 1) {
                TApplication.wenI = true;
                TApplication.wenBeanI = null;
                ReviewDataCall.saveInserData(getActivity(),false);
            } else {
                TApplication.wenI = false;
                TApplication.wenBeanI = null;
            }
        } else {
            ToastUtils.showToast("请填写检查单位名称或地点");
        }
        return false;
    }

    private void formatData() {
        Jianchalujing = dealData(etJianchalujing);
        chajianmiewendeng = dealData(etChajianmiewendeng);
        Chanjianxiaoxingjishui = dealData(etChanjianxiaoxingjishui);
        Chajianxiaoxingjishuiyangxing = dealData(etChajianxiaoxingjishuiyangxing);
        rongqijishui = dealData(etRongqijishui);
        Rongqijishuiyangxing = dealData(etRongqijishuiyangxing);
        kengwajishui = dealData(etKengwajishui);
        Kengwajishuiyangxing = dealData(etKengwajishuiyangxing);
        Jingguanchi = dealData(etJingguanchi);
        Jingguanchiyangxing = dealData(etJingguanchiyangxing);
        Paishuijinkoujishui = dealData(etPaishuijinkoujishui);
        Paishuijinkoujishuiyangxing = dealData(etPaishuijinkoujishuiyangxing);
        Dixiashijishui = dealData(etDixiashijishui);
        Dixiashijishuiyangxing = dealData(etDixiashijishuiyangxing);
        Gouqujishui = dealData(etGouqujishui);
        Gouqujishuiyangxing = dealData(etGouqujishuiyangxing);
        Luntaijishui = dealData(etLuntaijishui);
        Luntaijishuiyangxing = dealData(etLuntaijishuiyangxing);
        Qita = dealData(etQita);
        Qitayangxing = dealData(etQitayangxing);
        Youwenrenci = dealData(etYouwenrenci);
        Wenchongtingluocishu = dealData(etWenchongtingluocishu);
        Caiyanggong = dealData(etCaiyanggong);
        Yangxinggong = dealData(etYangxinggong);
        Wenyouchongheyonggong = dealData(etWenyouchongheyonggong);

        bean.setCheckDistance(Jianchalujing);
        bean.setSmallWater(Chanjianxiaoxingjishui);
        bean.setYangXinWater(Chajianxiaoxingjishuiyangxing);
        bean.setRongQi(rongqijishui);
        bean.setRongQiYangXin(Rongqijishuiyangxing);
        bean.setKengWa(kengwajishui);
        bean.setKengWaYangXin(Kengwajishuiyangxing);
        bean.setJingKou(Paishuijinkoujishui);
        bean.setJingKouYangXin(Paishuijinkoujishuiyangxing);
        bean.setJingGuanChi(Jingguanchi);
        bean.setJingGuanChiYangXin(Jingguanchiyangxing);
        bean.setDiXiaShi(Dixiashijishui);
        bean.setDiXiaShiYangXin(Dixiashijishuiyangxing);
        bean.setGouQu(Gouqujishui);
        bean.setGouQuYangXin(Gouqujishuiyangxing);
        bean.setLuntai(Luntaijishui);
        bean.setLuntaiYangXin(Luntaijishuiyangxing);
        bean.setQiTa(Qita);
        bean.setQiTaYangXin(Qitayangxing);
        bean.setYouWenRenCi(Youwenrenci);
        bean.setWenStopNum(Wenchongtingluocishu);
        bean.setMieWenDengNum(chajianmiewendeng);
        bean.setCaiYangShaoNum(Caiyanggong);
        bean.setYangXinShaoNum(Yangxinggong);
        bean.setWenYouNum(Wenyouchongheyonggong);
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


        if (bean.getCheckDistance() < 1 && bean.getYouWenRenCi() < 1 && bean.getCaiYangShaoNum() < 1
                &&!"17".equals(cheakInsertBean.getUnitClassID())) {
            return 1;
        }

        if ("17".equals(cheakInsertBean.getUnitClassID())&&bean.getCaiYangShaoNum()<1){
            return 1;
        }


        if (Chajianxiaoxingjishuiyangxing > Chanjianxiaoxingjishui) {
            ToastUtils.showWarningToast("蚊 <查见小型积水数填写>不合法");
            return 0;
        }

        if (Rongqijishuiyangxing > rongqijishui) {
            ToastUtils.showWarningToast("蚊 <容器积水数填写>不合法");
            return 0;
        }

        if (Kengwajishuiyangxing > kengwajishui) {
            ToastUtils.showWarningToast("蚊 <坑洼积水数填写>不合法");
            return 0;
        }

        if (Jingguanchiyangxing > Jingguanchi) {
            ToastUtils.showWarningToast("蚊 <景观池数填写>不合法");
            return 0;
        }

        if (Paishuijinkoujishuiyangxing > Paishuijinkoujishui) {
            ToastUtils.showWarningToast("蚊 <排水井口积水数填写>不合法");
            return 0;
        }

        if (Dixiashijishuiyangxing > Dixiashijishui) {
            ToastUtils.showWarningToast("蚊 <地下室积水数填写>不合法");
            return 0;
        }

        if (Gouqujishuiyangxing>Gouqujishui){
            ToastUtils.showWarningToast("蚊 沟渠积水数填写 不合法");
        }

        if (Luntaijishuiyangxing > Luntaijishui) {
            ToastUtils.showWarningToast("蚊 <轮胎积水数填写>不合法");
            return 0;
        }

        if (Qitayangxing > Qita) {
            ToastUtils.showWarningToast("蚊 <其他填写>不合法");
            return 0;
        }

        if (Yangxinggong > Caiyanggong) {
            ToastUtils.showWarningToast("蚊 <大中型水体采样数填写>不合法");
            return 0;
        }

        if (Jianchalujing < 1 && chajianmiewendeng > 0) {
            ToastUtils.showWarningToast("蚊 <检查路径填写>不合法");
            return 0;
        }

        if (rongqijishui + kengwajishui + Jingguanchi + Paishuijinkoujishui + Dixiashijishui + Luntaijishui + Qita >
                Chanjianxiaoxingjishui+Gouqujishui) {
            ToastUtils.showWarningToast("蚊 <查见小型积水填写>不合法");
            return 0;
        }


        if (Yangxinggong > 0 && Wenyouchongheyonggong < Yangxinggong) {
            ToastUtils.showWarningToast("蚊 <文幼虫和蛹填写>不合法");
            return 0;
        }


        if (Wenchongtingluocishu > 0 && Youwenrenci == 0) {
            ToastUtils.showWarningToast("蚊 <诱蚊人次填写>不合法");
            return 0;
        }

        if (Jianchalujing == 0 && Chanjianxiaoxingjishui > 0) {
            ToastUtils.showWarningToast("蚊 <检查路径填写>不合法");
            return 0;
        }


        return 2;
    }

}
