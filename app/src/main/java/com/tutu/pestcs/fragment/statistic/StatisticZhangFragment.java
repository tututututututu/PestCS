package com.tutu.pestcs.fragment.statistic;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.db.ZhangDao;
import com.tutu.pestcs.utils.ColorPhrase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 数检查数据统计老鼠
 */
public class StatisticZhangFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<ZhangBean> zhangdata;
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

                zhangdata = ZhangDao.queryAll();

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

    private void updateUI() {

        if (zhangdata == null) {
            return;
        }

        if (zhangdata.size() < 1) {
            return;
        }
        List<String> unity = new ArrayList<>();





        int jianchafangjianshu = 0;
        int chengruochongyangxingfangjianshu = 0;
        int qizhongdalian = 0;
        int xiaoliangong = 0;
        int luanqiaoyangxingfangjianshu = 0;
        int chahuoluanqiaogong = 0;
        int zhangjiyangxingfangjianshu = 0;
        int chongshi = 0;
        int canpian = 0;
        int kongluanqiaoke = 0;
        int zhanglangfenbian = 0;
        int tuipi = 0;

        for (ZhangBean bean : zhangdata) {
            if (!unity.contains(bean.getUnitCode())) {
                unity.add(bean.getUnitCode());
                jianchafangjianshu += bean.getCheckRoom();
                chengruochongyangxingfangjianshu = bean.getChengCongRoom();
                qizhongdalian += bean.getDaLianNum();
                xiaoliangong += bean.getXiaoLianNuml();
                luanqiaoyangxingfangjianshu += bean.getLuanQiaoRoom();
                chahuoluanqiaogong += bean.getLuanQiaoNum();
                zhangjiyangxingfangjianshu += bean.getZhangJiRoom();
                chongshi += bean.getChongShi();
                canpian += bean.getCanPian();
                kongluanqiaoke += bean.getKongKe();
                zhanglangfenbian += bean.getFenBian();
                tuipi += bean.getFenBian();
            }
        }


        float chengruochongqinghailv;
        if (jianchafangjianshu==0){
            chengruochongqinghailv = 0;
        }else{
            chengruochongqinghailv = (float) chengruochongyangxingfangjianshu / (float)jianchafangjianshu * 100;
        }

        float dalianpingjunyangxingjian;
        if (chengruochongyangxingfangjianshu==0){
            dalianpingjunyangxingjian = 0;
        }else{
            dalianpingjunyangxingjian = (float) qizhongdalian / (float)chengruochongyangxingfangjianshu;
        }


        float xiaolianpingjunyangxingjian;
        if (chengruochongyangxingfangjianshu==0){
            xiaolianpingjunyangxingjian = 0;
        }else{
            xiaolianpingjunyangxingjian = (float) xiaoliangong / (float)chengruochongyangxingfangjianshu;
        }


        float luanqiaoyangxingfangjianshuchahuolv;
        if (jianchafangjianshu==0){
            luanqiaoyangxingfangjianshuchahuolv = 0;
        }else {
            luanqiaoyangxingfangjianshuchahuolv = (float) luanqiaoyangxingfangjianshu / (float)jianchafangjianshu * 100;
        }


        float chahuoluanqiaopingjunyangxingjian;
        if (luanqiaoyangxingfangjianshu==0){
            chahuoluanqiaopingjunyangxingjian = 0;
        }else {
            chahuoluanqiaopingjunyangxingjian = (float) chahuoluanqiaogong / (float)luanqiaoyangxingfangjianshu;
        }


        float zhangjiyangxingfangjianchahuolv;
        if (jianchafangjianshu==0){
            zhangjiyangxingfangjianchahuolv = 0;
        }else{
            zhangjiyangxingfangjianchahuolv = (float)zhangjiyangxingfangjianshu / (float)jianchafangjianshu*100;
        }


        String chengruochongqianhaidengji = "A";
        String luanqiaochahuolvdengji = "A";
        String zhangjichahuolv = "A";

        if (chengruochongqinghailv <= 1 && xiaolianpingjunyangxingjian <= 5 && dalianpingjunyangxingjian <= 2) {
            chengruochongqianhaidengji = "A";
        } else if (chengruochongqinghailv <= 3 && xiaolianpingjunyangxingjian <= 10 && dalianpingjunyangxingjian <= 5) {
            chengruochongqianhaidengji = "B";
        } else if (chengruochongqinghailv <= 5 && xiaolianpingjunyangxingjian <= 10 && dalianpingjunyangxingjian <= 5) {
            chengruochongqianhaidengji = "C";
        } else {
            chengruochongqianhaidengji = "低于C";
        }

        if (luanqiaoyangxingfangjianshuchahuolv <= 1 && chahuoluanqiaopingjunyangxingjian <= 2) {
            luanqiaochahuolvdengji = "A";
        } else if (luanqiaoyangxingfangjianshuchahuolv <= 2 && chahuoluanqiaopingjunyangxingjian <= 4) {
            luanqiaochahuolvdengji = "B";
        } else if (luanqiaoyangxingfangjianshuchahuolv <= 3 && chahuoluanqiaopingjunyangxingjian <= 8) {
            luanqiaochahuolvdengji = "C";
        } else {
            luanqiaochahuolvdengji = "低于C";
        }

        if(zhangjiyangxingfangjianchahuolv<=3){
            zhangjichahuolv = "A";
        }else if(zhangjiyangxingfangjianchahuolv<=5){
            zhangjichahuolv = "B";
        }else if(zhangjiyangxingfangjianchahuolv<=7){
            zhangjichahuolv = "C";
        }else{
            zhangjichahuolv = "低于C";
        }


        String midukongzhishuiping = "A";
        if ("低于C".equals(chengruochongqianhaidengji) || "低于C".equals(luanqiaochahuolvdengji) || "低于C".equals(zhangjichahuolv)) {
            midukongzhishuiping = "低于C";
        } else if ("C".equals(chengruochongqianhaidengji) || "C".equals(luanqiaochahuolvdengji) || "C".equals(zhangjichahuolv)) {
            midukongzhishuiping = "C";
        } else if ("B".equals(chengruochongqianhaidengji) || "B".equals(luanqiaochahuolvdengji) || "B".equals(zhangjichahuolv)) {
            midukongzhishuiping = "B";
        } else {
            midukongzhishuiping = "A";
        }






        builder.append("检查单位数{" + unity.size() + "}个").append("\n");
        builder.append("当前蟑螂密度控制水平{" + midukongzhishuiping + "}级").append("\n\n");

        builder.append("检查房间数{" + jianchafangjianshu + "}间").append("\n\n");

        builder.append("成若虫侵害率{" + chengruochongqianhaidengji + "}级").append("\n");
        builder.append("成若虫阳性房间数{" + chengruochongyangxingfangjianshu + "}间,").append("侵害率{" + chengruochongqinghailv + "}%").append("\n");
        builder.append("其中大蠊共{" + qizhongdalian + "}只,").append("平均每阳性间{" + dalianpingjunyangxingjian + "}只").append("\n");
        builder.append("    小蠊共{" + xiaoliangong + "}只,").append("平均每阳性间{" + xiaolianpingjunyangxingjian + "}只").append("\n\n");

        builder.append("乱鞘查获率{" + luanqiaochahuolvdengji + "}级").append("\n");
        builder.append("卵鞘阳性房间数{" + luanqiaoyangxingfangjianshu + "}间,").append("查获率{" + luanqiaoyangxingfangjianshuchahuolv + "}%").append("\n");
        builder.append("查获卵鞘共{" + chahuoluanqiaogong + "}只,").append("平均每阳性间{" + chahuoluanqiaopingjunyangxingjian + "}只").append("\n\n");

        builder.append("蟑迹查获率{" + zhangjichahuolv + "}级").append("\n");
        builder.append("蟑迹阳性房间数{" + zhangjiyangxingfangjianshu + "}间,").append("查获率{" + zhangjiyangxingfangjianchahuolv + "}%").append("\n");
        builder.append("蟑迹类型").append("\n");
        builder.append("虫尸{" + chongshi + "}个," + "残片{" + canpian + "}个," + "空卵鞘壳{" + kongluanqiaoke + "}个").append
                ("\n");
        builder.append("蟑螂粪便{" + zhanglangfenbian + "}处," + "蜕皮{" + tuipi + "}个");


        CharSequence formatted = ColorPhrase.from(builder.toString())
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();

        tv_content.setText(formatted);
    }

    @Override
    public int getLayoutID() {
        return R.layout.statistic_zhang_fragment;
    }


}
