package com.tutu.pestcs.fragment.progress;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.KeyValueDataBean;
import com.tutu.pestcs.bean.ProgressMouse;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.GuoBiaoUnitDao;
import com.tutu.pestcs.db.ShuDao;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.utils.ComputeUtils;
import com.tutu.pestcs.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 老鼠
 */
public class MouseFragment extends BaseFragment {

    @Bind(R.id.tl_table)
    TableLayout tl_table;
    @Bind(R.id.tl_table_wai)
    TableLayout tl_table_wai;


    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;


    private List<ProgressMouse> inDoorData = new ArrayList<>();
    private List<ProgressMouse> outDoorData = new ArrayList<>();

    private int groupNum=1;

    @Override
    public int getLayoutID() {
        return R.layout.mouse_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        getCurrentTask();
    }

    private void getCurrentTask() {
        Tasks.executeInBackground(getContext(), new BackgroundWork<TaskBean>() {
            @Override
            public TaskBean doInBackground() throws Exception {
                return TaskDao.queryCurrent();
            }
        }, new Completion<TaskBean>() {
            @Override
            public void onSuccess(Context context, TaskBean result) {
                if (result == null) {
                    ToastUtils.showToast("没有设置当前任务");
                    return;
                }
                groupNum = result.getGroups();
                if (groupNum==0){
                    groupNum = 1;
                }
                fillData();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }


    private void fillData() {
        createTV();
        initDataIn();
        initDataOut();
        tl_table.setStretchAllColumns(true);
        tl_table_wai.setStretchAllColumns(true);

        for (int row = 0; row < inDoorData.size(); row++) {
            TableRow tableRow = new TableRow(mActivityContext);
            for (int col = 0; col < 4; col++) {
                TextView tv = createTV();
                if (col == 0) {
                    tv.setText(inDoorData.get(row).getUnitType());
                } else if (col == 1) {
                    if (inDoorData.get(row).getOriginalNumS()==0){
                        tv.setText("/"+ "-" + inDoorData.get(row).getOriginalNumE());
                    }else{
                        tv.setText(inDoorData.get(row).getOriginalNumS() + "-" + inDoorData.get(row).getOriginalNumE());
                    }
                } else if (col == 2) {
                    tv.setText(inDoorData.get(row).getCheakedNumS() + "-" + inDoorData.get(row).getCheakedNumE());
                } else if (col == 3) {

                    int s = inDoorData.get(row).getToCheakNumS();
                    int e = inDoorData.get(row).getToCheakNumE();
                    StringBuilder builder = new StringBuilder();

                    if (s == 0) {
                        builder.append("/");
                    } else if (s <= 0) {
                        builder.append("0");
                    } else {
                        builder.append(s);
                    }

                    builder.append("-");

                    if (e == 0) {
                        builder.append("/");
                    } else if (e <= 0) {
                        builder.append("0");
                    } else {
                        builder.append(e);
                    }

                    tv.setText(builder.toString());
                }


                tv.setBackground(getResources().getDrawable(R.drawable.tv_empty_rectangle));
                tableRow.addView(tv);
            }
            tl_table.addView(tableRow, new ViewGroup.LayoutParams(WC, MP));
        }


        for (int row = 0; row < outDoorData.size(); row++) {
            TableRow tableRow = new TableRow(mActivityContext);
            for (int col = 0; col < 4; col++) {
                TextView tv = createTV();
                if (col == 0) {
                    tv.setText(outDoorData.get(row).getUnitType());
                } else if (col == 1) {
                    if (outDoorData.get(row).getOriginalNumS()==0){
                        tv.setText("/" + "-" + outDoorData.get(row).getOriginalNumE());

                    }else {
                        tv.setText(outDoorData.get(row).getOriginalNumS() + "-" + outDoorData.get(row).getOriginalNumE());

                    }
                } else if (col == 2) {
                    tv.setText(outDoorData.get(row).getCheakedNumS() + "-" + outDoorData.get(row).getCheakedNumE());
                } else if (col == 3) {
                    int s = outDoorData.get(row).getToCheakNumS();
                    int e = outDoorData.get(row).getToCheakNumE();
                    StringBuilder builder = new StringBuilder();

                    if (s == 0) {
                        builder.append("/");
                    } else if (s <= 0) {
                        builder.append("0");
                    } else {
                        builder.append(s);
                    }

                    builder.append("-");

                    if (e == 0) {
                        builder.append("/");
                    } else if (e <= 0) {
                        builder.append("0");
                    } else {
                        builder.append(e);
                    }

                    tv.setText(builder.toString());
                }


                tv.setBackground(getResources().getDrawable(R.drawable.tv_empty_rectangle));
                tableRow.addView(tv);
            }
            tl_table_wai.addView(tableRow, new ViewGroup.LayoutParams(WC, MP));
        }
    }



    private TextView createTV() {
        return (TextView) LayoutInflater.from(mActivityContext).inflate(R.layout.table_tv, null);
    }

    private void initDataIn() {
        inDoorData.clear();

        TaskBean currentTask = TaskDao.queryCurrent();
        if (currentTask == null) {
            ToastUtils.showToast("您可能未设置当前任务");
            return;
        }
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("shu_in", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 10) {
            return;
        }


        int totelTocheakUnit = 0;
        int totelTocheakRoom = 0;
        int totleCheakedUnit = 0;
        int totleCheakedRoom = 0;

        int hadCCheakedUnitIn01 = ShuDao.getHadCheakedUnitInCount("01");
        int hadCCheakedRoomIn01 = ShuDao.getHadCheakedRoomInCount("01");
        totleCheakedUnit += hadCCheakedUnitIn01;
        totleCheakedRoom += hadCCheakedRoomIn01;
        addRow("餐饮店", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);

        int hadCCheakedUnitIn02 = ShuDao.getHadCheakedUnitInCount("02");
        int hadCCheakedRoomIn02 = ShuDao.getHadCheakedRoomInCount("02");
        totleCheakedUnit += hadCCheakedUnitIn02;
        totleCheakedRoom += hadCCheakedRoomIn02;
        addRow("商场,超市", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1).getValue
                ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn03 = ShuDao.getHadCheakedUnitInCount("03") + ShuDao.getHadCheakedUnitInCount("11") +
                ShuDao.getHadCheakedUnitInCount("12");
        int hadCCheakedRoomIn03 = ShuDao.getHadCheakedRoomInCount("03") + ShuDao.getHadCheakedRoomInCount("11") +
                ShuDao.getHadCheakedRoomInCount("12");
        totleCheakedUnit += hadCCheakedUnitIn03;
        totleCheakedRoom += hadCCheakedRoomIn03;
        addRow("机关,企业单位", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2)
                .getValue
                ()), hadCCheakedUnitIn03, hadCCheakedRoomIn03);

        int hadCCheakedUnitIn04 = ShuDao.getHadCheakedUnitInCount("04");
        int hadCCheakedRoomIn04 = ShuDao.getHadCheakedRoomInCount("04");
        totleCheakedUnit += hadCCheakedUnitIn04;
        totleCheakedRoom += hadCCheakedRoomIn04;
        addRow("宾馆饭店", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3).getValue
                ()), hadCCheakedUnitIn04, hadCCheakedRoomIn04);

