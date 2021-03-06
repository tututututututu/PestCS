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
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.KeyValueDataBean;
import com.tutu.pestcs.bean.ProgressMouse;
import com.tutu.pestcs.bean.TaskBean;
import com.tutu.pestcs.db.GuoBiaoUnitDao;
import com.tutu.pestcs.db.TaskDao;
import com.tutu.pestcs.db.WenDao;
import com.tutu.pestcs.event.ProgressChangeEvent;
import com.tutu.pestcs.utils.ComputeUtils;
import com.tutu.pestcs.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/7.
 * 蚊子
 */
public class MosquitosFragment extends BaseFragment {

    @Bind(R.id.tl_table)
    TableLayout tl_table;
    @Bind(R.id.tl_table_wai)
    TableLayout tl_table_wai;

    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;


    private List<ProgressMouse> inDoorData = new ArrayList<>();
    private List<ProgressMouse> outDoorData = new ArrayList<>();

    private int groupNum = 1;

    @Override
    public int getLayoutID() {
        return R.layout.mosquitos_fragment;
    }


    @Override
    public void handleMessage(Message msg) {

    }


    @Override
    public void initView() {
        getCurrentTask();
        registReloadDataEvent();
    }

    private void registReloadDataEvent() {
        RxBus.obtainEvent(ProgressChangeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ProgressChangeEvent>() {
                    @Override
                    public void call(ProgressChangeEvent event) {

                        getCurrentTask();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
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
                if (groupNum == 0) {
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
        tl_table.removeAllViews();
        tl_table_wai.removeAllViews();
        initDataIn();
        initDataWai();
        tl_table.setStretchAllColumns(true);
        tl_table_wai.setStretchAllColumns(true);

        tl_table.addView(TableRowFactory.create(getActivity()),new ViewGroup.LayoutParams(WC, MP));
        tl_table_wai.addView(TableRowFactory.create(getActivity()),new ViewGroup.LayoutParams(WC, MP));

        for (int row = 0; row < inDoorData.size(); row++) {
            TableRow tableRow = new TableRow(mActivityContext);
            for (int col = 0; col < 4; col++) {
                TextView tv = createTV();
                if (col == 0) {
                    tv.setText(inDoorData.get(row).getUnitType());
                } else if (col == 1) {
                    if (inDoorData.get(row).getOriginalNumS()==0){
                        tv.setText("/" + "-" + inDoorData.get(row).getOriginalNumE());

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
                    if (outDoorData.get(row).getOriginalNumE() == 0) {
                        tv.setText(outDoorData.get(row).getOriginalNumS() + "-" + "/");
                    } else {
                        tv.setText(outDoorData.get(row).getOriginalNumS() + "-" + outDoorData.get(row)
                                .getOriginalNumE());
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

        int hadCCheakedUnitIn02 = WenDao.getHadCheakedUnitInCount("09");
        int hadCCheakedRoomIn02 = WenDao.getHadCheakedRoomInCount("09");
        addRow("居委会", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0).getValue
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
                        ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);


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
    }


    private void initDataWai() {
        outDoorData.clear();

        TaskBean currentTask = TaskDao.queryCurrent();
        if (currentTask == null) {
            return;
        }
        List<KeyValueDataBean> toCheakedBean = GuoBiaoUnitDao.getToCheak("wen02", currentTask
                .getPopulation());
        if (toCheakedBean == null || toCheakedBean.size() < 2) {
            return;
        }


        int hadCCheakedUnitIn02 = WenDao.getHadCheakedUnitInCount("17");
        int hadCCheakedRoomIn02 = WenDao.getHadCheakedRoomInCount("17");

        addRowWai("大中型水体(个)", Integer.parseInt(toCheakedBean.get(0).getKey()), Integer.parseInt(toCheakedBean.get(0)
                .getValue
                        ()), hadCCheakedUnitIn02, hadCCheakedRoomIn02);

        int hadCCheakedUnitIn01 = WenDao.getHadCheakedUnitInCountWai("01") + WenDao.getHadCheakedUnitInCountWai
                ("02") + WenDao.getHadCheakedUnitInCountWai("03") + WenDao.getHadCheakedUnitInCountWai("04")
                + WenDao.getHadCheakedUnitInCountWai("05") + WenDao.getHadCheakedUnitInCountWai("06") + WenDao
                .getHadCheakedUnitInCountWai("07") + WenDao.getHadCheakedUnitInCountWai("08")
                + WenDao.getHadCheakedUnitInCountWai("09") + WenDao.getHadCheakedUnitInCountWai("10") + WenDao
                .getHadCheakedUnitInCountWai("11") + WenDao.getHadCheakedUnitInCountWai("12")
                + WenDao.getHadCheakedUnitInCountWai("13") + WenDao.getHadCheakedUnitInCountWai("14") + WenDao
                .getHadCheakedUnitInCountWai("15") + WenDao.getHadCheakedUnitInCountWai("16")
                + WenDao.getHadCheakedUnitInCountWai("17") + WenDao.getHadCheakedUnitInCountWai("18");
//        int hadCCheakedRoomIn01 = WenDao.getHadCheakedRoomInCountWai("01") + WenDao.getHadCheakedRoomInCountWai
//                ("02") + WenDao.getHadCheakedRoomInCountWai("03") + WenDao.getHadCheakedRoomInCountWai("04")
//                + WenDao.getHadCheakedRoomInCountWai("05") + WenDao.getHadCheakedRoomInCountWai("06") + WenDao
//                .getHadCheakedRoomInCountWai("07") + WenDao.getHadCheakedRoomInCountWai("08")
//                + WenDao.getHadCheakedRoomInCountWai("09") + WenDao.getHadCheakedRoomInCountWai("10") + WenDao
//                .getHadCheakedRoomInCountWai("11") + WenDao.getHadCheakedRoomInCountWai("12")
//                + WenDao.getHadCheakedRoomInCountWai("13") + WenDao.getHadCheakedRoomInCountWai("14") + WenDao
//                .getHadCheakedRoomInCountWai("15") + WenDao.getHadCheakedRoomInCountWai("16")
//                + WenDao.getHadCheakedRoomInCountWai("17") + WenDao.getHadCheakedRoomInCountWai("18");
        int hadCCheakedRoomIn01 = 0;
        addRowWai("特殊场所诱蚊(人次)", Integer.parseInt(toCheakedBean.get(1).getKey()), Integer.parseInt(toCheakedBean.get(1)
                .getValue
                        ()), hadCCheakedUnitIn01, hadCCheakedRoomIn01);
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

    private void addRowWai(String name, int toCheakUnit, int toCheakRoom, int hadCheakUnit, int hadCheakRoom) {
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
