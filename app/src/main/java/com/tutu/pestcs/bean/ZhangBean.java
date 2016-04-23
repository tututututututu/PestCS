package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/19.
 */
//T_ZhangRecord
public class ZhangBean {

	private String UnitCode;
	private int CheckRoom;
	private int ChengCongRoom;
	private int DaLianNum;
	private int XiaoLianNuml;
	private int LuanQiaoRoom;
	private int LuanQiaoNum;
	private int ZhangJiRoom;
	private int ChongShi;
	private int CanPian;
	private int KongKe;
	private int FenBian;
	private int TuiPi;

	public ZhangBean() {
	}

	public String getUnitCode() {
		return UnitCode;
	}

	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
	}

	public int getCheckRoom() {
		return CheckRoom;
	}

	public void setCheckRoom(int checkRoom) {
		CheckRoom = checkRoom;
	}

	public int getChengCongRoom() {
		return ChengCongRoom;
	}

	public void setChengCongRoom(int chengCongRoom) {
		ChengCongRoom = chengCongRoom;
	}

	public int getDaLianNum() {
		return DaLianNum;
	}

	public void setDaLianNum(int daLianNum) {
		DaLianNum = daLianNum;
	}

	public int getXiaoLianNuml() {
		return XiaoLianNuml;
	}

	public void setXiaoLianNuml(int xiaoLianNuml) {
		XiaoLianNuml = xiaoLianNuml;
	}

	public int getLuanQiaoRoom() {
		return LuanQiaoRoom;
	}

	public void setLuanQiaoRoom(int luanQiaoRoom) {
		LuanQiaoRoom = luanQiaoRoom;
	}

	public int getLuanQiaoNum() {
		return LuanQiaoNum;
	}

	public void setLuanQiaoNum(int luanQiaoNum) {
		LuanQiaoNum = luanQiaoNum;
	}

	public int getZhangJiRoom() {
		return ZhangJiRoom;
	}

	public void setZhangJiRoom(int zhangJiRoom) {
		ZhangJiRoom = zhangJiRoom;
	}

	public int getChongShi() {
		return ChongShi;
	}

	public void setChongShi(int chongShi) {
		ChongShi = chongShi;
	}

	public int getCanPian() {
		return CanPian;
	}

	public void setCanPian(int canPian) {
		CanPian = canPian;
	}

	public int getKongKe() {
		return KongKe;
	}

	public void setKongKe(int kongKe) {
		KongKe = kongKe;
	}

	public int getFenBian() {
		return FenBian;
	}

	public void setFenBian(int fenBian) {
		FenBian = fenBian;
	}

	public int getTuiPi() {
		return TuiPi;
	}

	public void setTuiPi(int tuiPi) {
		TuiPi = tuiPi;
	}

	@Override
	public String toString() {
		return "ZhangBean{" +
			"UnitCode='" + UnitCode + '\'' +
			", CheckRoom=" + CheckRoom +
			", ChengCongRoom=" + ChengCongRoom +
			", DaLianNum=" + DaLianNum +
			", XiaoLianNuml=" + XiaoLianNuml +
			", LuanQiaoRoom=" + LuanQiaoRoom +
			", LuanQiaoNum=" + LuanQiaoNum +
			", ZhangJiRoom=" + ZhangJiRoom +
			", ChongShi=" + ChongShi +
			", CanPian=" + CanPian +
			", KongKe=" + KongKe +
			", FenBian=" + FenBian +
			", TuiPi=" + TuiPi +
			'}';
	}
}
