package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/19.
 */
//T_YingRecord
@Table(name = "T_YingRecord")
public class YingBean implements Parcelable {
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
    @Column(name = "YingRoom")
    private int YingRoom;
    @Column(name = "YingNum")
    private int YingNum;
    @Column(name = "FangYingPlace")
    private int FangYingPlace;
    @Column(name = "FangYingBadPlace")
    private int FangYingBadPlace;
    @Column(name = "Gate_FangYing")
    private int Gate_FangYing;
    @Column(name = "Window_FangYing")
    private int Window_FangYing;
    @Column(name = "Door_FangYing")
    private int Door_FangYing;
    @Column(name = "ShushiRoom")
    private int ShushiRoom;
    @Column(name = "LiangcaiRoom")
    private int LiangcaiRoom;
    @Column(name = "ChuGui_FangYing")
    private int ChuGui_FangYing;
    @Column(name = "tandian")
    private int tandian;
    @Column(name = "QiTa_FangYing")
    private int QiTa_FangYing;
    @Column(name = "InnerZhiShengDi")
    private int InnerZhiShengDi;
    @Column(name = "InnerYangXin")
    private int InnerYangXin;
    @Column(name = "FoodPlaceNum")
    private int FoodPlaceNum;
    @Column(name = "FoodPlaceFly")
    private int FoodPlaceFly;
    @Column(name = "LampNum")
    private int LampNum;
    @Column(name = "LampBadPlaceNum")
    private int LampBadPlaceNum;
    @Column(name = "LaJiRongQiNum")
    private int LaJiRongQiNum;
    @Column(name = "YangXinRongQi")
    private int YangXinRongQi;
    @Column(name = "ToiletNum")
    private int ToiletNum;
    @Column(name = "Toilet_Ying")
    private int Toilet_Ying;
    @Column(name = "LaJiStation")
    private int LaJiStation;
    @Column(name = "Station_Ying")
    private int Station_Ying;
    @Column(name = "CheckDistance")
    private int CheckDistance;
    @Column(name = "SanZaiLaJiNum")
    private int SanZaiLaJiNum;
    @Column(name = "SanZaiYangXinNum")
    private int SanZaiYangXinNum;

