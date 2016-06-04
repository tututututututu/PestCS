package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.widget.AlertDialogUtil;
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


    private CheakInsertBean cheakInsertBean;
    private ZhangBean bean = new ZhangBean();


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);
        bean.setUnitCode(cheakInsertBean.getUnitCode());

    }

    @Override
    public int getLayoutID() {
        return R.layout.cock_insert_fragment;
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
                        ZhangDao.saveOrUpdate(bean);
                        ToastUtils.showToast("保存成功");
                    }
                } else {
                    ToastUtils.showToast("请填写单位类型和地址,是否重点单位");
                }

                break;
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

    private boolean verifyInput() {
        if (chengchongyangxingfangjianshu > jianchafangshu) {
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu < 1 && (chongshi + canpian + tuipi + kongluanqiaoke + zhanglangfenbian) > 0) {
            ToastUtils.showToast("<蟑迹阳性房间数填写>不合法");
            return false;
        }

        if (chengchongyangxingfangjianshu>0&&dalian+xiaolian<1){
            ToastUtils.showToast("<成若虫阳性间数填写>不合法");
            return false;
        }

        if (luanqiaoxiangxingfangjianshu>jianchafangshu){
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }

        if (zhangjiyangxingfangjianshu>jianchafangshu){
            ToastUtils.showToast("<检查房间数填写>不合法");
            return false;
        }

        return true;
    }
}
