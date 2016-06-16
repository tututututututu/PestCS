package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/19.
 */
//T_WenRecord
@Table(name = "T_WenRecord")
public class WenBean implements Parcelable {
    @Column(name = "UnitCode", isId = true)
    private String UnitCode;

    @Column(name = "uniType")
    private String uniType;
    @Column(name = "TaskCode")
    private String TaskCode;
    @Column(name = "AreaCode")
    private String AreaCode;
    @Column(name = "UnitClassID")
    private String UnitClassID;
    @Column(name = "IsKeyUnit")
    private boolean IsKeyUnit;
    @Column(name = "ExpertCode")
    private String ExpertCode;

    @Column(name = "CheckDistance")
    private int CheckDistance;
    @Column(name = "SmallWater")
    private int SmallWater;
    @Column(name = "YangXinWater")
    private int YangXinWater;
    @Column(name = "RongQi")
    private int RongQi;
    @Column(name = "RongQiYangXin")
    private int RongQiYangXin;
    @Column(name = "KengWa")
    private int KengWa;
    @Column(name = "KengWaYangXin")
    private int KengWaYangXin;
    @Column(name = "JingKou")
    private int JingKou;
    @Column(name = "JingKouYangXin")
    private int JingKouYangXin;
    @Column(name = "JingGuanChi")
    private int JingGuanChi;
    @Column(name = "JingGuanChiYangXin")
    private int JingGuanChiYangXin;
    @Column(name = "DiXiaShi")
    private int DiXiaShi;
    @Column(name = "DiXiaShiYangXin")
    private int DiXiaShiYangXin;
    @Column(name = "luntai")
    private int luntai;
    @Column(name = "luntaiYangXin")
    private int luntaiYangXin;
    @Column(name = "QiTa")
    private int QiTa;
    @Column(name = "QiTaYangXin")
    private int QiTaYangXin;
    @Column(name = "YouWenRenCi")
    private int YouWenRenCi;
    @Column(name = "WenStopNum")
    private int WenStopNum;
    @Column(name = "MieWenDengNum")
    private int MieWenDengNum;

    @Column(name = "CaiYangShaoNum")
    private int CaiYangShaoNum;
    @Column(name = "YangXinShaoNum")
    private int YangXinShaoNum;
    @Column(name = "WenYouNum")
    private int WenYouNum;


    /**
     * 水体类型
     * 1.湖泊
     * 2.河流
     * 3.人工湖
     * 4.景观池
     * 5.池塘
     * 6.沟渠
     * 7.其他
     */
    @Column(name = "ShuiTiType")
    private int ShuiTiType;


    public WenBean() {
    }

    public String getUniType() {
        return uniType;
    }

    public void setUniType(String uniType) {
        this.uniType = uniType;
    }

    public String getTaskCode() {
        return TaskCode;
    }

