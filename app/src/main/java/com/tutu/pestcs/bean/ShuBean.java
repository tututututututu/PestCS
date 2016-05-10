package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/19.
 */
@Table(name = "T_ShuRecord")
public class ShuBean implements Parcelable {
    @Column(name = "UnitCode", isId = true)
    private String UnitCode;
    @Column(name = "uniType")
    private String uniType;
    @Column(name = "CheckRoom")
    private int CheckRoom;
    @Column(name = "ShuRoom")
    private int ShuRoom;
    @Column(name = "ShuFen")
    private int ShuFen;
    @Column(name = "ShuDong")
    private int ShuDong;
    @Column(name = "ShuDao")
    private int ShuDao;
    @Column(name = "ShuYaoHen")
    private int ShuYaoHen;
    @Column(name = "ZhuaYin")
    private int ZhuaYin;
    @Column(name = "ShuShi")
    private int ShuShi;
    @Column(name = "HuoShu")
    private int HuoShu;
    @Column(name = "FangShuRoom")
    private int FangShuRoom;
    @Column(name = "FangShuBadRoom")
    private int FangShuBadRoom;
    @Column(name = "ChuShuiKou")
    private int ChuShuiKou;
    @Column(name = "PaiShuiGou")
    private int PaiShuiGou;
    @Column(name = "DiLou")
    private int DiLou;
    @Column(name = "MenFeng")
    private int MenFeng;
    @Column(name = "WoodDoor")
    private int WoodDoor;
    @Column(name = "DangShuBan")
    private int DangShuBan;
    @Column(name = "KongDong")
    private int KongDong;
    @Column(name = "PaiFengShan")
    private int PaiFengShan;
    @Column(name = "TongFengKou")
    private int TongFengKou;
    @Column(name = "Window")
    private int Window;
    @Column(name = "CheckDistance")
    private int CheckDistance;
    @Column(name = "ShuJiNum")
    private int ShuJiNum;
    @Column(name = "ShuFen2")
    private int ShuFen2;
    @Column(name = "ShuDong2")
    private int ShuDong2;
    @Column(name = "ShuDao2")
    private int ShuDao2;
    @Column(name = "ShuYaoHen2")
    private int ShuYaoHen2;
    @Column(name = "DaoTu2")
    private int DaoTu2;
    @Column(name = "ShuShi2")
    private int ShuShi2;
    @Column(name = "HuoShu2")
    private int HuoShu2;
    @Column(name = "BaitStation")
    private int BaitStation;
    @Column(name = "WuYaoStation")
    private int WuYaoStation;
    @Column(name = "WuXiaoYaoStation")
    private int WuXiaoYaoStation;
    @Column(name = "PlaceBadStation")
    private int PlaceBadStation;
    @Column(name = "NoWarningStation")
    private int NoWarningStation;

    public ShuBean() {
    }

    @Override
    public String toString() {
        return "ShuBean{" +
                "UnitCode='" + UnitCode + '\'' +
                ", uniType='" + uniType + '\'' +
                ", CheckRoom=" + CheckRoom +
                ", ShuRoom=" + ShuRoom +
                ", ShuFen=" + ShuFen +
                ", ShuDong=" + ShuDong +
                ", ShuDao=" + ShuDao +
                ", ShuYaoHen=" + ShuYaoHen +
                ", ZhuaYin=" + ZhuaYin +
                ", ShuShi=" + ShuShi +
                ", HuoShu=" + HuoShu +
                ", FangShuRoom=" + FangShuRoom +
                ", FangShuBadRoom=" + FangShuBadRoom +
                ", ChuShuiKou=" + ChuShuiKou +
                ", PaiShuiGou=" + PaiShuiGou +
                ", DiLou=" + DiLou +
                ", MenFeng=" + MenFeng +
                ", WoodDoor=" + WoodDoor +
                ", DangShuBan=" + DangShuBan +
                ", KongDong=" + KongDong +
                ", PaiFengShan=" + PaiFengShan +
                ", TongFengKou=" + TongFengKou +
                ", Window=" + Window +
                ", CheckDistance=" + CheckDistance +
                ", ShuJiNum=" + ShuJiNum +
                ", ShuFen2=" + ShuFen2 +
                ", ShuDong2=" + ShuDong2 +
                ", ShuDao2=" + ShuDao2 +
                ", ShuYaoHen2=" + ShuYaoHen2 +
                ", DaoTu2=" + DaoTu2 +
                ", ShuShi2=" + ShuShi2 +
                ", HuoShu2=" + HuoShu2 +
                ", BaitStation=" + BaitStation +
                ", WuYaoStation=" + WuYaoStation +
                ", WuXiaoYaoStation=" + WuXiaoYaoStation +
                ", PlaceBadStation=" + PlaceBadStation +
                ", NoWarningStation=" + NoWarningStation +
                '}';
    }

