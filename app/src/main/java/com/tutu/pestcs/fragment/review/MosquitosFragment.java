package com.tutu.pestcs.fragment.review;

import android.content.Context;
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

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.event.ModifyModeEvent;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

import butterknife.Bind;
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
    @Bind(R.id.et_jingguanchiyangxing)
    EditText etJingguanchiyangxing;
    int Jingguanchiyangxing = 0;
    @Bind(R.id.et_paishuijinkoujishui)
    EditText etPaishuijinkoujishui;
    int Paishuijinkoujishui = 0;
    @Bind(R.id.et_paishuijinkoujishuiyangxing)
    EditText etPaishuijinkoujishuiyangxing;
    int Paishuijinkoujishuiyangxing = 0;

    @Bind(R.id.et_gouqujishui)
    EditText etGouqujishui;
    int Gouqujishui = 0;
    @Bind(R.id.et_gouqujishuiyangxing)
    EditText etGouqujishuiyangxing;
    int Gouqujishuiyangxing = 0;

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

    @Bind(R.id.ll_xiaoxingjishui)
    LinearLayout llXiaoxingjishui;
    @Bind(R.id.tv_shuitileixing)
    TextView tvShuitileixing;
    @Bind(R.id.ll_xiaoxingjishui_wai)
    LinearLayout llXiaoxingjishuiWai;
    @Bind(R.id.ll_youwen)
    LinearLayout llYouwen;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;


    private String unitycode;
    private WenBean bean = new WenBean();


    @Override
    public int getLayoutID() {
        return R.layout.review_mosquitos_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        tbase.setChildEnable(tbase, false);
        unitycode = getArguments().getString(ActivityJumpParams.UNITYCODE);
        if (unitycode == null) {
            ToastUtils.showToast("非法记录查询");
            return;
        }

        // TODO: 2016/6/18 查询蟑螂详情 根据unitycode
        Tasks.executeInBackground(getActivity(), new BackgroundWork<WenBean>() {
            @Override
            public WenBean doInBackground() throws Exception {
                return WenDao.queryByUnitID(unitycode);
            }
        }, new Completion<WenBean>() {
            @Override
            public void onSuccess(Context context, WenBean result) {
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


    private void registModifyEvent() {
        subscriptions.add(RxBus.obtainEvent(ModifyModeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ModifyModeEvent>() {
                    @Override
                    public void call(ModifyModeEvent Event) {
                        if (Event.isEditable()) {
                            tbase.setChildEnable(tbase, true);
                            registWather();
                        } else {
                            tbase.setChildEnable(tbase, false);
                            onSave();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }

    private void initReviewData() {
        etJianchalujing.setText(bean.getCheckDistance() + "");
        etChajianmiewendeng.setText(bean.getMieWenDengNum() + "");
        etChanjianxiaoxingjishui.setText(bean.getSmallWater() + "");
        etChajianxiaoxingjishuiyangxing.setText(bean.getYangXinWater() + "");
        etRongqijishui.setText(bean.getRongQi() + "");
        etRongqijishuiyangxing.setText(bean.getRongQiYangXin() + "");
        etKengwajishui.setText(bean.getKengWa() + "");
        etKengwajishuiyangxing.setText(bean.getKengWaYangXin() + "");
        etJingguanchi.setText(bean.getJingGuanChi() + "");
        etJingguanchiyangxing.setText(bean.getJingGuanChiYangXin() + "");
        etPaishuijinkoujishui.setText(bean.getJingKou() + "");
        etPaishuijinkoujishuiyangxing.setText(bean.getJingKouYangXin() + "");
        etDixiashijishui.setText(bean.getDiXiaShi() + "");
        etDixiashijishuiyangxing.setText(bean.getDiXiaShiYangXin() + "");
        etGouqujishui.setText(bean.getGouQu()+"");
        etGouqujishuiyangxing.setText(bean.getGouQuYangXin()+"");
        etLuntaijishui.setText(bean.getLuntai() + "");
        etLuntaijishuiyangxing.setText(bean.getLuntaiYangXin() + "");
        etQita.setText(bean.getQiTa() + "");
        etQitayangxing.setText(bean.getQiTaYangXin() + "");
        etYouwenrenci.setText(bean.getYouWenRenCi() + "");
        etWenchongtingluocishu.setText(bean.getWenStopNum() + "");
        etCaiyanggong.setText(bean.getCaiYangShaoNum() + "");
        etYangxinggong.setText(bean.getYangXinShaoNum() + "");
        etWenyouchongheyonggong.setText(bean.getWenYouNum() + "");


        /**
         * 水体类型
         * 1.湖泊
         * 2.河流
         * 3.人工湖
         * 4.景观池
         * 5.池塘
         * 6.沟渠
         * 7.其他
         */
        switch (bean.getShuiTiType()) {
            case 1:
                rgShuiTiType.check(R.id.rb_hupo);
                break;
            case 2:
                rgShuiTiType.check(R.id.rb_heliu);
                break;
            case 3:
                rgShuiTiType.check(R.id.rb_rengonghu);
                break;
            case 4:
                rgShuiTiType.check(R.id.rb_jingguanchi);
                break;
            case 5:
                rgShuiTiType.check(R.id.rb_chitang);
                break;
            case 6:
                rgShuiTiType.check(R.id.rb_gouqu);
                break;
            case 7:
                rgShuiTiType.check(R.id.rb_qita);
                break;
        }
    }

    private void registWather() {
        //大中型水体
        if (bean.getUniType().equals("17")) {
            ll_shuiti.setVisibility(View.VISIBLE);
            tvShuitileixing.setVisibility(View.VISIBLE);
            llXiaoxingjishuiWai.setVisibility(View.GONE);
            llYouwen.setVisibility(View.GONE);
        } else {
            ll_shuiti.setVisibility(View.GONE);
            tvShuitileixing.setVisibility(View.GONE);
            llXiaoxingjishuiWai.setVisibility(View.VISIBLE);
            llYouwen.setVisibility(View.VISIBLE);
        }

        etChanjianxiaoxingjishui.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    if (Integer.valueOf(s.toString().trim()) > 0) {
                        llXiaoxingjishui.setVisibility(View.VISIBLE);
                    } else {
                        llXiaoxingjishui.setVisibility(View.GONE);
                    }
                } else {
                    llXiaoxingjishui.setVisibility(View.GONE);
                }
            }
        });

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

        rbHupo.setChecked(true);
        etRongqijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etKengwajishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etJingguanchiyangxing.addTextChangedListener(new MyTextWatcher());
        etPaishuijinkoujishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etDixiashijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etGouqujishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etLuntaijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etQitayangxing.addTextChangedListener(new MyTextWatcher());
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


    private void onSave() {

        formatData();
        if (verifyInput()) {
            WenDao.saveOrUpdate(bean);
            ToastUtils.showToast("保存成功");
        }

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

    private boolean verifyInput() {
        if (TextUtils.isEmpty(bean.getUnitCode())) {
            return false;
        }


        if (bean.getCheckDistance() < 1 && bean.getYouWenRenCi() < 1 && bean.getCaiYangShaoNum() < 1
                ) {
            return false;
        }


        if (Chajianxiaoxingjishuiyangxing > Chanjianxiaoxingjishui) {
            ToastUtils.showToast("<查见小型积水数填写>不合法");
            return false;
        }

        if (Rongqijishuiyangxing > rongqijishui) {
            ToastUtils.showToast("<容器积水数填写>不合法");
            return false;
        }

        if (Kengwajishuiyangxing > kengwajishui) {
            ToastUtils.showToast("<坑洼积水数填写>不合法");
            return false;
        }

        if (Jingguanchiyangxing > Jingguanchi) {
            ToastUtils.showToast("<景观池数填写>不合法");
            return false;
        }

        if (Paishuijinkoujishuiyangxing > Paishuijinkoujishui) {
            ToastUtils.showToast("<排水井口积水数填写>不合法");
            return false;
        }

        if (Dixiashijishuiyangxing > Dixiashijishui) {
            ToastUtils.showToast("<地下室积水数填写>不合法");
            return false;
        }

        if (Gouqujishuiyangxing>Gouqujishui){
            ToastUtils.showToast("沟渠积水数填写 不合法");
        }

        if (Luntaijishuiyangxing > Luntaijishui) {
            ToastUtils.showToast("<轮胎积水数填写>不合法");
            return false;
        }

        if (Qitayangxing > Qita) {
            ToastUtils.showToast("<其他填写>不合法");
            return false;
        }

        if (Yangxinggong > Caiyanggong) {
            ToastUtils.showToast("<大中型水体采样数填写>不合法");
            return false;
        }

        if (Jianchalujing < 1 && chajianmiewendeng > 0) {
            ToastUtils.showToast("<检查路径填写>不合法");
            return false;
        }

        if (rongqijishui + kengwajishui + Jingguanchi + Paishuijinkoujishui + Dixiashijishui + Luntaijishui + Qita >
                Chanjianxiaoxingjishui+Gouqujishui) {
            ToastUtils.showToast("<查见小型积水填写>不合法");
            return false;
        }


        if (Yangxinggong > 0 && Wenyouchongheyonggong < Yangxinggong) {
            ToastUtils.showToast("<文幼虫和蛹填写>不合法");
            return false;
        }


        if (Wenchongtingluocishu > 0 && Youwenrenci == 0) {
            ToastUtils.showToast("<诱蚊人次填写>不合法");
            return false;
        }

        if (Jianchalujing == 0 && Chanjianxiaoxingjishui > 0) {
            ToastUtils.showToast("<检查路径填写>不合法");
            return false;
        }

        return true;
    }

}
