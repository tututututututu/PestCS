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
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.YingDao;
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
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;
    @Bind(R.id.ll_shineichengying)
    LinearLayout llShineichengying;
    @Bind(R.id.ll_gonggongcesuo)
    LinearLayout llGonggongcesuo;
    @Bind(R.id.ll_lajizhongzhuanzhan)
    LinearLayout llLajizhongzhuanzhan;

    private String unitycode;
    private YingBean yingBean = new YingBean();


    @Override
    public int getLayoutID() {
        return R.layout.review_flies_fragment;
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
        Tasks.executeInBackground(getActivity(), new BackgroundWork<YingBean>() {
            @Override
            public YingBean doInBackground() throws Exception {
                return YingDao.queryByUnitID(unitycode);
            }
        }, new Completion<YingBean>() {
            @Override
            public void onSuccess(Context context, YingBean result) {

                if (result == null) {
                    addTextChangeListener();
                    return;
                }
                yingBean = result;
                addTextChangeListener();
                initReviewData();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

        registModifyEvent();
    }

    private void addTextChangeListener() {
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

        if ("13".equals(yingBean.getUniType()) || "14".equals(yingBean.getUniType())) {
            llShineichengying.setVisibility(View.GONE);
        } else {
            llShineichengying.setVisibility(View.VISIBLE);
        }

        if ("15".equals(yingBean.getUniType())){
            llLajizhongzhuanzhan.setVisibility(View.VISIBLE);
        }else{
            llLajizhongzhuanzhan.setVisibility(View.GONE);
        }

        if ("16".equals(yingBean.getUniType())){
            llGonggongcesuo.setVisibility(View.VISIBLE);
        }else{
            llGonggongcesuo.setVisibility(View.GONE);
        }
    }


    private void registModifyEvent() {
        subscriptions.add(RxBus.obtainEvent(ModifyModeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ModifyModeEvent>() {
                    @Override
                    public void call(ModifyModeEvent Event) {
//                        if (Event.isEditable()) {
//                            tbase.setChildEnable(tbase, true);
//                            registWacher();
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
        et_jianchafangshu.setText(yingBean.getCheckRoom() + "");
        et_yangxingfangshu.setText(yingBean.getYingRoom() + "");
        et_chengyingzshu.setText(yingBean.getYingNum() + "");
        et_jianchachangsuoshu.setText(yingBean.getFangYingPlace() + "");
        et_buhegechangsuoshu.setText(yingBean.getFangYingBadPlace() + "");
        et_shiwairumenkou.setText(yingBean.getGate_FangYing() + "");
        et_tongshiwaichuangkou.setText(yingBean.getWindow_FangYing() + "");
        et_chufangmen.setText(yingBean.getDoor_FangYing() + "");
        et_shushijian.setText(yingBean.getShushiRoom() + "");
        et_liangcaijian.setText(yingBean.getLiangcaiRoom() + "");
        et_zhijierukoushipinchugui.setText(yingBean.getChuGui_FangYing() + "");
        et_zhijierukoushipintandian.setText(yingBean.getTandian() + "");
        et_qita.setText(yingBean.getQiTa_FangYing() + "");
        et_shineiyingleizishengdi.setText(yingBean.getInnerZhiShengDi() + "");
        et_yangxing.setText(yingBean.getInnerYangXin() + "");
        et_scxszjrkspdcs.setText(yingBean.getFoodPlaceNum() + "");
        et_qizhongyouyingchangsuo.setText(yingBean.getFoodPlaceFly() + "");
        et_shineimieyingdeng.setText(yingBean.getLampNum() + "");
        et_fangzhibuzhengqueshu.setText(yingBean.getLampBadPlaceNum() + "");
        et_shiwailajirongqi.setText(yingBean.getLaJiRongQiNum() + "");
        et_shiwailajiyangxing.setText(yingBean.getYangXinRongQi() + "");
        et_gonggongcesuo.setText(yingBean.getToiletNum() + "");
        et_gonggongcesuoyangxing.setText(yingBean.getToilet_Ying() + "");
        et_lajizhongzhuanzhan.setText(yingBean.getLaJiStation() + "");
        et_lajizhongzhuanzhanyangxing.setText(yingBean.getStation_Ying() + "");
        et_jianchalujing.setText(yingBean.getCheckDistance() + "");
        et_sanzaizishendi.setText(yingBean.getSanZaiLaJiNum() + "");
        et_sanzaizishendiyangxing.setText(yingBean.getSanZaiYangXinNum() + "");
    }

    private void registWacher() {
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
    }


    private void onSave() {
        formatData();

        if (verifyInput() == 2) {
            TApplication.ying = true;
            TApplication.yingBean = yingBean;
            ReviewDataCall.saveReviewData(getActivity());
        } else if (verifyInput() == 1) {
            TApplication.ying = true;
            TApplication.yingBean = null;
            ReviewDataCall.saveReviewData(getActivity());
        } else {
            TApplication.ying = false;
            TApplication.yingBean = null;
        }

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

    private int verifyInput() {
        if (yingBean.getCheckRoom() < 1 && yingBean.getFangYingPlace() < 1 && yingBean.getFoodPlaceNum() < 1
                && yingBean.getLaJiRongQiNum() < 1 && yingBean.getToiletNum() < 1
                && yingBean.getLaJiStation() < 1 && yingBean.getCheckDistance() < 1) {
            return 1;
        }

        if (TextUtils.isEmpty(yingBean.getUnitCode())){
            CheakInsertBean cheakInsertBean = ((CheakRecoderDetail)getActivity()).getCheakInsertBean();
            yingBean.setUnitCode(cheakInsertBean.getUnitCode());
            yingBean.setUniType(cheakInsertBean.getUnitClassID());
            yingBean.setTaskCode(cheakInsertBean.getTaskCode());
            yingBean.setAreaCode(cheakInsertBean.getAreaCode());
            yingBean.setKeyUnit(cheakInsertBean.isKeyUnit());
            yingBean.setExpertCode(cheakInsertBean.getExpertCode());
        }

        if("17".equals(yingBean.getUnitCode())){
            return 1;
        }

        if (yangxingfangshu > jianchafangshu) {
            ToastUtils.showErrorToast("蝇 <阳性房间数填写>不合法");
            return 0;
        }

        if (buhegechangsuoshu > jianchachangsuoshu) {
            ToastUtils.showErrorToast("蝇 <不合格场所数填写>不合法");
            return 0;
        }

        if (qizhongyouyingchangsuo > scxszjrkspdcs) {
            ToastUtils.showErrorToast("蝇 <其中:有蝇场所数填写>不合法");
            return 0;
        }

        if (fangzhibuzhengqueshu > shineimieyingdeng) {
            ToastUtils.showErrorToast("蝇 <灭蝇灯放置不正确数填写>不合法");
            return 0;
        }

        if (shiwailajiyangxing > shiwailajirongqi) {
            ToastUtils.showErrorToast("蝇 <室外垃圾容器数填写>不合法");
            return 0;
        }

        if (gonggongcesuoyangxing > gonggongcesuo) {
            ToastUtils.showErrorToast("蝇 <公共厕所数填写>不合法");
            return 0;
        }

        if (lajizhongzhuanzhanyangxing > lajizhongzhuanzhan) {
            ToastUtils.showErrorToast("蝇 <垃圾中转站数填写>不合法");
            return 0;
        }

        if (sanzaizishendiyangxing > sanzaizishendi) {
            ToastUtils.showErrorToast("蝇 <散在孳生地数填写>不合法");
            return 0;
        }

        if (shineiyingleizishengdi < yangxing) {
            ToastUtils.showErrorToast("蝇 <室内蝇类孳生地数填写>不合法");
            return 0;
        }

        if (yangxingfangshu > 0 && chengyingzshu < 1) {
            ToastUtils.showErrorToast("蝇 <室内成蝇总数填写>不合法");
            return 0;
        }

        if (buhegechangsuoshu > 0 && shiwairumenkou + tongshiwaichuangkou + chufangmen + shushijian
                + zhijierukoushipinchugui + liangcaijian + zhijierukoushipintandian + qita < 1) {
            ToastUtils.showErrorToast("蝇 <防蝇设施不合格部位填写>不合法");
            return 0;
        }


        if (sanzaizishendi > 0 && jianchalujing < 1) {
            ToastUtils.showErrorToast("蝇 <检查路径填写>不合法");
            return 0;
        }


        if (yangxingfangshu > 0 && chengyingzshu < yangxingfangshu) {
            ToastUtils.showErrorToast("蝇 成蝇总数填写不合法");
            return 0;
        }

        if (buhegechangsuoshu > 0 && (shiwairumenkou + tongshiwaichuangkou
                + chufangmen + shushijian + zhijierukoushipinchugui + liangcaijian
                + zhijierukoushipintandian + qita) < buhegechangsuoshu) {
            ToastUtils.showErrorToast("蝇 不合格部位总数应等于或大于不合格场所数");
            return 0;
        }

        if (buhegechangsuoshu == 0 && (shiwairumenkou + tongshiwaichuangkou
                + chufangmen + shushijian + zhijierukoushipinchugui + liangcaijian
                + zhijierukoushipintandian + qita) > 0) {
            ToastUtils.showErrorToast("蝇 不合格场所数填写不正确");
            return 0;
        }

        if (yangxingfangshu == 0 && chengyingzshu > 0) {
            ToastUtils.showErrorToast("蝇 成蝇总数填写不正确");
            return 0;
        }

        return 2;
    }

}
