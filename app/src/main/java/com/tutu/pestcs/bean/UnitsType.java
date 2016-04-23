package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/12.
 */
public class UnitsType {
	int unitID;
	String name;
	/**
	 * 0一般类型,1重点类型,2不可为重点类型
	 */
	String type;
	/**
	 * 纯室外类型
	 */
	boolean outDoor;
	/**
	 * 纯室内类型
	 */
	boolean inDoor;

	boolean isZhongdian;

	public UnitsType(int unitID, String name, String type, boolean outDoor, boolean inDoor, boolean isZhongdian) {
		this.unitID = unitID;
		this.name = name;
		this.type = type;
		this.outDoor = outDoor;
		this.inDoor = inDoor;
		this.isZhongdian = isZhongdian;
	}

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOutDoor() {
		return outDoor;
	}

	public void setOutDoor(boolean outDoor) {
		this.outDoor = outDoor;
	}

	public boolean isInDoor() {
		return inDoor;
	}

	public void setInDoor(boolean inDoor) {
		this.inDoor = inDoor;
	}

	public boolean isZhongdian() {
		return isZhongdian;
	}

	public void setZhongdian(boolean zhongdian) {
		isZhongdian = zhongdian;
	}

	@Override
	public String toString() {
		return "UnitsType{" +
			"unitID=" + unitID +
			", name='" + name + '\'' +
			", type='" + type + '\'' +
			", outDoor=" + outDoor +
			", isZhongdian" + isZhongdian +
			", inDoor=" + inDoor +
			'}';
	}
}
