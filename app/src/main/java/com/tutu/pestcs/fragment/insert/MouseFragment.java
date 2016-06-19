package com.tutu.pestcs.fragment.insert;

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
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancel;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 */
public class MouseFragment extends BaseFragment {
    @Bind(R.id.et_jianchashu)
    EditText et_jianchashu;
    int jianchashu = 0;
    @Bind(R.id.et_yangxing)
    EditText et_yangxing;
    int yangxing = 0;
    @Bind(R.id.et_shufen)
    EditText et_shufen;
    int shufen = 0;
    @Bind(R.id.et_shudong)
    EditText et_shudong;
    int shudong = 0;
    @Bind(R.id.et_shudao)
    EditText et_shudao;
    int shudao = 0;
    @Bind(R.id.et_yaoheng)
    EditText et_yaoheng;
    int yaoheng = 0;
    @Bind(R.id.et_shuzhua)
    EditText et_shuzhua;
    int shuzhua = 0;
    @Bind(R.id.et_shushi)
    EditText et_shushi;
    int shushi = 0;
    @Bind(R.id.et_huoshu)
    EditText et_huoshu;
    int huoshu = 0;
    @Bind(R.id.et_sheshi_rooms)
    EditText et_sheshi_rooms;
    int sheshi_rooms = 0;
    @Bind(R.id.et_sheshi_buhege)
    EditText et_sheshi_buhege;
    int sheshi_buhege = 0;
    @Bind(R.id.et_chushuikou)
    EditText et_chushuikou;
    int chushuikou = 0;
    @Bind(R.id.et_paishuigou)
    EditText et_paishuigou;
    int paishuigou = 0;
    @Bind(R.id.et_dilou)
    EditText et_dilou;
    int dilou = 0;
    @Bind(R.id.et_paifengshan)
    EditText et_paifengshan;
    int paifengshan = 0;
    @Bind(R.id.et_chuanghu)
    EditText et_chuanghu;
    int chuanghu = 0;
    @Bind(R.id.et_menfeng)
    EditText et_menfeng;
    int menfeng = 0;
    @Bind(R.id.et_tongfengkou)
    EditText et_tongfengkou;
    int tongfengkou = 0;
    @Bind(R.id.et_kongdong)
    EditText et_kongdong;
    int kongdong = 0;
    @Bind(R.id.et_mumen)
    EditText et_mumen;
    int mumen = 0;
    @Bind(R.id.et_dangshuban)
    EditText et_dangshuban;
    int dangshuban = 0;
    @Bind(R.id.et_jianchalujing)
    EditText et_jianchalujing;
    int jianchalujing = 0;
    @Bind(R.id.et_shujiyangxing)
    EditText et_shujiyangxing;
    int shujiyangxing = 0;
    @Bind(R.id.et_wai_shufen)
    EditText et_wai_shufen;
    int wai_shufen = 0;
    @Bind(R.id.et_wai_shudong)
    EditText et_wai_shudong;
    int wai_shudong = 0;
    @Bind(R.id.et_wai_shudao)
    EditText et_wai_shudao;
    int wai_shudao = 0;
    @Bind(R.id.et_wai_yaoheng)
    EditText et_wai_yaoheng;
    int wai_yaoheng = 0;
    @Bind(R.id.et_wai_daotu)
    EditText et_wai_daotu;
    int wai_daotu = 0;
    @Bind(R.id.et_wai_shushi)
    EditText et_wai_shushi;
    int wai_shushi = 0;
    @Bind(R.id.et_wai_huoshu)
    EditText et_wai_huoshu;
    int wai_huoshu = 0;
    @Bind(R.id.et_mieshuzhan)
    EditText et_mieshuzhan;
    int mieshuzhan = 0;
    @Bind(R.id.et_wushuyao)
    EditText et_wushuyao;
    int wushuyao = 0;
    @Bind(R.id.et_shuyaowuxiao)
    EditText et_shuyaowuxiao;
    int shuyaowuxiao = 0;
    @Bind(R.id.et_fangzhibuzhengque)
    EditText et_fangzhibuzhengque;
    int fangzhibuzhengque = 0;
    @Bind(R.id.et_wujinshipai)
    EditText et_wujinshipai;
    int wujinshipai = 0;

