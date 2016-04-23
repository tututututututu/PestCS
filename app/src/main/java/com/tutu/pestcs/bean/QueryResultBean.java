package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/19.
 */
public class QueryResultBean {
	private String UnitName;
	private int col1Start;
	private int col1End;
	private int col2Start;
	private int col2End;
	private int col3Start;
	private int col3End;

	public QueryResultBean() {
	}

	public QueryResultBean(String unitName, int col1Start, int col1End, int col2Start, int col2End, int col3Start, int
		col3End) {
		UnitName = unitName;
		this.col1Start = col1Start;
		this.col1End = col1End;
		this.col2Start = col2Start;
		this.col2End = col2End;
		this.col3Start = col3Start;
		this.col3End = col3End;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public int getCol1Start() {
		return col1Start;
	}

	public void setCol1Start(int col1Start) {
		this.col1Start = col1Start;
	}

	public int getCol1End() {
		return col1End;
	}

	public void setCol1End(int col1End) {
		this.col1End = col1End;
	}

	public int getCol2Start() {
		return col2Start;
	}

	public void setCol2Start(int col2Start) {
		this.col2Start = col2Start;
	}

	public int getCol2End() {
		return col2End;
	}

	public void setCol2End(int col2End) {
		this.col2End = col2End;
	}

	public int getCol3Start() {
		return col3Start;
	}

	public void setCol3Start(int col3Start) {
		this.col3Start = col3Start;
	}

	public int getCol3End() {
		return col3End;
	}

	public void setCol3End(int col3End) {
		this.col3End = col3End;
	}

	@Override
	public String toString() {
		return "QueryResultBean{" +
			"UnitName='" + UnitName + '\'' +
			", col1Start=" + col1Start +
			", col1End=" + col1End +
			", col2Start=" + col2Start +
			", col2End=" + col2End +
			", col3Start=" + col3Start +
			", col3End=" + col3End +
			'}';
	}
}