        int hadCCheakedUnitIn05 = ShuDao.getHadCheakedUnitInCount("05");
        int hadCCheakedRoomIn05 = ShuDao.getHadCheakedRoomInCount("05");
        totleCheakedUnit += hadCCheakedUnitIn05;
        totleCheakedRoom += hadCCheakedRoomIn05;
        addRow("农贸市场", Integer.parseInt(toCheakedBean.get(4).getKey()), Integer.parseInt(toCheakedBean.get(4).getValue
                ()), hadCCheakedUnitIn05, hadCCheakedRoomIn05);

        int hadCCheakedUnitIn10 = ShuDao.getHadCheakedUnitInCount("06");
        int hadCCheakedRoomIn10 = ShuDao.getHadCheakedRoomInCount("06");
        totleCheakedUnit += hadCCheakedUnitIn10;
        totleCheakedRoom += hadCCheakedRoomIn10;
        addRow("机场或车站", Integer.parseInt(toCheakedBean.get(9).getKey()), Integer.parseInt(toCheakedBean.get(9).getValue
                ()), hadCCheakedUnitIn10, hadCCheakedRoomIn10);

        int hadCCheakedUnitIn07 = ShuDao.getHadCheakedUnitInCount("07");
        int hadCCheakedRoomIn07 = ShuDao.getHadCheakedRoomInCount("07");
        totleCheakedUnit += hadCCheakedUnitIn07;
        totleCheakedRoom += hadCCheakedRoomIn07;
        addRow("医院", Integer.parseInt(toCheakedBean.get(6).getKey()), Integer.parseInt(toCheakedBean.get(6).getValue
                ()), hadCCheakedUnitIn07, hadCCheakedRoomIn07);

