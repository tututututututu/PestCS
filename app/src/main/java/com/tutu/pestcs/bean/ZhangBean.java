package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/19.
 */
//T_ZhangRecord
@Table(name = "T_ZhangRecord")
public class ZhangBean implements Parcelable {
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


    @Column(name = "CheckRoom")
    private int CheckRoom;
    @Column(name = "ChengCongRoom")
    private int ChengCongRoom;
    @Column(name = "DaLianNum")
    private int DaLianNum;
    @Column(name = "XiaoLianNuml")
    private int XiaoLianNuml;
    @Column(name = "LuanQiaoRoom")
    private int LuanQiaoRoom;
    @Column(name = "LuanQiaoNum")
    private int LuanQiaoNum;
    @Column(name = "ZhangJiRoom")
    private int ZhangJiRoom;
    @Column(name = "ChongShi")
    private int ChongShi;
    @Column(name = "CanPian")
    private int CanPian;
    @Column(name = "KongKe")
    private int KongKe;
    @Column(name = "FenBian")
    private int FenBian;
    @Column(name = "TuiPi")
    private int TuiPi;

    public ZhangBean() {
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
                ", uniType='" + uniType + '\'' +
                ", TaskCode='" + TaskCode + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", UnitClassID='" + UnitClassID + '\'' +
                ", IsKeyUnit='" + IsKeyUnit + '\'' +
                ", ExpertCode='" + ExpertCode + '\'' +
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
        dest.writeInt(this.CheckRoom);
        dest.writeInt(this.ChengCongRoom);
        dest.writeInt(this.DaLianNum);
        dest.writeInt(this.XiaoLianNuml);
        dest.writeInt(this.LuanQiaoRoom);
        dest.writeInt(this.LuanQiaoNum);
        dest.writeInt(this.ZhangJiRoom);
        dest.writeInt(this.ChongShi);
        dest.writeInt(this.CanPian);
        dest.writeInt(this.KongKe);
        dest.writeInt(this.FenBian);
        dest.writeInt(this.TuiPi);
    }

    protected ZhangBean(Parcel in) {
        this.UnitCode = in.readString();
        this.uniType = in.readString();
        this.TaskCode = in.readString();
        this.AreaCode = in.readString();
        this.UnitClassID = in.readString();
        this.IsKeyUnit = in.readByte() != 0;
        this.ExpertCode = in.readString();
        this.CheckRoom = in.readInt();
        this.ChengCongRoom = in.readInt();
        this.DaLianNum = in.readInt();
        this.XiaoLianNuml = in.readInt();
        this.LuanQiaoRoom = in.readInt();
        this.LuanQiaoNum = in.readInt();
        this.ZhangJiRoom = in.readInt();
        this.ChongShi = in.readInt();
        this.CanPian = in.readInt();
        this.KongKe = in.readInt();
        this.FenBian = in.readInt();
        this.TuiPi = in.readInt();
    }

    public static final Creator<ZhangBean> CREATOR = new Creator<ZhangBean>() {
        @Override
        public ZhangBean createFromParcel(Parcel source) {
            return new ZhangBean(source);
        }

        @Override
        public ZhangBean[] newArray(int size) {
            return new ZhangBean[size];
        }
    };
}
