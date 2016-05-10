package com.tutu.pestcs.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.QueryBean;
import com.tutu.pestcs.bean.QueryResultBean;
import com.tutu.pestcs.bean.ShuBean;
import com.tutu.pestcs.bean.WenBean;
import com.tutu.pestcs.bean.YingBean;
import com.tutu.pestcs.bean.ZhangBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.CheakInsertDao;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.db.ZhangDao;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class QueryResult extends BaseActivity {
    public static final int TYPE_SHU = 1;
    public static final int TYPE_WEN = 2;
    public static final int TYPE_YING = 3;
    public static final int TYPE_ZHANG = 4;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_text1)
    TextView tvText1;
    @Bind(R.id.tv_text2)
    TextView tvText2;
    @Bind(R.id.tv_text3)
    TextView tvText3;
    @Bind(R.id.tv_title1)
    TextView tvTitle1;
    @Bind(R.id.tv_title2)
    TextView tvTitle2;
    @Bind(R.id.tv_title3)
    TextView tvTitle3;
    @Bind(R.id.tv_title4)
    TextView tvTitle4;
    @Bind(R.id.tl_table)
    TableLayout tlTable;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    private List<QueryResultBean> data = new ArrayList<>();


    private QueryBean queryBean;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        queryBean = getIntent().getParcelableExtra(ActivityJumpParams.QUERY_BEAN);
        if (queryBean == null) {
            svProgressHUD.showErrorWithStatus("系统错误");
            llBack.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);
        }

        if (queryBean.getQueryType() == TYPE_SHU) {
            tvText1.setText("室内鼠迹:阳性房间数/检查房间数");
            tvText2.setText("防鼠设施:不合格房间数/检查房间数");
            tvText3.setText("室外鼠迹:鼠迹阳性处数/检查路径延长米");
            tvTitle2.setText("室内鼠迹");
            tvTitle3.setText("防鼠设施");
            tvTitle4.setText("室外鼠迹");
        } else if (TYPE_WEN == queryBean.getQueryType()) {
            tvText1.setText("小型积水:蚊幼虫阳性积水处数/检查路径延长米");
            tvText2.setText("诱蚊人次:停落蚊数/人次");
            tvText3.setText("大中型水体:阳性勺数/采样勺数");
            tvTitle2.setText("小型积水");
            tvTitle3.setText("诱蚊人次");
            tvTitle4.setText("大中型水体");
        } else if (TYPE_YING == queryBean.getQueryType()) {
            tvText1.setText("室内成蝇:阳性房间数/检查房间数");
            tvText2.setText("防蝇设施:不合格场所数/检查场所数");
            tvText3.setText("蝇滋生地:阳性数/检查孳生地数");
            tvTitle2.setText("室内成蝇");
            tvTitle3.setText("防蝇设施");
            tvTitle4.setText("蝇滋生地");
        } else if (TYPE_ZHANG == queryBean.getQueryType()) {
            tvText1.setText("成虫数:阳性房间数/检查房间数");
            tvText2.setText("卵鞘:阳性房间数/检查房间数");
            tvText3.setText("蟑迹:阳性房间数/检查房间数");
            tvTitle2.setText("成虫数");
            tvTitle3.setText("卵鞘");
            tvTitle4.setText("蟑迹");
        }

        query();
    }


    @Override
    public void initData() {
        svProgressHUD.dismissImmediately();
        createTV();
        tlTable.setStretchAllColumns(true);

        for (int row = 0; row < data.size(); row++) {
            TableRow tableRow = new TableRow(this);
            for (int col = 0; col < 4; col++) {
                TextView tv = createTV();
                if (col == 0) {
                    tv.setText(data.get(row).getUnitName());
                } else if (col == 1) {
                    tv.setText(data.get(row).getCol1Start() + "/" + data.get(row).getCol1End());
                } else if (col == 2) {
                    tv.setText(data.get(row).getCol2Start() + "/" + data.get(row).getCol2End());
                } else if (col == 3) {
                    tv.setText(data.get(row).getCol3Start() + "/" + data.get(row).getCol3End());
                }
                tv.setBackground(getResources().getDrawable(R.drawable.tv_empty_rectangle));
                tableRow.addView(tv);
            }
            tlTable.addView(tableRow, new ViewGroup.LayoutParams(WC, MP));
        }
    }

    private TextView createTV() {
        return (TextView) LayoutInflater.from(this).inflate(R.layout.table_tv, null);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_query_result;
    }

    @OnClick(R.id.ll_back)
    public void onClick(View view) {
        finish();
    }

    private void query() {
        //查询分两步 1.根据单位类型和是否重点单位筛选出unitCode
        //         2.根据unitCode查询出具体单位的数据并计算组装成格式化数据
        svProgressHUD.showWithStatus("查询中...");
        queryUnitCode();
    }

    private void queryUnitCode() {
        Tasks.executeInBackground(this, new BackgroundWork<List<CheakInsertBean>>() {
            @Override
            public List<CheakInsertBean> doInBackground() throws Exception {
                return CheakInsertDao.queryByUnitTypeAndIsKeyClass(queryBean.getUnitType(), queryBean.getIsKeyUnit());
            }
        }, new Completion<List<CheakInsertBean>>() {
            @Override
            public void onSuccess(Context context, List<CheakInsertBean> result) {
                queryDetail(result);
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }

    private void queryDetail(final List<CheakInsertBean> result) {
        for (CheakInsertBean bean : result) {
            LogUtil.e("查询unitCode结果如下:");
            LogUtil.e(bean.getUnitCode());
        }

        Tasks.executeInBackground(this, new BackgroundWork<List<QueryResultBean>>() {
            @Override
            public List<QueryResultBean> doInBackground() throws Exception {

                switch (queryBean.getQueryType()) {
                    case TYPE_SHU:
                        queryShuDetail(result);
                        break;
                    case TYPE_YING:
                        queryYingDetail(result);
                        break;
                    case TYPE_ZHANG:
                        queryZhangDetail(result);
                        break;
                    case TYPE_WEN:
                        queryWenDetail(result);
                        break;
                }

                return null;
            }


        }, new Completion<List<QueryResultBean>>() {
            @Override
            public void onSuccess(Context context, List<QueryResultBean> result) {
                initData();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }

    private void queryWenDetail(List<CheakInsertBean> result) {
        for (CheakInsertBean bean : result) {
            WenBean bean1 = WenDao.queryByUnitIDWithConditon(bean.getUnitCode(), queryBean.getCondition1(), queryBean
					.getCondition2(), queryBean.getCondition3());
            if (bean1 == null) {
                LogUtil.e("没有符合要求的结果" + bean.toString());
                continue;
            }

            QueryResultBean queryResultBean = new QueryResultBean();
            queryResultBean.setUnitName(bean.getNamePlace());
            queryResultBean.setUnitCode(bean.getUnitCode());
            queryResultBean.setCol1Start(bean1.getYangXinWater());
            queryResultBean.setCol1End(bean1.getCheckDistance());
            queryResultBean.setCol2Start(bean1.getWenStopNum());
            queryResultBean.setCol2End(bean1.getYouWenRenCi());
            queryResultBean.setCol3Start(bean1.getYangXinShaoNum());
            queryResultBean.setCol3End(bean1.getCaiYangShaoNum());
            data.add(queryResultBean);

        }
    }

    private void queryZhangDetail(List<CheakInsertBean> result) {
        for (CheakInsertBean bean : result) {
            ZhangBean bean1 = ZhangDao.queryByUnitIDWithConditon(bean.getUnitCode(), queryBean.getCondition1(),
					queryBean.getCondition2(), queryBean.getCondition3());
            if (bean1 == null) {
                LogUtil.e("没有符合要求的结果" + bean.toString());
                continue;
            }

            QueryResultBean queryResultBean = new QueryResultBean();
            queryResultBean.setUnitName(bean.getNamePlace());
            queryResultBean.setUnitCode(bean.getUnitCode());
            queryResultBean.setCol1Start(bean1.getChengCongRoom());
            queryResultBean.setCol1End(bean1.getCheckRoom());
            queryResultBean.setCol2Start(bean1.getLuanQiaoRoom());
            queryResultBean.setCol2End(bean1.getCheckRoom());
            queryResultBean.setCol3Start(bean1.getZhangJiRoom());
            queryResultBean.setCol3End(bean1.getCheckRoom());
            data.add(queryResultBean);

        }
    }

    private void queryYingDetail(List<CheakInsertBean> result) {
        for (CheakInsertBean bean : result) {
            YingBean bean1 = YingDao.queryByUnitIDWithConditon(bean.getUnitCode(), queryBean.getCondition1(), queryBean.getCondition2(), queryBean.getCondition3());
            if (bean1 == null) {
                LogUtil.e("没有符合要求的结果" + bean.toString());
                continue;
            }

            QueryResultBean queryResultBean = new QueryResultBean();
            queryResultBean.setUnitName(bean.getNamePlace());
            queryResultBean.setUnitCode(bean.getUnitCode());
            queryResultBean.setCol1Start(bean1.getYingRoom());
            queryResultBean.setCol1End(bean1.getFangYingBadPlace());
            queryResultBean.setCol2Start(bean1.getFangYingPlace());
            queryResultBean.setCol2End(bean1.getFangYingPlace());
            queryResultBean.setCol3Start(bean1.getSanZaiYangXinNum());
            queryResultBean.setCol3End(bean1.getSanZaiLaJiNum());
            data.add(queryResultBean);

        }
    }

    private void queryShuDetail(List<CheakInsertBean> result) {
        for (CheakInsertBean bean : result) {
            ShuBean shuBean = ShuDao.queryByUnitIDWithConditon(bean.getUnitCode(), queryBean.getCondition1(), queryBean.getCondition2(), queryBean.getCondition3());
            if (shuBean == null) {
                LogUtil.e("没有符合要求的结果" + bean.toString());
                continue;
            }

            QueryResultBean queryResultBean = new QueryResultBean();
            queryResultBean.setUnitName(bean.getNamePlace());
            queryResultBean.setUnitCode(bean.getUnitCode());
            queryResultBean.setCol1Start(shuBean.getShuRoom());
            queryResultBean.setCol1End(shuBean.getCheckRoom());
            queryResultBean.setCol2Start(shuBean.getFangShuBadRoom());
            queryResultBean.setCol2End(shuBean.getFangShuRoom());
            queryResultBean.setCol3Start(shuBean.getShuJiNum());
            queryResultBean.setCol3End(shuBean.getCheckDistance());
            data.add(queryResultBean);

        }

    }

}
