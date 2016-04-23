package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/19.
 */
//T_WenRecord
public class WenBean {
	private String UnitCode;
	private int CheckDistance;
	private int SmallWater;
	private int YangXinWater;
	private int RongQi;
	private int RongQiYangXin;
	private int KengWa;
	private int KengWaYangXin;
	private int JingKou;
	private int JingKouYangXin;
	private int JingGuanChi;
	private int JingGuanChiYangXin;
	private int DiXiaShi;
	private int DiXiaShiYangXin;
	private int luntai;
	private int luntaiYangXin;
	private int QiTa;
	private int QiTaYangXin;
	private int YouWenRenCi;
	private int WenStopNum;
	private int MieWenDengNum;

	public WenBean() {
	}

	public String getUnitCode() {
		return UnitCode;
	}

	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
	}

	public int getCheckDistance() {
		return CheckDistance;
	}

	public void setCheckDistance(int checkDistance) {
		CheckDistance = checkDistance;
	}

	public int getSmallWater() {
		return SmallWater;
	}

	public void setSmallWater(int smallWater) {
		SmallWater = smallWater;
	}

	public int getYangXinWater() {
		return YangXinWater;
	}

	public void setYangXinWater(int yangXinWater) {
		YangXinWater = yangXinWater;
	}

	public int getRongQi() {
		return RongQi;
	}

	public void setRongQi(int rongQi) {
		RongQi = rongQi;
	}

	public int getRongQiYangXin() {
		return RongQiYangXin;
	}

	public void setRongQiYangXin(int rongQiYangXin) {
		RongQiYangXin = rongQiYangXin;
	}

	public int getKengWa() {
		return KengWa;
	}

	public void setKengWa(int kengWa) {
		KengWa = kengWa;
	}

	public int getKengWaYangXin() {
		return KengWaYangXin;
	}

	public void setKengWaYangXin(int kengWaYangXin) {
		KengWaYangXin = kengWaYangXin;
	}

	public int getJingKou() {
		return JingKou;
	}

	public void setJingKou(int jingKou) {
		JingKou = jingKou;
	}

	public int getJingKouYangXin() {
		return JingKouYangXin;
	}

	public void setJingKouYangXin(int jingKouYangXin) {
		JingKouYangXin = jingKouYangXin;
	}

	public int getJingGuanChi() {
		return JingGuanChi;
	}

	public void setJingGuanChi(int jingGuanChi) {
		JingGuanChi = jingGuanChi;
	}

	public int getJingGuanChiYangXin() {
		return JingGuanChiYangXin;
	}

	public void setJingGuanChiYangXin(int jingGuanChiYangXin) {
		JingGuanChiYangXin = jingGuanChiYangXin;
	}

	public int getDiXiaShi() {
		return DiXiaShi;
	}

	public void setDiXiaShi(int diXiaShi) {
		DiXiaShi = diXiaShi;
	}

	public int getDiXiaShiYangXin() {
		return DiXiaShiYangXin;
	}

	public void setDiXiaShiYangXin(int diXiaShiYangXin) {
		DiXiaShiYangXin = diXiaShiYangXin;
	}

	public int getLuntai() {
		return luntai;
	}

	public void setLuntai(int luntai) {
		this.luntai = luntai;
	}

	public int getLuntaiYangXin() {
		return luntaiYangXin;
	}

	public void setLuntaiYangXin(int luntaiYangXin) {
		this.luntaiYangXin = luntaiYangXin;
	}

	public int getQiTa() {
		return QiTa;
	}

	public void setQiTa(int qiTa) {
		QiTa = qiTa;
	}

	public int getQiTaYangXin() {
		return QiTaYangXin;
	}

	public void setQiTaYangXin(int qiTaYangXin) {
		QiTaYangXin = qiTaYangXin;
	}

	public int getYouWenRenCi() {
		return YouWenRenCi;
	}

	public void setYouWenRenCi(int youWenRenCi) {
		YouWenRenCi = youWenRenCi;
	}

	public int getWenStopNum() {
		return WenStopNum;
	}

	public void setWenStopNum(int wenStopNum) {
		WenStopNum = wenStopNum;
	}

	public int getMieWenDengNum() {
		return MieWenDengNum;
	}

	public void setMieWenDengNum(int mieWenDengNum) {
		MieWenDengNum = mieWenDengNum;
	}

	@Override
	public String toString() {
		return "WenBean{" +
			"UnitCode='" + UnitCode + '\'' +
			", CheckDistance=" + CheckDistance +
			", SmallWater=" + SmallWater +
			", YangXinWater=" + YangXinWater +
			", RongQi=" + RongQi +
			", RongQiYangXin=" + RongQiYangXin +
			", KengWa=" + KengWa +
			", KengWaYangXin=" + KengWaYangXin +
			", JingKou=" + JingKou +
			", JingKouYangXin=" + JingKouYangXin +
			", JingGuanChi=" + JingGuanChi +
			", JingGuanChiYangXin=" + JingGuanChiYangXin +
			", DiXiaShi=" + DiXiaShi +
			", DiXiaShiYangXin=" + DiXiaShiYangXin +
			", luntai=" + luntai +
			", luntaiYangXin=" + luntaiYangXin +
			", QiTa=" + QiTa +
			", QiTaYangXin=" + QiTaYangXin +
			", YouWenRenCi=" + YouWenRenCi +
			", WenStopNum=" + WenStopNum +
			", MieWenDengNum=" + MieWenDengNum +
			'}';
	}
}
