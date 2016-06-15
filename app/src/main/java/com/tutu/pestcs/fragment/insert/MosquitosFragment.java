package com.tutu.pestcs.fragment.insert;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.rg_shuiTiType)
    RadioGroup ShuiTiType;


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
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;

    @Bind(R.id.ll_xiaoxingjishui)
    LinearLayout llXiaoxingjishui;
    @Bind(R.id.tv_shuitileixing)
    TextView tvShuitileixing;


    private CheakInsertBean cheakInsertBean;
    private WenBean bean = new WenBean();


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {


        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);

        //大中型水体
        if (cheakInsertBean.getUnitClassID().equals("17")) {
            ShuiTiType.setVisibility(View.VISIBLE);
            tvShuitileixing.setVisibility(View.VISIBLE);
        } else {
            ShuiTiType.setVisibility(View.GONE);
            tvShuitileixing.setVisibility(View.GONE);
        }

        bean.setUnitCode(cheakInsertBean.getUnitCode());

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

        ShuiTiType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        etLuntaijishuiyangxing.addTextChangedListener(new MyTextWatcher());
        etQitayangxing.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

            int count = Rongqijishuiyangxing + Kengwajishuiyangxing + Jingguanchiyangxing + Paishuijinkoujishuiyangxing
                    + Dixiashijishuiyangxing + Luntaijishuiyangxing + Qitayangxing;
            etChajianxiaoxingjishuiyangxing.setText(count + "");
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.mosquitos_insert_fragment;
    }


    @OnClick({R.id.btn_save, R.id.btn_exit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                AlertDialogUtil.showDialog(mActivityContext, new IOnConfirmOrCancel() {
                    @Override
                    public void OnConfrim() {
                        getActivity().finish();
                    }

                    @Override
                    public void OnCancel() {

                    }
                });

                break;
            case R.id.btn_save:

                if (((InsertActivity) getActivity()).canSave()) {
                    formatData();
                    if (verifyInput()) {
                        WenDao.saveOrUpdate(bean);
                        ToastUtils.showToast("保存成功");
                    }
                } else {
                    ToastUtils.showToast("请填写单位类型和地址,是否重点单位");
                }


                break;
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

        return true;
    }

}
