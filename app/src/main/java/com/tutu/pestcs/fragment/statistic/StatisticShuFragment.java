package com.tutu.pestcs.fragment.statistic;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.utils.ColorPhrase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 检查数据统计老鼠
 */
public class StatisticShuFragment extends BaseFragment {

    @Bind(R.id.tv_content)
    TextView tv_content;
    private List<ShuBean> shudata;

    private TaskBean taskBean;

    private StringBuilder builder = new StringBuilder();

    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        taskBean = getArguments().getParcelable(ActivityJumpParams.TASK_BEAN);
        readData();
    }

    private void readData() {

        Tasks.executeInBackground(getContext(), new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {

                shudata = ShuDao.queryAll();

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

    private void updateUI(){

        if (shudata==null){
            return;
        }

        if (shudata.size()<1){
            return;
        }
        List<String> unity = new ArrayList<>();
        int cheakRooms = 0;
        int yangxingRooms = 0;
        int shufen = 0;
        int shudong = 0;
        int shudao = 0;
        int shuyaoheng = 0;
        int zhuaying = 0;
        int shushi = 0;
        int huoshu = 0;

        int fangcheakroom = 0;
        int fangbuhege = 0;
        int fangchushuikou = 0;
        int fangpaishuigou = 0;
        int fangdilou = 0;
        int fangmenfeng = 0;
        int fangmumen = 0;
        int fangchaunghu = 0;
        int fangkongdong = 0;
        int fangpaifengshan = 0;
        int fangtongfengkou = 0;
        int fangshipinkufangdangshuban = 0;

        int waijianchalujing = 0;
        int waishuji = 0;
        int waishufen = 0;
        int waishudong = 0;
        int waishudao = 0;
        int waiyaoheng = 0;
        int waidaotu = 0;
        int waishushi = 0;
        int waihuoshu = 0;


        int mieerzhan = 0;
        int wushuyao = 0;
        int shuyaowuxiao = 0;
        int fangzhibuzhengque = 0;
        int wujinshipai = 0;


        for (ShuBean bean:shudata){
            if (!unity.contains(bean.getUnitCode())){
                unity.add(bean.getUnitCode());
                cheakRooms+=bean.getCheckRoom();
                yangxingRooms+= bean.getShuRoom();
                shufen+=bean.getShuFen();
                shudong+=bean.getShuDong();
                shudao+=bean.getShuDao();
                shuyaoheng+=bean.getShuYaoHen();
                zhuaying+=bean.getZhuaYin();
                shushi+=bean.getShuShi();
                huoshu+=bean.getHuoShu();
                fangcheakroom+=bean.getFangShuRoom();
                fangbuhege+=bean.getFangShuBadRoom();
                fangchushuikou+=bean.getChuShuiKou();
                fangpaishuigou+=bean.getPaiShuiGou();
                fangdilou+=bean.getDiLou();
                fangmenfeng+=bean.getMenFeng();
                fangmumen+=bean.getWoodDoor();
                fangchaunghu+=bean.getWindow();
                fangkongdong+=bean.getKongDong();
                fangpaifengshan+=bean.getPaiFengShan();
                fangtongfengkou+=bean.getTongFengKou();
                fangshipinkufangdangshuban+=bean.getDangShuBan();

                waijianchalujing+=bean.getCheckDistance();
                waishuji+=bean.getShuJiNum();
                waishufen+=bean.getShuFen2();
                waishudong+=bean.getShuDong2();
                waishudao+=bean.getShuDao2();
                waiyaoheng+=bean.getShuYaoHen2();
                waidaotu+=bean.getDaoTu2();
                waishushi+=bean.getShuShi2();
                waihuoshu+=bean.getHuoShu2();

                mieerzhan+=bean.getBaitStation();
                wushuyao+=bean.getWuYaoStation();
                shuyaowuxiao+=bean.getWuXiaoYaoStation();
                fangzhibuzhengque+=bean.getPlaceBadStation();
                wujinshipai+=bean.getNoWarningStation();

            }
        }

        builder.append("检查单位数{"+unity.size()+"}个").append("\n");
        builder.append("当前鼠密度控制水平{"+0+"}级").append("\n\n");
        builder.append("检查房间数{"+cheakRooms+"}间,").append("阳性房间数{"+yangxingRooms+"}间,").append("阳性率{"+0+"}%").append("\n");
        builder.append("鼠粪{"+shufen+"}处,").append("鼠洞{"+shudong+"}个,").append("鼠道{"+shudao+"}处,").append("鼠咬痕{"+shuyaoheng+"}处").append("\n");
        builder.append("爪印{"+zhuaying+"}处,"+"鼠尸{"+shushi+"}只,").append("活鼠{"+huoshu+"}只").append("\n");
        builder.append("防鼠设施{"+0+"}级").append("\n");
        builder.append("检查房间数{"+fangcheakroom+"}间,").append("不合格房间数{"+fangbuhege+"}间,"+"合格率{"+0+"}%").append("\n");
        builder.append("防鼠设施不合格部位").append("\n");
        builder.append("出水口{"+fangchushuikou+"}处,").append("排水沟{"+fangpaishuigou+"}处,").append("地漏{"+fangdilou+"}处,")
                .append("门缝{"+fangmenfeng+"}处,").append("木门{"+fangmumen+"}处,").append("窗户{"+fangchaunghu+"}处,")
                .append("孔洞{"+fangkongdong+"}处,").append("排风扇{"+fangpaifengshan+"}处,").append("通风口{"+fangtongfengkou+"}处,")
                .append("食品库房挡鼠板{"+fangshipinkufangdangshuban+"}处").append("\n");
        builder.append("外环境鼠密度{"+0+"}级").append("\n");
        builder.append("检查路径{"+waijianchalujing+"}米,").append("鼠迹阳性{"+waishuji+"}处,").append("路径指数{"+0+"}处/千米").append("\n");
        builder.append("鼠迹类型").append("\n");

        builder.append("鼠粪{"+waishufen+"}处,").append("鼠洞{"+waishudong+"}个,").append("鼠道{"+waishudao+"}处,").append("鼠咬痕{"+waiyaoheng+"}处,")
                .append("盗土{"+waidaotu+"}处,").append("鼠尸{"+waishushi+"}只,").append("活鼠{"+waihuoshu+"}只").append("\n");
        builder.append("室外灭鼠毒饵站").append("\n");
        builder.append("查见灭鼠毒饵站{"+mieerzhan+"}个,").append("\n");
        builder.append("其中午鼠药站数{"+wushuyao+"}个,").append("鼠药无效站数{"+shuyaowuxiao+"}个").append("\n");
        builder.append("防止方法不正确数{"+fangzhibuzhengque+"}个,").append("无警示标识站数{"+wujinshipai+"}个").append("\n");


        CharSequence formatted = ColorPhrase.from(builder.toString())
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();

        tv_content.setText(formatted);
    }

    @Override
    public int getLayoutID() {
        return R.layout.statistic_shu_fragment;
    }

}
