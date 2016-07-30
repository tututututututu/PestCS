package com.tutu.pestcs.fragment.insert;

import android.content.DialogInterface;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancelWithDialog;
import com.tutu.pestcs.widget.AlderDialogHelper;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.ContactDialog;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 * 苍蝇
 */
public class FliesFragment extends BaseFragment {
    @Bind(R.id.et_jianchafangshu)
    EditText et_jianchafangshu;
    int jianchafangshu = 0;
    @Bind(R.id.et_yangxingfangshu)
    EditText et_yangxingfangshu;
    int yangxingfangshu = 0;
    @Bind(R.id.et_chengyingzshu)
    EditText et_chengyingzshu;
    int chengyingzshu = 0;
    @Bind(R.id.et_jianchachangsuoshu)
    EditText et_jianchachangsuoshu;
    int jianchachangsuoshu = 0;
    @Bind(R.id.et_buhegechangsuoshu)
    EditText et_buhegechangsuoshu;
    int buhegechangsuoshu = 0;
    @Bind(R.id.et_shiwairumenkou)
    EditText et_shiwairumenkou;
    int shiwairumenkou = 0;
    @Bind(R.id.et_tongshiwaichuangkou)
    EditText et_tongshiwaichuangkou;
    int tongshiwaichuangkou = 0;
    @Bind(R.id.et_chufangmen)
    EditText et_chufangmen;
    int chufangmen = 0;
    @Bind(R.id.et_shushijian)
    EditText et_shushijian;
    int shushijian = 0;
    @Bind(R.id.et_zhijierukoushipinchugui)
    EditText et_zhijierukoushipinchugui;
    int zhijierukoushipinchugui = 0;
    @Bind(R.id.et_liangcaijian)
    EditText et_liangcaijian;
    int liangcaijian = 0;
    @Bind(R.id.et_zhijierukoushipintandian)
    EditText et_zhijierukoushipintandian;
    int zhijierukoushipintandian = 0;
    @Bind(R.id.et_qita)
    EditText et_qita;
    int qita = 0;
    @Bind(R.id.et_scxszjrkspdcs)
    EditText et_scxszjrkspdcs;
    int scxszjrkspdcs = 0;
    @Bind(R.id.et_qizhongyouyingchangsuo)
    EditText et_qizhongyouyingchangsuo;
    int qizhongyouyingchangsuo = 0;
    @Bind(R.id.et_shineimieyingdeng)
    EditText et_shineimieyingdeng;
    int shineimieyingdeng = 0;
    @Bind(R.id.et_fangzhibuzhengqueshu)
    EditText et_fangzhibuzhengqueshu;
    int fangzhibuzhengqueshu = 0;
    @Bind(R.id.et_shineiyingleizishengdi)
    EditText et_shineiyingleizishengdi;
    int shineiyingleizishengdi = 0;
    @Bind(R.id.et_yangxing)
    EditText et_yangxing;
    int yangxing = 0;
    @Bind(R.id.et_shiwailajirongqi)
    EditText et_shiwailajirongqi;
    int shiwailajirongqi = 0;
    @Bind(R.id.et_shiwailajiyangxing)
    EditText et_shiwailajiyangxing;
    int shiwailajiyangxing = 0;
    @Bind(R.id.et_gonggongcesuo)
    EditText et_gonggongcesuo;
    int gonggongcesuo = 0;
    @Bind(R.id.et_gonggongcesuoyangxing)
    EditText et_gonggongcesuoyangxing;
    int gonggongcesuoyangxing = 0;
    @Bind(R.id.et_lajizhongzhuanzhan)
    EditText et_lajizhongzhuanzhan;
    int lajizhongzhuanzhan = 0;
    @Bind(R.id.et_lajizhongzhuanzhanyangxing)
    EditText et_lajizhongzhuanzhanyangxing;
    int lajizhongzhuanzhanyangxing = 0;
    @Bind(R.id.et_jianchalujing)
    EditText et_jianchalujing;
    int jianchalujing = 0;
    @Bind(R.id.et_sanzaizishendi)
    EditText et_sanzaizishendi;
    int sanzaizishendi = 0;
    @Bind(R.id.et_sanzaizishendiyangxing)
    EditText et_sanzaizishendiyangxing;
    int sanzaizishendiyangxing = 0;
    @Bind(R.id.ll_fangyingsheshibuhegebuwei)
    LinearLayout ll_fangyingsheshibuhegebuwei;
    @Bind(R.id.ll_shineichengying)
    LinearLayout llShineichengying;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;
    @Bind(R.id.ll_gonggongcesuo)
    LinearLayout llGonggongcesuo;
    @Bind(R.id.ll_lajizhongzhuanzhan)
    LinearLayout llLajizhongzhuanzhan;

