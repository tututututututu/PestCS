package com.tutu.pestcs.fragment.insert;

import android.content.DialogInterface;
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

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.interfaces.IOnConfirmOrCancelWithDialog;
import com.tutu.pestcs.widget.AlderDialogHelper;
import com.tutu.pestcs.widget.AlertDialogUtil;
import com.tutu.pestcs.widget.ContactDialog;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    @Bind(R.id.ll_shineishuji)
    LinearLayout llShineishuji;
    @Bind(R.id.ll_shineifangshusheshi)
    LinearLayout llShineifangshusheshi;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.base_layout)
    TuLinearLayout baseLayout;


    private CheakInsertBean cheakInsertBean;
    private ShuBean shuBean = new ShuBean();

    @Override
    public int getLayoutID() {
        return R.layout.insert_mouse_fragment;
    }


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

        if ("13".equals(cheakInsertBean.getUnitClassID())||"14".equals(cheakInsertBean.getUnitClassID())){
            llShineishuji.setVisibility(View.GONE);
            llShineifangshusheshi.setVisibility(View.GONE);
        }else {
            llShineishuji.setVisibility(View.VISIBLE);
            llShineifangshusheshi.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.btn_save, R.id.btn_exit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                AlertDialogUtil.showDialog1(mActivityContext, new IOnConfirmOrCancelWithDialog() {
                    @Override
                    public void OnConfrim(DialogInterface dialog) {
                        if (saveData()) {
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
            case R.id.btn_save:

                saveData();

                break;
        }
    }

    private boolean saveData() {
        if (((InsertActivity) getActivity()).canSave()) {
            formatData();
            if (verifyInput()) {
                //shuBean.setUniType(((InsertActivity) getActivity()).getUnitType());
                ShuDao.saveOrUpdate(shuBean);
                ToastUtils.showToast("保存成功");
                return true;
            }
        } else {
            ToastUtils.showToast("请填写检查单位名称或地点");
        }
        return false;
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
    }

    //检查输入合法性
    private boolean verifyInput() {

        if (TextUtils.isEmpty(shuBean.getUnitCode())){
            ContactDialog.show(getActivity(),getClass().getSimpleName()+"\n"+"verifyInput()"+"TextUtils.isEmpty(shuBean.getUnitCode()) is empty");
            return false;
        }

        if (cheakInsertBean.getUnitClassID().equals("17")) {
            AlderDialogHelper.showAlertDialog(getActivity(), "当前单位类型为<大中型水体>,无需保存鼠的相关数据", new DialogInterface
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


        if (shuBean.getCheckRoom() < 1 && shuBean.getFangShuRoom() < 1 && shuBean.getCheckDistance() < 1) {
            ToastUtils.showToast("录入数据未达到保存条件");
            return false;
        }

        if (yangxing > 0 && (shufen + shudong + shudao + yaoheng + shuzhua + shushi + huoshu) < yangxing) {
            ToastUtils.showToast("<阳性房间数填写>不合法");
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

        if (yangxing > 0 && shufen + shudong + shudao + yaoheng + shuzhua + shushi + huoshu < 1) {
            ToastUtils.showToast("鼠迹类型至少有一项大于0");
            return false;
        }

        if (sheshi_buhege > 0 && dilou + chuanghu + menfeng + kongdong + mumen + chushuikou + paishuigou + paifengshan +
                tongfengkou + dangshuban < 1) {
            ToastUtils.showToast("防鼠设施不合格至少有一项大于0");
            return false;
        }

        if (shujiyangxing > 0 && wai_shufen + wai_shudong + wai_shudao + wai_yaoheng + wai_daotu + wai_shushi
                + wai_huoshu < 1) {
            ToastUtils.showToast("外环境鼠迹至少有一项大于0");
            return false;
        }

        if (sheshi_buhege > 0 && (chushuikou + paishuigou + dilou + paifengshan
                + chuanghu + menfeng + tongfengkou + kongdong + mumen + dangshuban) < sheshi_buhege) {
            ToastUtils.showToast("防鼠设施不合格房间数填写不正确");
            return false;
        }

        if (shujiyangxing > 0 && (wai_shufen + wai_shudong + wai_shudao + wai_yaoheng
                + wai_daotu + wai_shushi + wai_huoshu) < shujiyangxing) {
            ToastUtils.showToast("外鼠迹阳性数不正确");
            return false;
        }

        if (mieshuzhan > 0 && wujinshipai > mieshuzhan) {
            ToastUtils.showToast("无警示牌数不正确");
            return false;
        }

        if (mieshuzhan > 0 && fangzhibuzhengque > mieshuzhan) {
            ToastUtils.showToast("放置不规范数不正确");
            return false;
        }


        if (mieshuzhan > 0 && wushuyao > mieshuzhan) {
            ToastUtils.showToast("无鼠药数不正确");
            return false;
        }

        if (mieshuzhan > 0 && shuyaowuxiao > mieshuzhan) {
            ToastUtils.showToast("鼠药无效数不正确");
            return false;
        }


        if ((wushuyao + shuyaowuxiao) > mieshuzhan) {
            ToastUtils.showToast("无鼠药+鼠药无效数填写不正确");
            return false;
        }

        if (yangxing == 0 && shufen + shudong + shudao + yaoheng + shuzhua + shushi + huoshu > 0) {
            ToastUtils.showToast("阳性房间数填写不正确");
            return false;
        }


        if (sheshi_buhege == 0 && dilou + chuanghu + menfeng + kongdong + mumen + chushuikou + paishuigou +
                paifengshan +
                tongfengkou + dangshuban > 0) {
            ToastUtils.showToast("防鼠设施不合格间数填写不正确");
            return false;
        }


        if (shujiyangxing == 0 && (wai_shufen + wai_shudong + wai_shudao + wai_yaoheng
                + wai_daotu + wai_shushi + wai_huoshu) > 0) {
            ToastUtils.showToast("外鼠迹阳性数不正确");
            return false;
        }


        return true;
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
}