    public String getUniType() {
        return uniType;
    }

    public void setUniType(String uniType) {
        this.uniType = uniType;
    }

    public int getShuDao() {
        return ShuDao;
    }

    public void setShuDao(int shuDao) {
        ShuDao = shuDao;
    }

    public int getShuYaoHen() {
        return ShuYaoHen;
    }

    public void setShuYaoHen(int shuYaoHen) {
        ShuYaoHen = shuYaoHen;
    }

    public int getKongDong() {
        return KongDong;
    }

    public void setKongDong(int kongDong) {
        KongDong = kongDong;
    }

    public int getPaiFengShan() {
        return PaiFengShan;
    }

    public void setPaiFengShan(int paiFengShan) {
        PaiFengShan = paiFengShan;
    }

    public int getHuoShu2() {
        return HuoShu2;
    }

    public void setHuoShu2(int huoShu2) {
        HuoShu2 = huoShu2;
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

    public int getShuRoom() {
        return ShuRoom;
    }

    public void setShuRoom(int shuRoom) {
        ShuRoom = shuRoom;
    }

    public int getShuFen() {
        return ShuFen;
    }

    public void setShuFen(int shuFen) {
        ShuFen = shuFen;
    }

    public int getShuDong() {
        return ShuDong;
    }

    public void setShuDong(int shuDong) {
        ShuDong = shuDong;
    }

    public int getZhuaYin() {
        return ZhuaYin;
    }

    public void setZhuaYin(int zhuaYin) {
        ZhuaYin = zhuaYin;
    }

    public int getShuShi() {
        return ShuShi;
    }

    public void setShuShi(int shuShi) {
        ShuShi = shuShi;
    }

    public int getHuoShu() {
        return HuoShu;
    }

    public void setHuoShu(int huoShu) {
        HuoShu = huoShu;
    }

    public int getFangShuRoom() {
        return FangShuRoom;
    }

    public void setFangShuRoom(int fangShuRoom) {
        FangShuRoom = fangShuRoom;
    }

    public int getFangShuBadRoom() {
        return FangShuBadRoom;
    }

    public void setFangShuBadRoom(int fangShuBadRoom) {
        FangShuBadRoom = fangShuBadRoom;
    }

    public int getChuShuiKou() {
        return ChuShuiKou;
    }

    public void setChuShuiKou(int chuShuiKou) {
        ChuShuiKou = chuShuiKou;
    }

    public int getPaiShuiGou() {
        return PaiShuiGou;
    }

    public void setPaiShuiGou(int paiShuiGou) {
        PaiShuiGou = paiShuiGou;
    }

    public int getDiLou() {
        return DiLou;
    }

    public void setDiLou(int diLou) {
        DiLou = diLou;
    }

    public int getMenFeng() {
        return MenFeng;
    }

    public void setMenFeng(int menFeng) {
        MenFeng = menFeng;
    }

    public int getWoodDoor() {
        return WoodDoor;
    }

    public void setWoodDoor(int woodDoor) {
        WoodDoor = woodDoor;
    }

    public int getDangShuBan() {
        return DangShuBan;
    }

    public void setDangShuBan(int dangShuBan) {
        DangShuBan = dangShuBan;
    }

    public int getTongFengKou() {
        return TongFengKou;
    }

    public void setTongFengKou(int tongFengKou) {
        TongFengKou = tongFengKou;
    }

    public int getWindow() {
        return Window;
    }

    public void setWindow(int window) {
        Window = window;
    }

    public int getCheckDistance() {
        return CheckDistance;
    }

    public void setCheckDistance(int checkDistance) {
        CheckDistance = checkDistance;
    }

    public int getShuJiNum() {
        return ShuJiNum;
    }

    public void setShuJiNum(int shuJiNum) {
        ShuJiNum = shuJiNum;
    }

    public int getShuFen2() {
        return ShuFen2;
    }

    public void setShuFen2(int shuFen2) {
        ShuFen2 = shuFen2;
    }

    public int getShuDong2() {
        return ShuDong2;
    }

    public void setShuDong2(int shuDong2) {
        ShuDong2 = shuDong2;
    }

    public int getShuDao2() {
        return ShuDao2;
    }

    public void setShuDao2(int shuDao2) {
        ShuDao2 = shuDao2;
    }

    public int getShuYaoHen2() {
        return ShuYaoHen2;
    }

    public void setShuYaoHen2(int shuYaoHen2) {
        ShuYaoHen2 = shuYaoHen2;
    }

    public int getDaoTu2() {
        return DaoTu2;
    }

    public void setDaoTu2(int daoTu2) {
        DaoTu2 = daoTu2;
    }

    public int getShuShi2() {
        return ShuShi2;
    }

    public void setShuShi2(int shuShi2) {
        ShuShi2 = shuShi2;
    }

    public int getBaitStation() {
        return BaitStation;
    }

    public void setBaitStation(int baitStation) {
        BaitStation = baitStation;
    }

    public int getWuYaoStation() {
        return WuYaoStation;
    }

    public void setWuYaoStation(int wuYaoStation) {
        WuYaoStation = wuYaoStation;
    }

    public int getWuXiaoYaoStation() {
        return WuXiaoYaoStation;
    }

    public void setWuXiaoYaoStation(int wuXiaoYaoStation) {
        WuXiaoYaoStation = wuXiaoYaoStation;
    }

    public int getPlaceBadStation() {
        return PlaceBadStation;
    }

    public void setPlaceBadStation(int placeBadStation) {
        PlaceBadStation = placeBadStation;
    }

    public int getNoWarningStation() {
        return NoWarningStation;
    }

    public void setNoWarningStation(int noWarningStation) {
        NoWarningStation = noWarningStation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UnitCode);
        dest.writeString(this.uniType);
        dest.writeInt(this.CheckRoom);
        dest.writeInt(this.ShuRoom);
        dest.writeInt(this.ShuFen);
        dest.writeInt(this.ShuDong);
        dest.writeInt(this.ShuDao);
        dest.writeInt(this.ShuYaoHen);
        dest.writeInt(this.ZhuaYin);
        dest.writeInt(this.ShuShi);
        dest.writeInt(this.HuoShu);
        dest.writeInt(this.FangShuRoom);
        dest.writeInt(this.FangShuBadRoom);
        dest.writeInt(this.ChuShuiKou);
        dest.writeInt(this.PaiShuiGou);
        dest.writeInt(this.DiLou);
        dest.writeInt(this.MenFeng);
        dest.writeInt(this.WoodDoor);
        dest.writeInt(this.DangShuBan);
        dest.writeInt(this.KongDong);
        dest.writeInt(this.PaiFengShan);
        dest.writeInt(this.TongFengKou);
        dest.writeInt(this.Window);
        dest.writeInt(this.CheckDistance);
        dest.writeInt(this.ShuJiNum);
        dest.writeInt(this.ShuFen2);
        dest.writeInt(this.ShuDong2);
        dest.writeInt(this.ShuDao2);
        dest.writeInt(this.ShuYaoHen2);
        dest.writeInt(this.DaoTu2);
        dest.writeInt(this.ShuShi2);
        dest.writeInt(this.HuoShu2);
        dest.writeInt(this.BaitStation);
        dest.writeInt(this.WuYaoStation);
        dest.writeInt(this.WuXiaoYaoStation);
        dest.writeInt(this.PlaceBadStation);
        dest.writeInt(this.NoWarningStation);
    }

