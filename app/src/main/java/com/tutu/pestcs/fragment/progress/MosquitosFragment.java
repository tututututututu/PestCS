package com.tutu.pestcs.fragment.progress;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.ProgressMouse;

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

		inDoorData.add(new ProgressMouse("餐饮店", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("商场,超市", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("机关,事业单位", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("宾馆,饭店", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("农贸市场", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("学校", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("医院", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("建筑拆迁工地", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("居(家)委会", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("汽车或车站", 35, 50, 25, 350, 10, 30, true));
		inDoorData.add(new ProgressMouse("合计", 350, 3500, 25, 350, 10, 30, true));


		outDoorData.add(new ProgressMouse("公共绿地,公园或道路两侧", 35, 50, 25, 350, 10, 30, false));
		outDoorData.add(new ProgressMouse("垃圾中转站或公共厕所", 35, 50, 25, 350, 10, 30, false));
		outDoorData.add(new ProgressMouse("单位或居民区院内", 35, 50, 25, 350, 10, 30, false));
		outDoorData.add(new ProgressMouse("农贸市场,工地或车站", 35, 50, 25, 350, 10, 30, false));
		outDoorData.add(new ProgressMouse("合计", 350, 3500, 25, 350, 10, 30, false));
	}
}