        int hadCCheakedUnitIn06 = ShuDao.getHadCheakedUnitInCount("08");
        int hadCCheakedRoomIn06 = ShuDao.getHadCheakedRoomInCount("08");
        totleCheakedUnit += hadCCheakedUnitIn06;
        totleCheakedRoom += hadCCheakedRoomIn06;
        addRow("学校", Integer.parseInt(toCheakedBean.get(5).getKey()), Integer.parseInt(toCheakedBean.get(5).getValue
                ()), hadCCheakedUnitIn06, hadCCheakedRoomIn06);



        int hadCCheakedUnitIn08 = ShuDao.getHadCheakedUnitInCount("09");
        int hadCCheakedRoomIn08 = ShuDao.getHadCheakedRoomInCount("09");
        totleCheakedUnit += hadCCheakedUnitIn08;
        totleCheakedRoom += hadCCheakedRoomIn08;
        addRow("建筑拆迁工地", Integer.parseInt(toCheakedBean.get(7).getKey()), Integer.parseInt(toCheakedBean.get(7).getValue
                ()), hadCCheakedUnitIn08, hadCCheakedRoomIn08);

        int hadCCheakedUnitIn09 = ShuDao.getHadCheakedUnitInCount("10");
        int hadCCheakedRoomIn09 = ShuDao.getHadCheakedRoomInCount("10");
        totleCheakedUnit += hadCCheakedUnitIn09;
        totleCheakedRoom += hadCCheakedRoomIn09;
        addRow("居委会", Integer.parseInt(toCheakedBean.get(8).getKey()), Integer.parseInt(toCheakedBean.get(8).getValue
                ()), hadCCheakedUnitIn09, hadCCheakedRoomIn09);




