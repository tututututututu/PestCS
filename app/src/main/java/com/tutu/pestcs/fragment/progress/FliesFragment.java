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
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.db.YingDao;
import com.tutu.pestcs.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 苍蝇
 */
public class FliesFragment extends BaseFragment {

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
        initDataWai();
        tl_table.setStretchAllColumns(true);
        tl_table_wai.setStretchAllColumns(true);

        for (int row = 0; row < inDoorData.size(); row++) {
            TableRow tableRow = new TableRow(mActivityContext);
            for (int col = 0; col < 4; col++) {
                TextView tv = createTV();
                if (col == 0) {
                    tv.setText(inDoorData.get(row).getUnitType());
                } else if (col == 1) {
                    tv.setText(inDoorData.get(row).getOriginalNumS() + "-" + inDoorData.get(row).getOriginalNumE());
                } else if (col == 2) {
                    tv.setText(inDoorData.get(row).getCheakedNumS() + "-" + inDoorData.get(row).getCheakedNumE());
                } else if (col == 3) {
                    tv.setText(inDoorData.get(row).getToCheakNumS() + "-" + inDoorData.get(row).getToCheakNumE());
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
                    tv.setText(outDoorData.get(row).getOriginalNumS() + "-" + outDoorData.get(row).getOriginalNumE());
                } else if (col == 2) {
                    tv.setText(outDoorData.get(row).getCheakedNumS() + "-" + outDoorData.get(row).getCheakedNumE());
                } else if (col == 3) {
                    tv.setText(outDoorData.get(row).getToCheakNumS() + "-" + outDoorData.get(row).getToCheakNumE());
                }
                tv.setBackground(getResources().getDrawable(R.drawable.tv_empty_rectangle));
                tableRow.addView(tv);
            }
            tl_table_wai.addView(tableRow, new ViewGroup.LayoutParams(WC, MP));
        }
    }


    @Override
    public int getLayoutID() {
        return R.layout.flies_fragment;
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
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("ying_in", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 8) {
            return;
        }


        int totelTocheakUnit = 0;
        int totelTocheakRoom = 0;
        int totleCheakedUnit = 0;
        int totleCheakedRoom = 0;

        int hadCCheakedUnitIn01 = YingDao.getHadCheakedUnitInCount("01");
        int hadCCheakedRoomIn01 = YingDao.getHadCheakedRoomInCount("01");
        totleCheakedUnit += hadCCheakedUnitIn01;
        totleCheakedRoom += hadCCheakedRoomIn01;
        addRow("餐饮店", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);

        int hadCCheakedUnitIn02 = YingDao.getHadCheakedUnitInCount("02");
        int hadCCheakedRoomIn02 = YingDao.getHadCheakedRoomInCount("02");
        totleCheakedUnit += hadCCheakedUnitIn02;
        totleCheakedRoom += hadCCheakedRoomIn02;
        addRow("商场,超市", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1).getValue
                ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn03 = YingDao.getHadCheakedUnitInCount("03") + YingDao.getHadCheakedUnitInCount("11") +
				YingDao.getHadCheakedUnitInCount("08") + YingDao.getHadCheakedUnitInCount("12");
        int hadCCheakedRoomIn03 = YingDao.getHadCheakedRoomInCount("03") + YingDao.getHadCheakedRoomInCount("11") +
				YingDao.getHadCheakedUnitInCount("08") + YingDao.getHadCheakedRoomInCount("12");
        totleCheakedUnit += hadCCheakedUnitIn03;
        totleCheakedRoom += hadCCheakedRoomIn03;
        addRow("机关,事业单位", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2)
				.getValue
                ()), hadCCheakedUnitIn03, hadCCheakedRoomIn03);

        int hadCCheakedUnitIn04 = YingDao.getHadCheakedUnitInCount("04");
        int hadCCheakedRoomIn04 = YingDao.getHadCheakedRoomInCount("04");
        totleCheakedUnit += hadCCheakedUnitIn04;
        totleCheakedRoom += hadCCheakedRoomIn04;
        addRow("宾馆,饭店", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3).getValue
                ()), hadCCheakedUnitIn04, hadCCheakedRoomIn04);

        int hadCCheakedUnitIn05 = YingDao.getHadCheakedUnitInCount("05");
        int hadCCheakedRoomIn05 = YingDao.getHadCheakedRoomInCount("05");
        totleCheakedUnit += hadCCheakedUnitIn05;
        totleCheakedRoom += hadCCheakedRoomIn05;
        addRow("农贸市场", Integer.parseInt(toCheakedBean.get(4).getKey()), Integer.parseInt(toCheakedBean.get(4).getValue
                ()), hadCCheakedUnitIn05, hadCCheakedRoomIn05);

        int hadCCheakedUnitIn10 = YingDao.getHadCheakedUnitInCount("06");
        int hadCCheakedRoomIn10 = YingDao.getHadCheakedRoomInCount("06");
        totleCheakedUnit += hadCCheakedUnitIn10;
        totleCheakedRoom += hadCCheakedRoomIn10;
        addRow("汽车或车站", Integer.parseInt(toCheakedBean.get(5).getKey()), Integer.parseInt(toCheakedBean.get(5).getValue
                ()), hadCCheakedUnitIn10, hadCCheakedRoomIn10);

        int hadCCheakedUnitIn07 = YingDao.getHadCheakedUnitInCount("07");
        int hadCCheakedRoomIn07 = YingDao.getHadCheakedRoomInCount("07");
        totleCheakedUnit += hadCCheakedUnitIn07;
        totleCheakedRoom += hadCCheakedRoomIn07;
        addRow("医院", Integer.parseInt(toCheakedBean.get(6).getKey()), Integer.parseInt(toCheakedBean.get(6).getValue
                ()), hadCCheakedUnitIn07, hadCCheakedRoomIn07);

        int hadCCheakedUnitIn08 = YingDao.getHadCheakedUnitInCount("09");
        int hadCCheakedRoomIn08 = YingDao.getHadCheakedRoomInCount("09");
        totleCheakedUnit += hadCCheakedUnitIn08;
        totleCheakedRoom += hadCCheakedRoomIn08;
        addRow("建筑拆迁工地", Integer.parseInt(toCheakedBean.get(7).getKey()), Integer.parseInt(toCheakedBean.get(7).getValue
                ()), hadCCheakedUnitIn08, hadCCheakedRoomIn08);


        for (KeyValueDataBean bean : toCheakedBean) {
            totelTocheakUnit += Integer.parseInt(bean.getKey());
            totelTocheakRoom += Integer.parseInt(bean.getValue());
        }
        addRow("合计", totelTocheakUnit, totelTocheakRoom, totleCheakedUnit, totleCheakedRoom);
    }


    private void initDataWai() {
        outDoorData.clear();

        TaskBean currentTask = TaskDao.queryCurrent();
        if (currentTask == null) {
            return;
        }
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("ying_out", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 4) {
            return;
        }

        int hadCCheakedUnitIn01 = YingDao.getHadCheakedUnitInCount("01")+YingDao.getHadCheakedUnitInCount("02")+YingDao.getHadCheakedUnitInCount("03")+YingDao.getHadCheakedUnitInCount("04")
                +YingDao.getHadCheakedUnitInCount("05")+YingDao.getHadCheakedUnitInCount("06")+YingDao.getHadCheakedUnitInCount("07")+YingDao.getHadCheakedUnitInCount("08")
                +YingDao.getHadCheakedUnitInCount("09")+YingDao.getHadCheakedUnitInCount("10")+YingDao.getHadCheakedUnitInCount("11")+YingDao.getHadCheakedUnitInCount("12")
                +YingDao.getHadCheakedUnitInCount("13")+YingDao.getHadCheakedUnitInCount("14")+YingDao.getHadCheakedUnitInCount("15")+YingDao.getHadCheakedUnitInCount("16")
                +YingDao.getHadCheakedUnitInCount("17")+YingDao.getHadCheakedUnitInCount("18");
        int hadCCheakedRoomIn01 = YingDao.getHadCheakedRoomInCount("01")+YingDao.getHadCheakedRoomInCount("02")+YingDao.getHadCheakedRoomInCount("03")+YingDao.getHadCheakedRoomInCount("04")
                +YingDao.getHadCheakedRoomInCount("05")+YingDao.getHadCheakedRoomInCount("06")+YingDao.getHadCheakedRoomInCount("07")+YingDao.getHadCheakedRoomInCount("08")
                +YingDao.getHadCheakedRoomInCount("09")+YingDao.getHadCheakedRoomInCount("10")+YingDao.getHadCheakedRoomInCount("11")+YingDao.getHadCheakedRoomInCount("12")
                +YingDao.getHadCheakedRoomInCount("13")+YingDao.getHadCheakedRoomInCount("14")+YingDao.getHadCheakedRoomInCount("15")+YingDao.getHadCheakedRoomInCount("16")
                +YingDao.getHadCheakedRoomInCount("17")+YingDao.getHadCheakedRoomInCount("18");
        addRowWai("室外垃圾容器", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);

        int hadCCheakedUnitIn02 = YingDao.getHadCheakedUnitInCount("15");
        int hadCCheakedRoomIn02 = YingDao.getHadCheakedRoomInCount("15");
        addRowWai("垃圾中转站", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1).getValue
                ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn03 = hadCCheakedUnitIn01;
        int hadCCheakedRoomIn03 = hadCCheakedRoomIn01;
        addRowWai("外环境散在孳生地", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2)
                .getValue
                        ()), hadCCheakedUnitIn03, hadCCheakedRoomIn03);

        int hadCCheakedUnitIn04 = YingDao.getHadCheakedUnitInCount("16");
        int hadCCheakedRoomIn04 = YingDao.getHadCheakedRoomInCount("16");
        addRowWai("公共厕所", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3).getValue
                ()), hadCCheakedUnitIn04, hadCCheakedRoomIn04);
    }


    private void addRow(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
        inDoorData.add(new ProgressMouse(name,
                toCheakUnit/groupNum,
                toCheakRoom/groupNum,
                hadCheakUnit,
                hadCheakRoom,
                toCheakUnit/groupNum - hadCheakUnit,
                toCheakRoom/groupNum - hadCheakRoom,
                true));
    }

    private void addRowWai(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
        outDoorData.add(new ProgressMouse(name,
                toCheakUnit/groupNum,
                toCheakRoom/groupNum,
                hadCheakUnit,
                hadCheakRoom,
                toCheakUnit/groupNum - hadCheakUnit,
                toCheakRoom/groupNum - hadCheakRoom,
                true));
    }
}