    private CheakInsertBean cheakInsertBean;
    private YingBean yingBean = new YingBean();


    @Override
    public int getLayoutID() {
        return R.layout.flies_insert_fragment;
    }


    @Override
    public void handleMessage(Message msg) {
    }


    @Override
    public void initView() {
        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);

        yingBean.setUnitCode(cheakInsertBean.getUnitCode());
        yingBean.setUniType(cheakInsertBean.getUnitClassID());
        yingBean.setAreaCode(cheakInsertBean.getAreaCode());
        yingBean.setTaskCode(cheakInsertBean.getTaskCode());
        yingBean.setKeyUnit(cheakInsertBean.isKeyUnit());
        yingBean.setExpertCode(cheakInsertBean.getExpertCode());

        et_buhegechangsuoshu.addTextChangedListener(new TextWatcher() {
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
                        ll_fangyingsheshibuhegebuwei.setVisibility(View.VISIBLE);
                    } else {
                        ll_fangyingsheshibuhegebuwei.setVisibility(View.GONE);
                    }
                } else {
                    ll_fangyingsheshibuhegebuwei.setVisibility(View.GONE);
                }
            }
        });

        if ("13".equals(cheakInsertBean.getUnitClassID()) || "14".equals(cheakInsertBean.getUnitClassID())) {
            llShineichengying.setVisibility(View.GONE);
        } else {
            llShineichengying.setVisibility(View.VISIBLE);
        }

        if ("15".equals(cheakInsertBean.getUnitClassID())){
            llLajizhongzhuanzhan.setVisibility(View.VISIBLE);
        }else{
            llLajizhongzhuanzhan.setVisibility(View.GONE);
        }

        if ("16".equals(cheakInsertBean.getUnitClassID())){
            llGonggongcesuo.setVisibility(View.VISIBLE);
        }else{
            llGonggongcesuo.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.btn_save, R.id.btn_exit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveDada();
                break;
            case R.id.btn_exit:
                AlertDialogUtil.showDialog1(mActivityContext, new IOnConfirmOrCancelWithDialog() {
                    @Override
                    public void OnConfrim(DialogInterface dialog) {
                        if (saveDada()) {
                            dialog.cancel();
                            getActivity().finish();
                        } else {
                            dialog.cancel();
                        }
                    }

                    @Override
                    public void OnCancel(DialogInterface dialog) {
                        getActivity().finish();
                    }
                });
                break;
        }
    }

    private boolean saveDada() {

        if (((InsertActivity) getActivity()).canSave()) {
            formatData();
            if (verifyInput()) {
                YingDao.saveOrUpdate(yingBean);
                ToastUtils.showOKToast("保存成功");
                return true;
            }
        } else {
            ToastUtils.showErrorToast("请填写检查单位名称或地点");
        }
        return false;
    }

    private void formatData() {
        jianchafangshu = dealData(et_jianchafangshu);
        yangxingfangshu = dealData(et_yangxingfangshu);
        chengyingzshu = dealData(et_chengyingzshu);
        jianchachangsuoshu = dealData(et_jianchachangsuoshu);
        buhegechangsuoshu = dealData(et_buhegechangsuoshu);
        shiwairumenkou = dealData(et_shiwairumenkou);
        tongshiwaichuangkou = dealData(et_tongshiwaichuangkou);
        chufangmen = dealData(et_chufangmen);
        shushijian = dealData(et_shushijian);
        zhijierukoushipinchugui = dealData(et_zhijierukoushipinchugui);
        liangcaijian = dealData(et_liangcaijian);
        zhijierukoushipintandian = dealData(et_zhijierukoushipintandian);
        qita = dealData(et_qita);
        scxszjrkspdcs = dealData(et_scxszjrkspdcs);
        qizhongyouyingchangsuo = dealData(et_qizhongyouyingchangsuo);
        shineimieyingdeng = dealData(et_shineimieyingdeng);
        fangzhibuzhengqueshu = dealData(et_fangzhibuzhengqueshu);
        shineiyingleizishengdi = dealData(et_shineiyingleizishengdi);
        yangxing = dealData(et_yangxing);
        shiwailajirongqi = dealData(et_shiwailajirongqi);
        shiwailajiyangxing = dealData(et_shiwailajiyangxing);
        gonggongcesuo = dealData(et_gonggongcesuo);
        gonggongcesuoyangxing = dealData(et_gonggongcesuoyangxing);
        lajizhongzhuanzhan = dealData(et_lajizhongzhuanzhan);
        lajizhongzhuanzhanyangxing = dealData(et_lajizhongzhuanzhanyangxing);
        jianchalujing = dealData(et_jianchalujing);
        sanzaizishendi = dealData(et_sanzaizishendi);
        sanzaizishendiyangxing = dealData(et_sanzaizishendiyangxing);

        yingBean.setCheckDistance(jianchalujing);
        yingBean.setYingRoom(yangxingfangshu);
        yingBean.setYingNum(chengyingzshu);
        yingBean.setYangXinRongQi(lajizhongzhuanzhanyangxing);
        yingBean.setWindow_FangYing(tongshiwaichuangkou);
        yingBean.setToiletNum(gonggongcesuo);
        yingBean.setToilet_Ying(gonggongcesuoyangxing);
        yingBean.setTandian(zhijierukoushipintandian);
        yingBean.setStation_Ying(lajizhongzhuanzhanyangxing);
        yingBean.setShushiRoom(shushijian);
        yingBean.setSanZaiYangXinNum(sanzaizishendiyangxing);
        yingBean.setYangXinRongQi(shiwailajiyangxing);
        yingBean.setSanZaiLaJiNum(sanzaizishendi);
        yingBean.setCheckRoom(jianchafangshu);
        yingBean.setDoor_FangYing(chufangmen);
        yingBean.setChuGui_FangYing(zhijierukoushipinchugui);
        yingBean.setFoodPlaceFly(qizhongyouyingchangsuo);
        yingBean.setFangYingBadPlace(buhegechangsuoshu);
        yingBean.setFoodPlaceNum(scxszjrkspdcs);
        yingBean.setGate_FangYing(shiwairumenkou);
        yingBean.setFangYingPlace(jianchachangsuoshu);
        yingBean.setLiangcaiRoom(liangcaijian);
        yingBean.setQiTa_FangYing(qita);
        yingBean.setInnerZhiShengDi(shineiyingleizishengdi);
        yingBean.setInnerYangXin(yangxing);
        yingBean.setLampNum(shineimieyingdeng);
        yingBean.setLampBadPlaceNum(fangzhibuzhengqueshu);
        yingBean.setLaJiRongQiNum(shiwailajirongqi);
        yingBean.setLaJiStation(lajizhongzhuanzhan);
    }

    private int dealData(EditText et) {
        return Integer.valueOf(TextUtils.isEmpty(et.getText().toString().trim()) ? "0" :
                et.getText().toString().trim());
    }

    private boolean verifyInput() {

        if (cheakInsertBean.getUnitClassID().equals("17")) {
            AlderDialogHelper.showAlertDialog(getActivity(), "当前单位类型为<大中型水体>,无需保存蝇的相关数据", new DialogInterface
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


        if (TextUtils.isEmpty(yingBean.getUnitCode())){
            ContactDialog.show(getActivity(),getClass().getSimpleName()+"\n"+"verifyInput()"+"TextUtils.isEmpty(yingBean.getUnitCode()) is empty");
            return false;
        }


        if (yingBean.getCheckRoom() < 1 && yingBean.getFangYingPlace() < 1 && yingBean.getFoodPlaceNum() < 1
                && yingBean.getLaJiRongQiNum() < 1 && yingBean.getToiletNum() < 1
                && yingBean.getLaJiStation() < 1 && yingBean.getCheckDistance() < 1) {
            ToastUtils.showWarningToast("录入数据未达到保存条件");
            return false;
        }


        if (yangxingfangshu > jianchafangshu) {
            ToastUtils.showWarningToast("<阳性房间数填写>不合法");
            return false;
        }

        if (buhegechangsuoshu > jianchachangsuoshu) {
            ToastUtils.showWarningToast("<不合格场所数填写>不合法");
            return false;
        }

        if (qizhongyouyingchangsuo > scxszjrkspdcs) {
            ToastUtils.showWarningToast("<其中:有蝇场所数填写>不合法");
            return false;
        }

        if (fangzhibuzhengqueshu > shineimieyingdeng) {
            ToastUtils.showWarningToast("<灭蝇灯放置不正确数填写>不合法");
            return false;
        }

        if (shiwailajiyangxing > shiwailajirongqi) {
            ToastUtils.showWarningToast("<室外垃圾容器数填写>不合法");
            return false;
        }

        if (gonggongcesuoyangxing > gonggongcesuo) {
            ToastUtils.showWarningToast("<公共厕所数填写>不合法");
            return false;
        }

        if (lajizhongzhuanzhanyangxing > lajizhongzhuanzhan) {
            ToastUtils.showWarningToast("<垃圾中转站数填写>不合法");
            return false;
        }

        if (sanzaizishendiyangxing > sanzaizishendi) {
            ToastUtils.showWarningToast("<散在孳生地数填写>不合法");
            return false;
        }

        if (shineiyingleizishengdi < yangxing) {
            ToastUtils.showWarningToast("<室内蝇类孳生地数填写>不合法");
            return false;
        }

        if (yangxingfangshu > 0 && chengyingzshu < 1) {
            ToastUtils.showWarningToast("<室内成蝇总数填写>不合法");
            return false;
        }

        if (buhegechangsuoshu > 0 && shiwairumenkou + tongshiwaichuangkou + chufangmen + shushijian
                + zhijierukoushipinchugui + liangcaijian + zhijierukoushipintandian + qita < 1) {
            ToastUtils.showWarningToast("<防蝇设施不合格部位填写>不合法");
            return false;
        }


        if (sanzaizishendi > 0 && jianchalujing < 1) {
            ToastUtils.showWarningToast("<检查路径填写>不合法");
            return false;
        }


        if (yangxingfangshu > 0 && chengyingzshu < yangxingfangshu) {
            ToastUtils.showWarningToast("成蝇总数填写不合法");
            return false;
        }

        if (buhegechangsuoshu > 0 && (shiwairumenkou + tongshiwaichuangkou
                + chufangmen + shushijian + zhijierukoushipinchugui + liangcaijian
                + zhijierukoushipintandian + qita) < buhegechangsuoshu) {
            ToastUtils.showWarningToast("不合格部位总数应等于或大于不合格场所数");
            return false;
        }

        if (buhegechangsuoshu == 0 && (shiwairumenkou + tongshiwaichuangkou
                + chufangmen + shushijian + zhijierukoushipinchugui + liangcaijian
                + zhijierukoushipintandian + qita) > 0) {
            ToastUtils.showWarningToast("不合格场所数填写不正确");
            return false;
        }

        if (yangxingfangshu == 0 && chengyingzshu > 0) {
            ToastUtils.showWarningToast("成蝇总数填写不正确");
            return false;
        }


        return true;
    }
}