        for (KeyValueDataBean bean : toCheakedBean) {
            totelTocheakUnit += Integer.parseInt(bean.getKey());
            totelTocheakRoom += Integer.parseInt(bean.getValue());
        }
        addRow("合计", totelTocheakUnit, totelTocheakRoom, totleCheakedUnit, totleCheakedRoom);
    }


    private void initDataOut() {
        outDoorData.clear();

        TaskBean currentTask = TaskDao.queryCurrent();
        if (currentTask == null) {
            return;
        }
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("shu_out", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 4) {
            return;
        }


        int totelTocheakUnit = 0;
        int totelTocheakRoom = 0;
        int totleCheakedUnit = 0;
        int totleCheakedRoom = 0;

        int hadCCheakedUnitIn01 = ShuDao.getHadCheakedUnitInCountWai("13")+ShuDao.getHadCheakedUnitInCountWai("14");
        int hadCCheakedRoomIn01 = ShuDao.getHadCheakedRoomInCountWai("13")+ShuDao.getHadCheakedRoomInCountWai("14");
        totleCheakedUnit += hadCCheakedUnitIn01;
        totleCheakedRoom += hadCCheakedRoomIn01;
        addRowOut("公共绿地、公园或道路两侧", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);

        int hadCCheakedUnitIn08 = ShuDao.getHadCheakedUnitInCountWai("15")+ShuDao.getHadCheakedUnitInCountWai("16");
        int hadCCheakedRoomIn08 = ShuDao.getHadCheakedRoomInCountWai("15")+ShuDao.getHadCheakedRoomInCountWai("16");
        totleCheakedUnit += hadCCheakedUnitIn08;
        totleCheakedRoom += hadCCheakedRoomIn08;
        addRowOut("垃圾中转站或公共厕所", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1).getValue
                ()), hadCCheakedUnitIn08, hadCCheakedRoomIn08);

        int hadCCheakedUnitIn09 = ShuDao.getHadCheakedUnitInCountWai("01")+ShuDao.getHadCheakedUnitInCountWai("02")+ShuDao.getHadCheakedUnitInCountWai("03")+ShuDao.getHadCheakedUnitInCountWai("04")+ShuDao.getHadCheakedUnitInCountWai("07")+ShuDao.getHadCheakedUnitInCountWai("08")+ShuDao.getHadCheakedUnitInCountWai("10")+ShuDao.getHadCheakedUnitInCountWai("11")+ShuDao.getHadCheakedUnitInCountWai("12")+ShuDao.getHadCheakedUnitInCountWai("18");
        int hadCCheakedRoomIn09 = ShuDao.getHadCheakedRoomInCountWai("01")+ShuDao.getHadCheakedRoomInCountWai("02")+ShuDao.getHadCheakedRoomInCountWai("03")+ShuDao.getHadCheakedRoomInCountWai("04")+ShuDao.getHadCheakedRoomInCountWai("07")+ShuDao.getHadCheakedRoomInCountWai("08")+ShuDao.getHadCheakedRoomInCountWai("10")+ShuDao.getHadCheakedRoomInCountWai("11")+ShuDao.getHadCheakedRoomInCountWai("12")+ShuDao.getHadCheakedRoomInCountWai("18");
        totleCheakedUnit += hadCCheakedUnitIn09;
        totleCheakedRoom += hadCCheakedRoomIn09;
        addRowOut("单位或居民区院内", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2).getValue
                ()), hadCCheakedUnitIn09, hadCCheakedRoomIn09);

        int hadCCheakedUnitIn10 = ShuDao.getHadCheakedUnitInCountWai("05")+ShuDao.getHadCheakedUnitInCountWai("06")+ShuDao.getHadCheakedUnitInCountWai("09");
        int hadCCheakedRoomIn10 = ShuDao.getHadCheakedRoomInCountWai("05")+ShuDao.getHadCheakedRoomInCountWai("06")+ShuDao.getHadCheakedRoomInCountWai("09");
        totleCheakedUnit += hadCCheakedUnitIn10;
        totleCheakedRoom += hadCCheakedRoomIn10;
        addRowOut("农贸市场、工地或车站", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3).getValue
                ()), hadCCheakedUnitIn10, hadCCheakedRoomIn10);


        for (KeyValueDataBean bean : toCheakedBean) {
            totelTocheakUnit += Integer.parseInt(bean.getKey());
            totelTocheakRoom += Integer.parseInt(bean.getValue());
        }
        addRowOut("合计", totelTocheakUnit, totelTocheakRoom, totleCheakedUnit, totleCheakedRoom);
    }


    private void addRow(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
        inDoorData.add(new ProgressMouse(name,
                ComputeUtils.floatUp(toCheakUnit,groupNum),
                ComputeUtils.floatUp(toCheakRoom,groupNum),
                hadCheakUnit,
                hadCheakRoom,
                ComputeUtils.floatUp(toCheakUnit,groupNum) - hadCheakUnit,
                ComputeUtils.floatUp(toCheakRoom,groupNum) - hadCheakRoom,
                true));
    }

    private void addRowOut(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
        outDoorData.add(new ProgressMouse(name,
                ComputeUtils.floatUp(toCheakUnit,groupNum),
                ComputeUtils.floatUp(toCheakRoom,groupNum),
                hadCheakUnit,
                hadCheakRoom,
                ComputeUtils.floatUp(toCheakUnit,groupNum) - hadCheakUnit,
                ComputeUtils.floatUp(toCheakRoom,groupNum) - hadCheakRoom,
                true));
    }
}
