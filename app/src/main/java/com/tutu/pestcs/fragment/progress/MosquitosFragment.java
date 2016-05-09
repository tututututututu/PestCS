package com.tutu.pestcs.fragment.progress;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.KeyValueDataBean;
import com.tutu.pestcs.bean.ProgressMouse;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.GuoBiaoUnitDao;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.db.WenDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 蚊子
 */
public class MosquitosFragment extends BaseFragment {

    @Bind(R.id.tl_table)
    TableLayout tl_table;


    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;


    private List<ProgressMouse> inDoorData = new ArrayList<>();
    private List<ProgressMouse> outDoorData = new ArrayList<>();


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        createTV();
        initData();
        tl_table.setStretchAllColumns(true);

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

		/*
        TableRow tableRow = new TableRow(mActivityContext);
		TextView tv = new TextView(mActivityContext);
		tv.setText("的打击打击");
		tableRow.addView(tv);
		tl_table.addView(tableRow,new ViewGroup.LayoutParams(WC,MP));
		*/
    }

    @Override
    public int getLayoutID() {
        return R.layout.mosquitos_fragment;
    }

    private TextView createTV() {
        return (TextView) LayoutInflater.from(mActivityContext).inflate(R.layout.table_tv, null);
    }

    private void initData() {
        inDoorData.clear();
        outDoorData.clear();

        TaskBean currentTask = TaskDao.queryCurrent();
        if (currentTask == null) {
            return;
        }
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("wen01", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 4) {
            return;
        }


        int totelTocheakUnit = 0;
        int totelTocheakRoom = 0;
        int totleCheakedUnit = 0;
        int totleCheakedRoom = 0;


        int hadCCheakedUnitIn09 = WenDao.getHadCheakedUnitInCount("10");
        int hadCCheakedRoomIn09 = WenDao.getHadCheakedRoomInCount("10");
        totleCheakedUnit += hadCCheakedUnitIn09;
        totleCheakedRoom += hadCCheakedRoomIn09;
        addRow("居(家)委会", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn09, hadCCheakedRoomIn09);

        int hadCCheakedUnitIn01 = WenDao.getHadCheakedUnitInCount("01") + WenDao.getHadCheakedUnitInCount("02") +
                WenDao.getHadCheakedUnitInCount("03") + WenDao.getHadCheakedUnitInCount("04") + WenDao
                .getHadCheakedUnitInCount("05") +
                WenDao.getHadCheakedUnitInCount("06") + WenDao.getHadCheakedUnitInCount("07") + WenDao
                .getHadCheakedUnitInCount("08") + WenDao.getHadCheakedUnitInCount("10") +
                WenDao.getHadCheakedUnitInCount("11") + WenDao.getHadCheakedUnitInCount("12") + WenDao
                .getHadCheakedUnitInCount("15") + WenDao.getHadCheakedUnitInCount("18");

        int hadCCheakedRoomIn01 = WenDao.getHadCheakedRoomInCount("01") + WenDao.getHadCheakedRoomInCount("02") +
                WenDao.getHadCheakedRoomInCount("03") + WenDao.getHadCheakedRoomInCount("04") +
                WenDao.getHadCheakedRoomInCount("05") + WenDao.getHadCheakedRoomInCount("06") + WenDao
                .getHadCheakedRoomInCount("07") + WenDao.getHadCheakedRoomInCount("08") +
                WenDao.getHadCheakedRoomInCount("10") + WenDao.getHadCheakedRoomInCount("11") + WenDao
                .getHadCheakedRoomInCount("12") + WenDao.getHadCheakedRoomInCount("15") + WenDao
                .getHadCheakedRoomInCount("18");

        totleCheakedUnit += hadCCheakedUnitIn01;
        totleCheakedRoom += hadCCheakedRoomIn01;
        addRow("单位(有独立院落)", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1)
                .getValue
                ()), hadCCheakedUnitIn09, hadCCheakedRoomIn09);

        int hadCCheakedUnitIn02 = WenDao.getHadCheakedUnitInCount("09");
        int hadCCheakedRoomIn02 = WenDao.getHadCheakedRoomInCount("09");
        totleCheakedUnit += hadCCheakedUnitIn02;
        totleCheakedRoom += hadCCheakedRoomIn02;
        addRow("建筑拆迁工地", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2).getValue
                ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn13 = WenDao.getHadCheakedUnitInCount("13");
        int hadCCheakedRoomIn13 = WenDao.getHadCheakedRoomInCount("13");
        totleCheakedUnit += hadCCheakedUnitIn13;
        totleCheakedRoom += hadCCheakedRoomIn13;
        addRow("道路(雨水井口)", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3)
                .getValue
                ()), hadCCheakedUnitIn13, hadCCheakedRoomIn13);


        for (KeyValueDataBean bean : toCheakedBean) {
            totelTocheakUnit += Integer.parseInt(bean.getKey());
            totelTocheakRoom += Integer.parseInt(bean.getValue());
        }
        addRow("合计", totelTocheakUnit, totelTocheakRoom, totleCheakedUnit, totleCheakedRoom);


        outDoorData.add(new ProgressMouse("公共绿地,公园或道路两侧", 35, 50, 25, 350, 10, 30, false));
        outDoorData.add(new ProgressMouse("垃圾中转站或公共厕所", 35, 50, 25, 350, 10, 30, false));
        outDoorData.add(new ProgressMouse("单位或居民区院内", 35, 50, 25, 350, 10, 30, false));
        outDoorData.add(new ProgressMouse("农贸市场,工地或车站", 35, 50, 25, 350, 10, 30, false));
        outDoorData.add(new ProgressMouse("合计", 350, 3500, 25, 350, 10, 30, false));
    }

    private void addRow(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
        inDoorData.add(new ProgressMouse(name,
                toCheakUnit,
                toCheakRoom,
                hadCheakUnit,
                hadCheakRoom,
                toCheakUnit - hadCheakUnit,
                toCheakRoom - hadCheakRoom,
                true));
    }
}
