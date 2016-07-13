package com.tutu.pestcs.fragment.insert;

import android.content.DialogInterface;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancelWithDialog;
import com.tutu.pestcs.widget.AlderDialogHelper;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.ContactDialog;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

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
                if (TextUtils.isEmpty(s.toString().trim())){
                    ll_zhangjileixing.setVisibility(View.GONE);
                    return;
                }
                if (Integer.parseInt(s.toString().trim())>0){
                    ll_zhangjileixing.setVisibility(View.VISIBLE);
                }else {
                    ll_zhangjileixing.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.btn_save, R.id.btn_exit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                AlertDialogUtil.showDialog1(mActivityContext, new IOnConfirmOrCancelWithDialog() {
                    @Override
                    public void OnConfrim(DialogInterface dialog) {
                        if (saveDada()){
                            dialog.cancel();
                            getActivity().finish();
                        }else {
                            dialog.cancel();
                        }
                    }

                    @Override
                    public void OnCancel(DialogInterface dialog) {
                        getActivity().finish();
                    }
                });

                break;
            case R.id.btn_save:
                saveDada();
                break;
        }
    }

    private boolean saveDada() {
        if (((InsertActivity) getActivity()).canSave()) {
            formatData();
            if (verifyInput()) {
                ZhangDao.saveOrUpdate(bean);
                ToastUtils.showToast("保存成功");
                return true;
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

    private boolean verifyInput() {

        if (cheakInsertBean.getUnitClassID().equals("17")) {
            AlderDialogHelper.showAlertDialog(getActivity(), "当前单位类型为<大中型水体>,无需保存蟑螂的相关数据", new DialogInterface
                    .OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            return false;
        }


        if (TextUtils.isEmpty(bean.getUnitCode())){
            ContactDialog.show(getActivity(),getClass().getSimpleName()+"\n"+"verifyInput()"+"TextUtils.isEmpty(bean.getUnitCode()) is empty");
            return false;
        }

        if (bean.getCheckRoom() < 1) {
            ToastUtils.showToast("录入数据未达到保存条件");
            return false;
        }


        if (chengchongyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) != 0) {
            ToastUtils.showToast("<蟑迹阳性房间数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu>0&&(chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) <zhangjiyangxingfangjianshu)
        {
            ToastUtils.showToast("<蟑迹阳性房间数填写>不合法");
            return false;
        }

        if (chengchongyangxingfangjianshu > 0 && dalian + xiaolian < 1) {
            ToastUtils.showToast("<成若虫阳性间数填写>不合法");
            return false;
        }

        if (chengchongyangxingfangjianshu == 0 && (dalian + xiaolian) > 0) {
            ToastUtils.showToast("<成若虫阳性间数填写>不合法");
            return false;
        }

        if (chengchongyangxingfangjianshu > 0 && ((dalian + xiaolian) < chengchongyangxingfangjianshu)) {
            ToastUtils.showToast("<成若虫阳性间数填写>不合法");
            return false;
        }

        if (luanqiaoxiangxingfangjianshu > jianchafangshu) {
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }


        if (luanqiaoxiangxingfangjianshu == 0 && chahuoluanqiaoshu > 0) {
            ToastUtils.showToast("<查获卵鞘数填写>不合法");
            return false;
        }

        if (luanqiaoxiangxingfangjianshu > 0 && chahuoluanqiaoshu < luanqiaoxiangxingfangjianshu) {
            ToastUtils.showToast("<查获卵鞘数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu == 0 && (chongshi + canpian + kongluanqiaoke + zhanglangfenbian + tuipi) > 0) {
            ToastUtils.showToast("<蟑螂阳性房间数填写>不合法");
            return false;
        }




        return true;
    }
}
