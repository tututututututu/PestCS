package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/8.
 */
public class IndexGVBean {
	private String name;
	private int resID;

	public IndexGVBean() {
	}

	public IndexGVBean(String name, int resID) {
		this.name = name;
		this.resID = resID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResID() {
		return resID;
	}

	public void setResID(int resID) {
		this.resID = resID;
	}

	@Override
	public String toString() {
		return "IndexGVBean{" +
			"name='" + name + '\'' +
			", resID=" + resID +
			'}';
	}
}
