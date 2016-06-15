package com.tutu.pestcs.fragment.statistic;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.utils.ColorPhrase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 数检查数据统计老鼠
 */
public class StatisticWenFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<WenBean> wendata;
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

                wendata = WenDao.queryAll();

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
        return R.layout.statistic_wen_fragment;
    }

    private void updateUI() {

        if (wendata == null) {
            return;
        }

        if (wendata.size() < 1) {
            return;
        }
        List<String> unity = new ArrayList<>();

        int jianchalujing = 0;
        int chajianmiewendeng = 0;
        int faxianxiaoxingjishui = 0;
        int xiaoxingjishuiyangxing = 0;
        int rongqijishui = 0;
        int rongqijishuiyangxing = 0;
        int kengwajishui = 0;
        int kengwajishuiyangxing = 0;
        int paishuijingkoujishui = 0;
        int paishuijingkoujishuiyangxing = 0;
        int jinguganchi = 0;
        int jingguanchiyangxiang = 0;
        int dixiashijishui = 0;
        int dixiashijishuiyangxing = 0;
        int luntaijishui = 0;
        int luntaijishuiyangxing = 0;
        int qita = 0;
        int qitayangxing = 0;

        int caiyanggong = 0;
        int yangxinggong = 0;
        int wenyouchongheyonggong = 0;

        int hupo = 0;
        int rengonghu = 0;
        int chitang = 0;
        int jinguanchi1 = 0;
        int gouqu = 0;
        int qita1 = 0;

        int youwenrenci = 0;
        int wenchongtingluozhishu = 0;

        int gongchashuitishu = 0;


        for (WenBean bean : wendata) {
            if (!unity.contains(bean.getUnitCode())) {


                unity.add(bean.getUnitCode());
                if (caiyanggong != 0) {
                    gongchashuitishu++;
                }

                jianchalujing += bean.getCheckDistance();
                faxianxiaoxingjishui += bean.getSmallWater();
                chajianmiewendeng += bean.getMieWenDengNum();
                xiaoxingjishuiyangxing += bean.getYangXinWater();
                rongqijishui += bean.getRongQi();
                rongqijishuiyangxing += bean.getRongQiYangXin();
                kengwajishui += bean.getKengWa();
                kengwajishuiyangxing += bean.getKengWaYangXin();
                paishuijingkoujishui += bean.getJingKou();
                paishuijingkoujishuiyangxing += bean.getJingKouYangXin();
                jinguganchi += bean.getJingGuanChi();
                jingguanchiyangxiang += bean.getJingGuanChiYangXin();
                dixiashijishui += bean.getDiXiaShi();
                dixiashijishuiyangxing += bean.getDiXiaShiYangXin();
                luntaijishui += bean.getLuntai();
                luntaijishuiyangxing += bean.getLuntaiYangXin();
                qita += bean.getQiTa();
                qitayangxing += bean.getQiTaYangXin();
                caiyanggong += bean.getCaiYangShaoNum();
                yangxinggong += bean.getYangXinShaoNum();
                wenyouchongheyonggong += bean.getWenYouNum();
                /* 1.湖泊
                        * 2.河流
                        * 3.人工湖
                        * 4.景观池
                        * 5.池塘
                        * 6.沟渠
                        * 7.其他
                */
                switch (bean.getShuiTiType()) {
                    case 1:
                        hupo++;
                        break;
                    case 2:
                        qita1++;
                        break;
                    case 3:
                        rengonghu++;
                        break;
                    case 4:
                        jinguanchi1++;
                        break;
                    case 5:
                        chitang++;
                        break;
                    case 6:
                        gouqu++;
                        break;
                    case 7:
                        qita1++;
                        break;
                }

                youwenrenci += bean.getYouWenRenCi();
                wenchongtingluozhishu += bean.getWenStopNum();
            }
        }

        float lujingzhishu;
        if (faxianxiaoxingjishui == 0) {
            lujingzhishu = 0;
        } else {
            lujingzhishu = (float) xiaoxingjishuiyangxing / (faxianxiaoxingjishui / 1000);
        }

        String xiaoxingjishuidengji = "A";
        if (lujingzhishu <= 0.1) {
            xiaoxingjishuidengji = "A";
        } else if (lujingzhishu <= 0.5) {
            xiaoxingjishuidengji = "B";
        } else if (lujingzhishu <= 0.8) {
            xiaoxingjishuidengji = "C";
        } else {
            xiaoxingjishuidengji = "低于C";
        }

        float dazhongxingcaiyangshaozhishu;
        if (caiyanggong==0){
            dazhongxingcaiyangshaozhishu = 0;
        }else{
            dazhongxingcaiyangshaozhishu = (float) yangxinggong / caiyanggong * 100;
        }




        float pingjunmeiyangxinshaozhishu;
        if (yangxinggong==0){
            pingjunmeiyangxinshaozhishu = 0;
        }else{
            pingjunmeiyangxinshaozhishu = (float) wenyouchongheyonggong / yangxinggong;
        }


        String dazhongxingshuitiwenchongmidudengji = "A";
        if (dazhongxingcaiyangshaozhishu <= 1 && pingjunmeiyangxinshaozhishu < 3) {
            dazhongxingshuitiwenchongmidudengji = "A";
        } else if (dazhongxingcaiyangshaozhishu <= 3 && pingjunmeiyangxinshaozhishu < 5) {
            dazhongxingshuitiwenchongmidudengji = "B";
        } else if (dazhongxingcaiyangshaozhishu <= 5 && pingjunmeiyangxinshaozhishu < 8) {
            dazhongxingshuitiwenchongmidudengji = "C";
        } else {
            dazhongxingshuitiwenchongmidudengji = "低于C";
        }


        String wenchongmidudengji = "A";

        float tingluozhishu;
        if (youwenrenci == 0) {
            tingluozhishu = 0;
        } else {
            tingluozhishu = wenchongtingluozhishu / youwenrenci;
        }

        if (tingluozhishu <= 0.5) {
            wenchongmidudengji = "A";
        } else if (tingluozhishu <= 1.0) {
            wenchongmidudengji = "B";
        } else if (tingluozhishu <= 1.5) {
            wenchongmidudengji = "C";
        } else {
            wenchongmidudengji = "低于C";
        }


        String zongmidu = "A";
        if ("低于C".equals(xiaoxingjishuidengji) || "低于C".equals(dazhongxingshuitiwenchongmidudengji) || "低于C".equals
                (wenchongmidudengji)) {
            zongmidu = "低于C";
        } else if ("C".equals(xiaoxingjishuidengji) || "C".equals(dazhongxingshuitiwenchongmidudengji) || "C".equals
                (wenchongmidudengji)) {
            zongmidu = "C";
        } else if ("B".equals(xiaoxingjishuidengji) || "B".equals(dazhongxingshuitiwenchongmidudengji) || "B".equals
                (wenchongmidudengji)) {
            zongmidu = "B";
        } else {
            zongmidu = "A";
        }

        builder.append("检查单位数{" + unity.size() + "}个(不包括大中型水体数)").append("\n");
        builder.append("当前蚊密度控制水平{" + zongmidu + "}级").append("\n\n");

        builder.append("检查路径{" + jianchalujing + "}米,").append("查见灭蚊灯{" + chajianmiewendeng + "}个").append("\n\n");

        builder.append("小型积水蚊虫密度{" + xiaoxingjishuidengji + "}级").append("\n");
        builder.append("发现小型积水{" + faxianxiaoxingjishui + "}处").append("\n");
        builder.append("其中阳性积水{" + xiaoxingjishuiyangxing + "}处," + "路径指数{" + lujingzhishu + "}(处/千米)").append("\n");
        builder.append("小型积水类型:").append("\n");
        builder.append("容器积水{" + rongqijishui + "}处,").append("阳性{" + rongqijishuiyangxing + "}处").append("\n");
        builder.append("坑洼积水{" + kengwajishui + "}处,").append("阳性{" + kengwajishuiyangxing + "}处").append("\n");
        builder.append("排水井积水{" + paishuijingkoujishui + "}处,").append("阳性{" + paishuijingkoujishuiyangxing + "}处")
                .append("\n");
        builder.append("景观池{" + jinguganchi + "}处,").append("阳性{" + jingguanchiyangxiang + "}处").append("\n");
        builder.append("地下室积水{" + dixiashijishui + "}处,").append("阳性{" + dixiashijishuiyangxing + "}处").append("\n");
        builder.append("轮胎积水{" + luntaijishui + "}处,").append("阳性{" + luntaijishuiyangxing + "}处").append("\n");
        builder.append("其他{" + qita + "}处,").append("阳性{" + qitayangxing + "}处").append("\n\n");

        builder.append("大中型水体蚊虫密度{" + dazhongxingshuitiwenchongmidudengji + "}级").append("\n");
        builder.append("共查{" + gongchashuitishu + "}个水体").append("\n");
        builder.append("采样共{" + caiyanggong + "}勺,").append("阳性共{" + yangxinggong + "}勺,").append("采样指数{" +
                dazhongxingcaiyangshaozhishu + "}%").append("\n");
        builder.append("蚊幼虫和蛹共{" + wenyouchongheyonggong + "}只,{" + pingjunmeiyangxinshaozhishu + "}只").append("\n\n");
        builder.append("水体类型").append("\n");
        builder.append("湖泊{" + hupo + "}处,");
        builder.append("人工湖{" + rengonghu + "}处,");
        builder.append("池塘{" + chitang + "}处,");
        builder.append("景观池{" + jinguanchi1 + "}处,");
        builder.append("沟渠{" + gouqu + "}处,");
        builder.append("其他{" + qita1 + "}处").append("\n");


        builder.append("外环境蚊虫密度{" + wenchongmidudengji + "}级").append("\n");
        builder.append("诱蚊人次{" + youwenrenci + "}人").append("蚊虫停落只数{" + wenchongtingluozhishu + "}").append("\n");
        builder.append("停落指数{" + wenchongtingluozhishu + "}");


        CharSequence formatted = ColorPhrase.from(builder.toString())
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();

        tv_content.setText(formatted);
    }
}
