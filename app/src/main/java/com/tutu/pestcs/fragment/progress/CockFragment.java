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
import com.tutu.pestcs.db.ZhangDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tutu on 16/4/7.
 * 蟑螂
 */
public class CockFragment extends BaseFragment {

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
        return R.layout.cock_fragment;
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
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("zhang", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 9) {
            return;
        }


        int totelTocheakUnit = 0;
        int totelTocheakRoom = 0;
        int totleCheakedUnit = 0;
        int totleCheakedRoom = 0;

        int hadCCheakedUnitIn01 = ZhangDao.getHadCheakedUnitInCount("01");
        int hadCCheakedRoomIn01 = ZhangDao.getHadCheakedRoomInCount("01");
        totleCheakedUnit += hadCCheakedUnitIn01;
        totleCheakedRoom += hadCCheakedRoomIn01;
        addRow("餐饮店", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
                ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);

        int hadCCheakedUnitIn02 = ZhangDao.getHadCheakedUnitInCount("02");
        int hadCCheakedRoomIn02 = ZhangDao.getHadCheakedRoomInCount("02");
        totleCheakedUnit += hadCCheakedUnitIn02;
        totleCheakedRoom += hadCCheakedRoomIn02;
        addRow("商场,超市", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1).getValue
                ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn03 = ZhangDao.getHadCheakedUnitInCount("03") + ZhangDao.getHadCheakedUnitInCount("09") +
				ZhangDao.getHadCheakedUnitInCount("11") + ZhangDao.getHadCheakedUnitInCount("12");
        int hadCCheakedRoomIn03 = ZhangDao.getHadCheakedRoomInCount("03") + ZhangDao.getHadCheakedRoomInCount("09") +
				ZhangDao.getHadCheakedUnitInCount("11") + ZhangDao.getHadCheakedRoomInCount("12");
        totleCheakedUnit += hadCCheakedUnitIn03;
        totleCheakedRoom += hadCCheakedRoomIn03;
        addRow("机关,事业单位", Integer.parseInt(toCheakedBean.get(2).getKey()), Integer.parseInt(toCheakedBean.get(2)
				.getValue
                ()), hadCCheakedUnitIn03, hadCCheakedRoomIn03);

        int hadCCheakedUnitIn04 = ZhangDao.getHadCheakedUnitInCount("04");
        int hadCCheakedRoomIn04 = ZhangDao.getHadCheakedRoomInCount("04");
        totleCheakedUnit += hadCCheakedUnitIn04;
        totleCheakedRoom += hadCCheakedRoomIn04;
        addRow("宾馆", Integer.parseInt(toCheakedBean.get(3).getKey()), Integer.parseInt(toCheakedBean.get(3).getValue
                ()), hadCCheakedUnitIn04, hadCCheakedRoomIn04);

        int hadCCheakedUnitIn05 = ZhangDao.getHadCheakedUnitInCount("05");
        int hadCCheakedRoomIn05 = ZhangDao.getHadCheakedRoomInCount("05");
        totleCheakedUnit += hadCCheakedUnitIn05;
        totleCheakedRoom += hadCCheakedRoomIn05;
        addRow("农贸市场", Integer.parseInt(toCheakedBean.get(4).getKey()), Integer.parseInt(toCheakedBean.get(4).getValue
                ()), hadCCheakedUnitIn05, hadCCheakedRoomIn05);

        int hadCCheakedUnitIn10 = ZhangDao.getHadCheakedUnitInCount("06");
        int hadCCheakedRoomIn10 = ZhangDao.getHadCheakedRoomInCount("06");
        totleCheakedUnit += hadCCheakedUnitIn10;
        totleCheakedRoom += hadCCheakedRoomIn10;
        addRow("机场或车站", Integer.parseInt(toCheakedBean.get(5).getKey()), Integer.parseInt(toCheakedBean.get(5).getValue
                ()), hadCCheakedUnitIn10, hadCCheakedRoomIn10);

        int hadCCheakedUnitIn07 = ZhangDao.getHadCheakedUnitInCount("07");
        int hadCCheakedRoomIn07 = ZhangDao.getHadCheakedRoomInCount("07");
        totleCheakedUnit += hadCCheakedUnitIn07;
        totleCheakedRoom += hadCCheakedRoomIn07;
        addRow("医院", Integer.parseInt(toCheakedBean.get(6).getKey()), Integer.parseInt(toCheakedBean.get(6).getValue
                ()), hadCCheakedUnitIn07, hadCCheakedRoomIn07);

        int hadCCheakedUnitIn06 = ZhangDao.getHadCheakedUnitInCount("08");
        int hadCCheakedRoomIn06 = ZhangDao.getHadCheakedRoomInCount("08");
        totleCheakedUnit += hadCCheakedUnitIn06;
        totleCheakedRoom += hadCCheakedRoomIn06;
        addRow("学校", Integer.parseInt(toCheakedBean.get(7).getKey()), Integer.parseInt(toCheakedBean.get(7).getValue
                ()), hadCCheakedUnitIn06, hadCCheakedRoomIn06);


        int hadCCheakedUnitIn09 = ZhangDao.getHadCheakedUnitInCount("10");
        int hadCCheakedRoomIn09 = ZhangDao.getHadCheakedRoomInCount("10");
        totleCheakedUnit += hadCCheakedUnitIn09;
        totleCheakedRoom += hadCCheakedRoomIn09;
        addRow("居(家)委会", Integer.parseInt(toCheakedBean.get(8).getKey()), Integer.parseInt(toCheakedBean.get(8).getValue
                ()), hadCCheakedUnitIn09, hadCCheakedRoomIn09);


        for (KeyValueDataBean bean : toCheakedBean) {
            totelTocheakUnit += Integer.parseInt(bean.getKey());
            totelTocheakRoom += Integer.parseInt(bean.getValue());
        }
        addRow("合计", totelTocheakUnit, totelTocheakRoom, totleCheakedUnit, totleCheakedRoom);
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