    public YingBean() {
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

    public int getYingRoom() {
        return YingRoom;
    }

    public void setYingRoom(int yingRoom) {
        YingRoom = yingRoom;
    }

    public int getYingNum() {
        return YingNum;
    }

    public void setYingNum(int yingNum) {
        YingNum = yingNum;
    }

    public int getFangYingPlace() {
        return FangYingPlace;
    }

    public void setFangYingPlace(int fangYingPlace) {
        FangYingPlace = fangYingPlace;
    }

    public int getFangYingBadPlace() {
        return FangYingBadPlace;
    }

    public void setFangYingBadPlace(int fangYingBadPlace) {
        FangYingBadPlace = fangYingBadPlace;
    }

    public int getGate_FangYing() {
        return Gate_FangYing;
    }

    public void setGate_FangYing(int gate_FangYing) {
        Gate_FangYing = gate_FangYing;
    }

    public int getWindow_FangYing() {
        return Window_FangYing;
    }

    public void setWindow_FangYing(int window_FangYing) {
        Window_FangYing = window_FangYing;
    }

    public int getDoor_FangYing() {
        return Door_FangYing;
    }

    public void setDoor_FangYing(int door_FangYing) {
        Door_FangYing = door_FangYing;
    }

    public int getShushiRoom() {
        return ShushiRoom;
    }

    public void setShushiRoom(int shushiRoom) {
        ShushiRoom = shushiRoom;
    }

    public int getLiangcaiRoom() {
        return LiangcaiRoom;
    }

    public void setLiangcaiRoom(int liangcaiRoom) {
        LiangcaiRoom = liangcaiRoom;
    }

    public int getChuGui_FangYing() {
        return ChuGui_FangYing;
    }

    public void setChuGui_FangYing(int chuGui_FangYing) {
        ChuGui_FangYing = chuGui_FangYing;
    }

    public int getTandian() {
        return tandian;
    }

    public void setTandian(int tandian) {
        this.tandian = tandian;
    }

    public int getQiTa_FangYing() {
        return QiTa_FangYing;
    }

    public void setQiTa_FangYing(int qiTa_FangYing) {
        QiTa_FangYing = qiTa_FangYing;
    }

    public int getInnerZhiShengDi() {
        return InnerZhiShengDi;
    }

    public void setInnerZhiShengDi(int innerZhiShengDi) {
        InnerZhiShengDi = innerZhiShengDi;
    }

    public int getInnerYangXin() {
        return InnerYangXin;
    }

    public void setInnerYangXin(int innerYangXin) {
        InnerYangXin = innerYangXin;
    }

    public int getFoodPlaceNum() {
        return FoodPlaceNum;
    }

    public void setFoodPlaceNum(int foodPlaceNum) {
        FoodPlaceNum = foodPlaceNum;
    }

    public int getFoodPlaceFly() {
        return FoodPlaceFly;
    }

    public void setFoodPlaceFly(int foodPlaceFly) {
        FoodPlaceFly = foodPlaceFly;
    }

    public int getLampNum() {
        return LampNum;
    }

    public void setLampNum(int lampNum) {
        LampNum = lampNum;
    }

    public int getLampBadPlaceNum() {
        return LampBadPlaceNum;
    }

    public void setLampBadPlaceNum(int lampBadPlaceNum) {
        LampBadPlaceNum = lampBadPlaceNum;
    }

    public int getLaJiRongQiNum() {
        return LaJiRongQiNum;
    }

    public void setLaJiRongQiNum(int laJiRongQiNum) {
        LaJiRongQiNum = laJiRongQiNum;
    }

    public int getYangXinRongQi() {
        return YangXinRongQi;
    }

    public void setYangXinRongQi(int yangXinRongQi) {
        YangXinRongQi = yangXinRongQi;
    }

    public int getToiletNum() {
        return ToiletNum;
    }

    public void setToiletNum(int toiletNum) {
        ToiletNum = toiletNum;
    }

    public int getToilet_Ying() {
        return Toilet_Ying;
    }

    public void setToilet_Ying(int toilet_Ying) {
        Toilet_Ying = toilet_Ying;
    }

    public int getLaJiStation() {
        return LaJiStation;
    }

    public void setLaJiStation(int laJiStation) {
        LaJiStation = laJiStation;
    }

    public int getStation_Ying() {
        return Station_Ying;
    }

    public void setStation_Ying(int station_Ying) {
        Station_Ying = station_Ying;
    }

    public int getCheckDistance() {
        return CheckDistance;
    }

    public void setCheckDistance(int checkDistance) {
        CheckDistance = checkDistance;
    }

    public int getSanZaiLaJiNum() {
        return SanZaiLaJiNum;
    }

    public void setSanZaiLaJiNum(int sanZaiLaJiNum) {
        SanZaiLaJiNum = sanZaiLaJiNum;
    }

    public int getSanZaiYangXinNum() {
        return SanZaiYangXinNum;
    }

    public void setSanZaiYangXinNum(int sanZaiYangXinNum) {
        SanZaiYangXinNum = sanZaiYangXinNum;
    }

    @Override
    public String toString() {
        return "YingBean{" +
                "UnitCode='" + UnitCode + '\'' +
                ", uniType='" + uniType + '\'' +
                ", TaskCode='" + TaskCode + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", UnitClassID='" + UnitClassID + '\'' +
                ", IsKeyUnit='" + IsKeyUnit + '\'' +
                ", ExpertCode='" + ExpertCode + '\'' +
                ", CheckRoom=" + CheckRoom +
                ", YingRoom=" + YingRoom +
                ", YingNum=" + YingNum +
                ", FangYingPlace=" + FangYingPlace +
                ", FangYingBadPlace=" + FangYingBadPlace +
                ", Gate_FangYing=" + Gate_FangYing +
                ", Window_FangYing=" + Window_FangYing +
                ", Door_FangYing=" + Door_FangYing +
                ", ShushiRoom=" + ShushiRoom +
                ", LiangcaiRoom=" + LiangcaiRoom +
                ", ChuGui_FangYing=" + ChuGui_FangYing +
                ", tandian=" + tandian +
                ", QiTa_FangYing=" + QiTa_FangYing +
                ", InnerZhiShengDi=" + InnerZhiShengDi +
                ", InnerYangXin=" + InnerYangXin +
                ", FoodPlaceNum=" + FoodPlaceNum +
                ", FoodPlaceFly=" + FoodPlaceFly +
                ", LampNum=" + LampNum +
                ", LampBadPlaceNum=" + LampBadPlaceNum +
                ", LaJiRongQiNum=" + LaJiRongQiNum +
                ", YangXinRongQi=" + YangXinRongQi +
                ", ToiletNum=" + ToiletNum +
                ", Toilet_Ying=" + Toilet_Ying +
                ", LaJiStation=" + LaJiStation +
                ", Station_Ying=" + Station_Ying +
                ", CheckDistance=" + CheckDistance +
                ", SanZaiLaJiNum=" + SanZaiLaJiNum +
                ", SanZaiYangXinNum=" + SanZaiYangXinNum +
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
        dest.writeInt(this.YingRoom);
        dest.writeInt(this.YingNum);
        dest.writeInt(this.FangYingPlace);
        dest.writeInt(this.FangYingBadPlace);
        dest.writeInt(this.Gate_FangYing);
        dest.writeInt(this.Window_FangYing);
        dest.writeInt(this.Door_FangYing);
        dest.writeInt(this.ShushiRoom);
        dest.writeInt(this.LiangcaiRoom);
        dest.writeInt(this.ChuGui_FangYing);
        dest.writeInt(this.tandian);
        dest.writeInt(this.QiTa_FangYing);
        dest.writeInt(this.InnerZhiShengDi);
        dest.writeInt(this.InnerYangXin);
        dest.writeInt(this.FoodPlaceNum);
        dest.writeInt(this.FoodPlaceFly);
        dest.writeInt(this.LampNum);
        dest.writeInt(this.LampBadPlaceNum);
        dest.writeInt(this.LaJiRongQiNum);
        dest.writeInt(this.YangXinRongQi);
        dest.writeInt(this.ToiletNum);
        dest.writeInt(this.Toilet_Ying);
        dest.writeInt(this.LaJiStation);
        dest.writeInt(this.Station_Ying);
        dest.writeInt(this.CheckDistance);
        dest.writeInt(this.SanZaiLaJiNum);
        dest.writeInt(this.SanZaiYangXinNum);
    }

    protected YingBean(Parcel in) {
        this.UnitCode = in.readString();
        this.uniType = in.readString();
        this.TaskCode = in.readString();
        this.AreaCode = in.readString();
        this.UnitClassID = in.readString();
        this.IsKeyUnit = in.readByte() != 0;
        this.ExpertCode = in.readString();
        this.CheckRoom = in.readInt();
        this.YingRoom = in.readInt();
        this.YingNum = in.readInt();
        this.FangYingPlace = in.readInt();
        this.FangYingBadPlace = in.readInt();
        this.Gate_FangYing = in.readInt();
        this.Window_FangYing = in.readInt();
        this.Door_FangYing = in.readInt();
        this.ShushiRoom = in.readInt();
        this.LiangcaiRoom = in.readInt();
        this.ChuGui_FangYing = in.readInt();
        this.tandian = in.readInt();
        this.QiTa_FangYing = in.readInt();
        this.InnerZhiShengDi = in.readInt();
        this.InnerYangXin = in.readInt();
        this.FoodPlaceNum = in.readInt();
        this.FoodPlaceFly = in.readInt();
        this.LampNum = in.readInt();
        this.LampBadPlaceNum = in.readInt();
        this.LaJiRongQiNum = in.readInt();
        this.YangXinRongQi = in.readInt();
        this.ToiletNum = in.readInt();
        this.Toilet_Ying = in.readInt();
        this.LaJiStation = in.readInt();
        this.Station_Ying = in.readInt();
        this.CheckDistance = in.readInt();
        this.SanZaiLaJiNum = in.readInt();
        this.SanZaiYangXinNum = in.readInt();
    }

    public static final Creator<YingBean> CREATOR = new Creator<YingBean>() {
        @Override
        public YingBean createFromParcel(Parcel source) {
            return new YingBean(source);
        }

        @Override
        public YingBean[] newArray(int size) {
            return new YingBean[size];
        }
    };
}