    protected ShuBean(Parcel in) {
        this.UnitCode = in.readString();
        this.uniType = in.readString();
        this.CheckRoom = in.readInt();
        this.ShuRoom = in.readInt();
        this.ShuFen = in.readInt();
        this.ShuDong = in.readInt();
        this.ShuDao = in.readInt();
        this.ShuYaoHen = in.readInt();
        this.ZhuaYin = in.readInt();
        this.ShuShi = in.readInt();
        this.HuoShu = in.readInt();
        this.FangShuRoom = in.readInt();
        this.FangShuBadRoom = in.readInt();
        this.ChuShuiKou = in.readInt();
        this.PaiShuiGou = in.readInt();
        this.DiLou = in.readInt();
        this.MenFeng = in.readInt();
        this.WoodDoor = in.readInt();
        this.DangShuBan = in.readInt();
        this.KongDong = in.readInt();
        this.PaiFengShan = in.readInt();
        this.TongFengKou = in.readInt();
        this.Window = in.readInt();
        this.CheckDistance = in.readInt();
        this.ShuJiNum = in.readInt();
        this.ShuFen2 = in.readInt();
        this.ShuDong2 = in.readInt();
        this.ShuDao2 = in.readInt();
        this.ShuYaoHen2 = in.readInt();
        this.DaoTu2 = in.readInt();
        this.ShuShi2 = in.readInt();
        this.HuoShu2 = in.readInt();
        this.BaitStation = in.readInt();
        this.WuYaoStation = in.readInt();
        this.WuXiaoYaoStation = in.readInt();
        this.PlaceBadStation = in.readInt();
        this.NoWarningStation = in.readInt();
    }

    public static final Creator<ShuBean> CREATOR = new Creator<ShuBean>() {
        @Override
        public ShuBean createFromParcel(Parcel source) {
            return new ShuBean(source);
        }

        @Override
        public ShuBean[] newArray(int size) {
            return new ShuBean[size];
        }
    };
}
