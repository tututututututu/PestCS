package com.tutu.pestcs.fragment.statistic;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.utils.ColorPhrase;
import com.tutu.pestcs.utils.FloatFormat;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 数检查数据统计老鼠
 */
public class StatisticYingFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<YingBean> yingdata;
    private StringBuilder builder;

    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        readData();
    }

    private void readData() {
        builder = new StringBuilder();
        Tasks.executeInBackground(getContext(), new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {

                yingdata = YingDao.queryCurrentTask();

                return null;
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
                updateUI();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.statistic_ying_fragment;
    }

    private void updateUI() {

        if (yingdata == null) {
            return;
        }

        if (yingdata.size() < 1) {
            return;
        }
        List<String> unity = new ArrayList<>();
        int shineijianchafangjianshu = 0;
        int shineiyangxingfangjianshu = 0;
        int shineichengyingzhishu = 0;

        int fangjianchachangsuoshu = 0;
        int fangbuhegechangsuoshu = 0;
        int fangshiwairukoumen = 0;
        int fangtongshiwaichuanghu = 0;
        int fangchufangmen = 0;
        int fangshushijian = 0;
        int fangliangcaijian = 0;
        int fangzhijierukoushipingchugui = 0;
        int fangzhijierukoushipingtangdian = 0;
        int fangqita = 0;

        int shineiyingleizishendi = 0;
        int shineiyingleizishendiyangxing = 0;
        int shenchanxiaoshouzhijierukoushipingchansuo = 0;
        int youyingchangsuo = 0;
        int chajianshineimieyingdeng = 0;
        int fangzhifangfabuzhengqueshu = 0;

        int waijianchashiwailajirongqi = 0;
        int wailajirongqiyangxing = 0;
        int gonggongcesuo = 0;
        int gonggongcesuoyangxing = 0;
        int lajizhongzhuanzhan = 0;
        int lajizhongzhuanzhanyangxing = 0;
        int jianchalujing = 0;
        int saizaizishendi = 0;
        int sanzaizishengdiyangxing = 0;


        for (YingBean bean : yingdata) {
            if (!unity.contains(bean.getUnitCode())) {
                unity.add(bean.getUnitCode());
                shineijianchafangjianshu += bean.getCheckRoom();
                shineiyangxingfangjianshu += bean.getYingRoom();
                shineichengyingzhishu += bean.getYingNum();

                fangjianchachangsuoshu += bean.getFangYingPlace();
                fangbuhegechangsuoshu += bean.getFangYingBadPlace();
                fangshiwairukoumen += bean.getGate_FangYing();
                fangtongshiwaichuanghu += bean.getWindow_FangYing();
                fangchufangmen += bean.getDoor_FangYing();
                fangshushijian += bean.getShushiRoom();
                fangliangcaijian += bean.getLiangcaiRoom();
                fangzhijierukoushipingchugui += bean.getChuGui_FangYing();
                fangzhijierukoushipingtangdian += bean.getTandian();
                fangqita += bean.getQiTa_FangYing();

                shineiyingleizishendi += bean.getInnerZhiShengDi();
                shineiyingleizishendiyangxing += bean.getInnerYangXin();
                shenchanxiaoshouzhijierukoushipingchansuo += bean.getFoodPlaceNum();
                youyingchangsuo += bean.getFoodPlaceFly();
                chajianshineimieyingdeng += bean.getLampNum();
                fangzhifangfabuzhengqueshu += bean.getLampBadPlaceNum();
                shineiyingleizishendiyangxing += bean.getInnerYangXin();
                waijianchashiwailajirongqi += bean.getLaJiRongQiNum();
                wailajirongqiyangxing += bean.getYangXinRongQi();
                gonggongcesuo += bean.getToiletNum();
                gonggongcesuoyangxing += bean.getToilet_Ying();
                lajizhongzhuanzhan += bean.getLaJiStation();
                lajizhongzhuanzhanyangxing += bean.getStation_Ying();
                jianchalujing += bean.getCheckDistance();
                saizaizishendi+=bean.getSanZaiLaJiNum();
                sanzaizishengdiyangxing += bean.getSanZaiYangXinNum();
            }
        }


        float yangxingjianyingmidu;
        if (shineiyangxingfangjianshu == 0) {
            yangxingjianyingmidu = 0;
        } else {
            yangxingjianyingmidu = (float) shineichengyingzhishu / (float) shineiyangxingfangjianshu;
        }


        float youyingfangjianyangxinglv;
        if (shineijianchafangjianshu == 0) {
            youyingfangjianyangxinglv = 0;
        } else {
            youyingfangjianyangxinglv = (float) shineiyangxingfangjianshu / (float) shineijianchafangjianshu;
        }


        String shineichengyinmidu = "A";
        if (yangxingjianyingmidu > 3) {
            shineichengyinmidu = "低于C";
        } else if (youyingfangjianyangxinglv <= 3) {
            shineichengyinmidu = "A";
        } else if (youyingfangjianyangxinglv <= 6) {
            shineichengyinmidu = "B";
        } else if (youyingfangjianyangxinglv <= 9) {
            shineichengyinmidu = "C";
        } else {
            shineichengyinmidu = "低于C";
        }

        float fangyingsheshihegelv;

        if (fangjianchachangsuoshu == 0) {
            fangyingsheshihegelv = 100;
        } else {
            fangyingsheshihegelv = (float) (fangjianchachangsuoshu - fangbuhegechangsuoshu) / (float)
                    fangjianchachangsuoshu * 100;
        }


        String fangyingsheshidengji = "A";
        if (fangyingsheshihegelv >= 98) {
            fangyingsheshidengji = "A";
        } else if (fangyingsheshihegelv >= 95) {
            fangyingsheshidengji = "B";
        } else if (fangyingsheshihegelv >= 90) {
            fangyingsheshidengji = "C";
        } else {
            fangyingsheshidengji = "低于C";
        }


        float lajirongqiyangxinglv;
        if (waijianchashiwailajirongqi==0){
            lajirongqiyangxinglv = 0;
        }else{
            lajirongqiyangxinglv = (float) wailajirongqiyangxing / waijianchashiwailajirongqi * 100;
        }

        float waizishendiyangxinglv;

        if (waijianchashiwailajirongqi + gonggongcesuo + lajizhongzhuanzhan + saizaizishendi==0){
            waizishendiyangxinglv = 0;
        }else{
            waizishendiyangxinglv = (float) (wailajirongqiyangxing + gonggongcesuoyangxing +
                    lajizhongzhuanzhanyangxing + sanzaizishengdiyangxing)
                    / (waijianchashiwailajirongqi + gonggongcesuo + lajizhongzhuanzhan + saizaizishendi) * 100;
        }


        String waizishendiyangxingdengji = "A";
        if (waizishendiyangxinglv <= 1) {
            waizishendiyangxingdengji = "A";
        } else if (waizishendiyangxinglv <= 3) {
            waizishendiyangxingdengji = "B";
        } else if (waizishendiyangxinglv <= 4) {
            waizishendiyangxingdengji = "A";
        } else {
            waizishendiyangxingdengji = "低于C";
        }


        String shumidu = "A";
        if ("低于C".equals(shineichengyinmidu) || "低于C".equals(fangyingsheshidengji) || "低于C".equals
                (waizishendiyangxingdengji)) {
            shumidu = "低于C";
        } else if ("C".equals(shineichengyinmidu) || "C".equals(fangyingsheshidengji) || "C".equals
                (waizishendiyangxingdengji)) {
            shumidu = "C";
        } else if ("B".equals(shineichengyinmidu) || "B".equals(fangyingsheshidengji) || "B".equals
                (waizishendiyangxingdengji)) {
            shumidu = "B";
        } else {
            shumidu = "A";
        }

        builder.append("检查单位数{" + unity.size() + "}个").append("\n");
        builder.append("当前蝇密度控制水平{" + shumidu + "}级").append("\n\n");

        builder.append("室内成蝇密度{" + shineichengyinmidu + "}级").append("\n");
        builder.append("检查房间数{" + shineijianchafangjianshu + "}间,").append("阳性房间数{" + shineiyangxingfangjianshu + "}间")
                .append("阳性率{" + FloatFormat.format(youyingfangjianyangxinglv) + "}%").append("\n");
        builder.append("成蝇只数{" + shineichengyingzhishu + "}只,").append("阳性间蝇密度{" + yangxingjianyingmidu + "}只/间")
                .append("\n\n");

        builder.append("防蝇设施{" + fangyingsheshidengji + "}级").append("\n");
        builder.append("检查场所数{" + fangjianchachangsuoshu + "}个,").append("不合格场所数{" + fangbuhegechangsuoshu + "}个,")
                .append("合格率{" + FloatFormat.format(fangyingsheshihegelv) + "}%").append("\n");

        builder.append("防蝇设施不合格部位:").append("\n");
        builder.append("室外入口门{" + fangshiwairukoumen + "}处,").append("通室外窗户{" + fangtongshiwaichuanghu + "}处,")
                .append("厨房门{" + fangchufangmen + "}处").append("\n");
        builder.append("熟食间{" + fangshushijian + "}处,").append("凉菜间{" + fangliangcaijian + "}处,").append("直接入口食品橱柜{"
                + fangzhijierukoushipingchugui)
                .append("}处").append("\n");
        builder.append("直接入口摊点{" + fangzhijierukoushipingtangdian + "}处,").append("其他{" + fangqita + "}处").append
                ("\n\n");

        builder.append("室内蝇类孳生地{" + shineiyingleizishendi + "}个,").append("阳性{" + shineiyingleizishendiyangxing +
                "}个").append("\n");
        builder.append("生产销售直接入口食品场所{" + shenchanxiaoshouzhijierukoushipingchansuo + "}个,").append("有蝇场所{" +
                youyingchangsuo + "}个")
                .append("\n");
        builder.append("查见室内灭蝇灯{" + chajianshineimieyingdeng + "}只,").append("放置方法不正确数{" + fangzhifangfabuzhengqueshu
                + "}个").append("\n\n");

        builder.append("室外蝇类孳生地{" + waizishendiyangxingdengji + "}级").append("\n");
        builder.append("共查蝇类孳生地{" + (waijianchashiwailajirongqi + gonggongcesuo + lajizhongzhuanzhan +
                saizaizishendi) + "}个,").append("阳性{" + (wailajirongqiyangxing + gonggongcesuoyangxing +
                lajizhongzhuanzhanyangxing + sanzaizishengdiyangxing) + "}个,")
                .append("阳性率{" + FloatFormat.format(waizishendiyangxinglv) + "}%").append("\n");
        builder.append("其中:").append("\n");
        builder.append("检查室外垃圾容器{" + waijianchashiwailajirongqi + "}个,").append("阳性{" + wailajirongqiyangxing + "}个,")
                .append("阳性率{" + FloatFormat.format(lajirongqiyangxinglv) + "}%").append("\n");

        builder.append("公共厕所{" + gonggongcesuo + "}个,").append("阳性{" + gonggongcesuoyangxing + "}个").append("\n");
        builder.append("垃圾中转站{" + lajizhongzhuanzhan + "}个,").append("阳性{" + lajizhongzhuanzhanyangxing + "}个")
                .append("\n");
        builder.append("检查路径{" + jianchalujing + "}米,").append("散在孳生地{" + saizaizishendi + "}个,")
                .append("阳性{" + sanzaizishengdiyangxing + "}个");


        CharSequence formatted = ColorPhrase.from(builder.toString())
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();

        tv_content.setText(formatted);
    }
}