    public void setTaskCode(String taskCode) {
        TaskCode = taskCode;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getUnitClassID() {
        return UnitClassID;
    }

    public void setUnitClassID(String unitClassID) {
        UnitClassID = unitClassID;
    }

    public boolean isKeyUnit() {
        return IsKeyUnit;
    }

    public void setKeyUnit(boolean keyUnit) {
        IsKeyUnit = keyUnit;
    }

    public String getExpertCode() {
        return ExpertCode;
    }

    public void setExpertCode(String expertCode) {
        ExpertCode = expertCode;
    }

    public int getCaiYangShaoNum() {
        return CaiYangShaoNum;
    }

    public void setCaiYangShaoNum(int caiYangShaoNum) {
        CaiYangShaoNum = caiYangShaoNum;
    }

    public int getYangXinShaoNum() {
        return YangXinShaoNum;
    }

    public void setYangXinShaoNum(int yangXinShaoNum) {
        YangXinShaoNum = yangXinShaoNum;
    }

    public int getWenYouNum() {
        return WenYouNum;
    }

    public void setWenYouNum(int wenYouNum) {
        WenYouNum = wenYouNum;
    }

    public int getShuiTiType() {
        return ShuiTiType;
    }

    public void setShuiTiType(int shuiTiType) {
        ShuiTiType = shuiTiType;
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
                ", uniType='" + uniType + '\'' +
                ", TaskCode='" + TaskCode + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", UnitClassID='" + UnitClassID + '\'' +
                ", IsKeyUnit='" + IsKeyUnit + '\'' +
                ", ExpertCode='" + ExpertCode + '\'' +
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
                ", CaiYangShaoNum=" + CaiYangShaoNum +
                ", YangXinShaoNum=" + YangXinShaoNum +
                ", WenYouNum=" + WenYouNum +
                ", ShuiTiType=" + ShuiTiType +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UnitCode);
        dest.writeString(this.uniType);
        dest.writeString(this.TaskCode);
        dest.writeString(this.AreaCode);
        dest.writeString(this.UnitClassID);
        dest.writeByte(this.IsKeyUnit ? (byte) 1 : (byte) 0);
        dest.writeString(this.ExpertCode);
        dest.writeInt(this.CheckDistance);
        dest.writeInt(this.SmallWater);
        dest.writeInt(this.YangXinWater);
        dest.writeInt(this.RongQi);
        dest.writeInt(this.RongQiYangXin);
        dest.writeInt(this.KengWa);
        dest.writeInt(this.KengWaYangXin);
        dest.writeInt(this.JingKou);
        dest.writeInt(this.JingKouYangXin);
        dest.writeInt(this.JingGuanChi);
        dest.writeInt(this.JingGuanChiYangXin);
        dest.writeInt(this.DiXiaShi);
        dest.writeInt(this.DiXiaShiYangXin);
        dest.writeInt(this.luntai);
        dest.writeInt(this.luntaiYangXin);
        dest.writeInt(this.QiTa);
        dest.writeInt(this.QiTaYangXin);
        dest.writeInt(this.YouWenRenCi);
        dest.writeInt(this.WenStopNum);
        dest.writeInt(this.MieWenDengNum);
        dest.writeInt(this.CaiYangShaoNum);
        dest.writeInt(this.YangXinShaoNum);
        dest.writeInt(this.WenYouNum);
        dest.writeInt(this.ShuiTiType);
    }

    protected WenBean(Parcel in) {
        this.UnitCode = in.readString();
        this.uniType = in.readString();
        this.TaskCode = in.readString();
        this.AreaCode = in.readString();
        this.UnitClassID = in.readString();
        this.IsKeyUnit = in.readByte() != 0;
        this.ExpertCode = in.readString();
        this.CheckDistance = in.readInt();
        this.SmallWater = in.readInt();
        this.YangXinWater = in.readInt();
        this.RongQi = in.readInt();
        this.RongQiYangXin = in.readInt();
        this.KengWa = in.readInt();
        this.KengWaYangXin = in.readInt();
        this.JingKou = in.readInt();
        this.JingKouYangXin = in.readInt();
        this.JingGuanChi = in.readInt();
        this.JingGuanChiYangXin = in.readInt();
        this.DiXiaShi = in.readInt();
        this.DiXiaShiYangXin = in.readInt();
        this.luntai = in.readInt();
        this.luntaiYangXin = in.readInt();
        this.QiTa = in.readInt();
        this.QiTaYangXin = in.readInt();
        this.YouWenRenCi = in.readInt();
        this.WenStopNum = in.readInt();
        this.MieWenDengNum = in.readInt();
        this.CaiYangShaoNum = in.readInt();
        this.YangXinShaoNum = in.readInt();
        this.WenYouNum = in.readInt();
        this.ShuiTiType = in.readInt();
    }

    public static final Creator<WenBean> CREATOR = new Creator<WenBean>() {
        @Override
        public WenBean createFromParcel(Parcel source) {
            return new WenBean(source);
        }

        @Override
        public WenBean[] newArray(int size) {
            return new WenBean[size];
        }
    };
}