    @Bind(R.id.ll_shuji)
    LinearLayout ll_shuji;
    @Bind(R.id.ll_sheshi_buhege)
    LinearLayout ll_sheshi_buhege;
    @Bind(R.id.ll_waihuanjingshuji)
    LinearLayout ll_waihuanjingshuji;
    @Bind(R.id.ll_qizhong)
    LinearLayout ll_qizhong;


    private CheakInsertBean cheakInsertBean;
    private ShuBean shuBean = new ShuBean();


    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView() {
        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);

        shuBean.setUnitCode(cheakInsertBean.getUnitCode());
        shuBean.setUniType(cheakInsertBean.getUnitClassID());
        shuBean.setAreaCode(cheakInsertBean.getAreaCode());
        shuBean.setTaskCode(cheakInsertBean.getTaskCode());
        shuBean.setUnitClassID(cheakInsertBean.getUnitClassID());
        shuBean.setKeyUnit(cheakInsertBean.isKeyUnit());
        shuBean.setExpertCode(cheakInsertBean.getExpertCode());

        et_yangxing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_yangxing.getText().toString().trim())) {
                    yangxing = Integer.valueOf(s.toString().trim());
                    if (yangxing > 0) {
                        ll_shuji.setVisibility(View.VISIBLE);
                    } else {
                        ll_shuji.setVisibility(View.GONE);
                    }
                } else {
                    ll_shuji.setVisibility(View.GONE);
                }


            }
        });

        et_sheshi_buhege.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_sheshi_buhege.getText().toString().trim())) {
                    if (Integer.valueOf(et_sheshi_buhege.getText().toString().trim()) > 0) {
                        ll_sheshi_buhege.setVisibility(View.VISIBLE);
                    } else {
                        ll_sheshi_buhege.setVisibility(View.GONE);
                    }
                } else {
                    ll_sheshi_buhege.setVisibility(View.GONE);
                }
            }
        });

        et_shujiyangxing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_shujiyangxing.getText().toString().trim())) {
                    if (Integer.valueOf(et_shujiyangxing.getText().toString().trim()) > 0) {
                        ll_waihuanjingshuji.setVisibility(View.VISIBLE);
                    } else {
                        ll_waihuanjingshuji.setVisibility(View.GONE);
                    }
                } else {
                    ll_waihuanjingshuji.setVisibility(View.GONE);
                }
            }
        });

        et_mieshuzhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_mieshuzhan.getText().toString().trim())) {
                    if (Integer.valueOf(et_mieshuzhan.getText().toString().trim()) > 0) {
                        ll_qizhong.setVisibility(View.VISIBLE);
                    } else {
                        ll_qizhong.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.insert_mouse_fragment;
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
                        //shuBean.setUniType(((InsertActivity) getActivity()).getUnitType());
                        ShuDao.saveOrUpdate(shuBean);
                        ToastUtils.showToast("保存成功");
                    }
                } else {
                    ToastUtils.showToast("请填写单位类型和地址,是否重点单位");
                }

                break;
        }
    }


    private void formatData() {
        jianchashu = Integer.valueOf(TextUtils.isEmpty(et_jianchashu.getText().toString().trim()) ? "0" :
                et_jianchashu.getText().toString().trim());
        yangxing = Integer.valueOf(TextUtils.isEmpty(et_yangxing.getText().toString().trim()) ? "0" : et_yangxing
                .getText().toString().trim());
        shufen = Integer.valueOf(TextUtils.isEmpty(et_shufen.getText().toString().trim()) ? "0" : et_shufen.getText()
                .toString().trim());
        shudong = Integer.valueOf(TextUtils.isEmpty(et_shudong.getText().toString().trim()) ? "0" : et_shudong.getText
                ().toString().trim());
        shudao = Integer.valueOf(TextUtils.isEmpty(et_shudao.getText().toString().trim()) ? "0" : et_shudao.getText()
                .toString().trim());
        yaoheng = Integer.valueOf(TextUtils.isEmpty(et_yaoheng.getText().toString().trim()) ? "0" : et_yaoheng.getText
                ().toString().trim());
        shuzhua = Integer.valueOf(TextUtils.isEmpty(et_shuzhua.getText().toString().trim()) ? "0" : et_shuzhua.getText
                ().toString().trim());
        shushi = Integer.valueOf(TextUtils.isEmpty(et_shushi.getText().toString().trim()) ? "0" : et_shushi.getText()
                .toString().trim());
        huoshu = Integer.valueOf(TextUtils.isEmpty(et_huoshu.getText().toString().trim()) ? "0" : et_huoshu.getText()
                .toString().trim());
        sheshi_rooms = Integer.valueOf(TextUtils.isEmpty(et_sheshi_rooms.getText().toString().trim()) ? "0" :
                et_sheshi_rooms.getText().toString().trim());
        sheshi_buhege = Integer.valueOf(TextUtils.isEmpty(et_sheshi_buhege.getText().toString().trim()) ? "0" :
                et_sheshi_buhege.getText().toString().trim());
        chushuikou = Integer.valueOf(TextUtils.isEmpty(et_chushuikou.getText().toString().trim()) ? "0" :
                et_chushuikou.getText().toString().trim());
        paishuigou = Integer.valueOf(TextUtils.isEmpty(et_paishuigou.getText().toString().trim()) ? "0" :
                et_paishuigou.getText().toString().trim());
        dilou = Integer.valueOf(TextUtils.isEmpty(et_dilou.getText().toString().trim()) ? "0" : et_dilou.getText()
                .toString().trim());
        paifengshan = Integer.valueOf(TextUtils.isEmpty(et_paifengshan.getText().toString().trim()) ? "0" :
                et_paifengshan.getText().toString().trim());
        chuanghu = Integer.valueOf(TextUtils.isEmpty(et_chuanghu.getText().toString().trim()) ? "0" : et_chuanghu
                .getText().toString().trim());
        menfeng = Integer.valueOf(TextUtils.isEmpty(et_menfeng.getText().toString().trim()) ? "0" : et_menfeng.getText
                ().toString().trim());
        tongfengkou = Integer.valueOf(TextUtils.isEmpty(et_tongfengkou.getText().toString().trim()) ? "0" :
                et_tongfengkou.getText().toString().trim());
        kongdong = Integer.valueOf(TextUtils.isEmpty(et_kongdong.getText().toString().trim()) ? "0" : et_kongdong
                .getText().toString().trim());
        mumen = Integer.valueOf(TextUtils.isEmpty(et_mumen.getText().toString().trim()) ? "0" : et_mumen.getText()
                .toString().trim());
        dangshuban = Integer.valueOf(TextUtils.isEmpty(et_dangshuban.getText().toString().trim()) ? "0" :
                et_dangshuban.getText().toString().trim());
        jianchalujing = Integer.valueOf(TextUtils.isEmpty(et_jianchalujing.getText().toString().trim()) ? "0" :
                et_jianchalujing.getText().toString().trim());
        shujiyangxing = Integer.valueOf(TextUtils.isEmpty(et_shujiyangxing.getText().toString().trim()) ? "0" :
                et_shujiyangxing.getText().toString().trim());
        wai_shufen = Integer.valueOf(TextUtils.isEmpty(et_wai_shufen.getText().toString().trim()) ? "0" :
                et_wai_shufen.getText().toString().trim());
        wai_shudong = Integer.valueOf(TextUtils.isEmpty(et_wai_shudong.getText().toString().trim()) ? "0" :
                et_wai_shudong.getText().toString().trim());
        wai_shudao = Integer.valueOf(TextUtils.isEmpty(et_wai_shudao.getText().toString().trim()) ? "0" :
                et_wai_shudao.getText().toString().trim());
        wai_yaoheng = Integer.valueOf(TextUtils.isEmpty(et_wai_yaoheng.getText().toString().trim()) ? "0" :
                et_wai_yaoheng.getText().toString().trim());
        wai_daotu = Integer.valueOf(TextUtils.isEmpty(et_wai_daotu.getText().toString().trim()) ? "0" : et_wai_daotu
                .getText().toString().trim());
        wai_shushi = Integer.valueOf(TextUtils.isEmpty(et_wai_shushi.getText().toString().trim()) ? "0" :
                et_wai_shushi.getText().toString().trim());
        wai_huoshu = Integer.valueOf(TextUtils.isEmpty(et_wai_huoshu.getText().toString().trim()) ? "0" :
                et_wai_huoshu.getText().toString().trim());
        mieshuzhan = Integer.valueOf(TextUtils.isEmpty(et_mieshuzhan.getText().toString().trim()) ? "0" :
                et_mieshuzhan.getText().toString().trim());
        wushuyao = Integer.valueOf(TextUtils.isEmpty(et_wushuyao.getText().toString().trim()) ? "0" : et_wushuyao
                .getText().toString().trim());
        shuyaowuxiao = Integer.valueOf(TextUtils.isEmpty(et_shuyaowuxiao.getText().toString().trim()) ? "0" :
                et_shuyaowuxiao.getText().toString().trim());
        fangzhibuzhengque = Integer.valueOf(TextUtils.isEmpty(et_fangzhibuzhengque.getText().toString().trim()) ? "0"
                : et_fangzhibuzhengque.getText().toString().trim());
        wujinshipai = Integer.valueOf(TextUtils.isEmpty(et_wujinshipai.getText().toString().trim()) ? "0" :
                et_wujinshipai.getText().toString().trim());

        shuBean.setBaitStation(mieshuzhan);
        shuBean.setCheckDistance(jianchalujing);
        shuBean.setCheckRoom(jianchashu);
        shuBean.setChuShuiKou(chushuikou);
        shuBean.setDangShuBan(dangshuban);
        shuBean.setDaoTu2(wai_daotu);
        shuBean.setDiLou(dilou);
        shuBean.setFangShuBadRoom(sheshi_buhege);
        shuBean.setHuoShu(huoshu);
        shuBean.setMenFeng(menfeng);
        shuBean.setNoWarningStation(wujinshipai);
        shuBean.setCheckDistance(jianchalujing);
        shuBean.setPaiShuiGou(paishuigou);
        shuBean.setFangShuRoom(sheshi_rooms);
        shuBean.setPlaceBadStation(fangzhibuzhengque);
        shuBean.setShuDao2(wai_shudao);
        shuBean.setShuDong(shudong);
        shuBean.setShuDong2(wai_shudong);
        shuBean.setZhuaYin(shuzhua);
        shuBean.setWuYaoStation(wushuyao);
        shuBean.setWuXiaoYaoStation(shuyaowuxiao);
        shuBean.setWoodDoor(mumen);
        shuBean.setWindow(chuanghu);
        shuBean.setTongFengKou(tongfengkou);
        shuBean.setShuYaoHen2(wai_yaoheng);
        shuBean.setShuShi2(wai_shushi);
        shuBean.setShuShi(shushi);
        shuBean.setShuRoom(yangxing);
        shuBean.setShuJiNum(shujiyangxing);
        shuBean.setShuFen(shufen);
        shuBean.setShuFen2(wai_shufen);
        shuBean.setShuDao(shudao);
        shuBean.setShuYaoHen(yaoheng);
        shuBean.setKongDong(kongdong);
        shuBean.setPaiFengShan(paifengshan);
        shuBean.setHuoShu2(wai_huoshu);


        verifyInput();
    }

    //检查输入合法性
    private boolean verifyInput() {
        if (shuBean.getCheckRoom()<1&&shuBean.getFangShuRoom()<1&&shuBean.getCheckDistance()<1){
            ToastUtils.showToast("录入数据未达到保存条件");
            return false;
        }


        if (yangxing > jianchashu) {
            ToastUtils.showToast("<阳性房间数填写>不合法");
            return false;
        }
        if (sheshi_buhege > sheshi_rooms) {
            ToastUtils.showToast("<不合格房间数填写>不合法");
            return false;
        }


        if (shujiyangxing > jianchalujing) {
            ToastUtils.showToast("<鼠迹阳性填写>不合法");
            return false;
        }

        if ((wushuyao + shuyaowuxiao + fangzhibuzhengque + wujinshipai) > mieshuzhan) {
            ToastUtils.showToast("<灭鼠毒饵站子类填写>不合法");
            return false;
        }

        if (yangxing>0&&shufen+shudong+shudao+yaoheng+shuzhua+shushi+huoshu<1){
            ToastUtils.showToast("鼠迹类型至少有一项大于0");
            return false;
        }

        if (sheshi_buhege>0&&dilou+chuanghu+menfeng+kongdong+mumen+chushuikou+paishuigou+paifengshan+
                tongfengkou+dangshuban<1){
            ToastUtils.showToast("防鼠设施不合格至少有一项大于0");
            return false;
        }

        if (shujiyangxing>0&&wai_shufen+wai_shudong+wai_shudao+wai_yaoheng+wai_daotu+wai_shushi
                +wai_huoshu<1){
            ToastUtils.showToast("外环境鼠迹至少有一项大于0");
            return false;
        }




        return true;
    }
}
