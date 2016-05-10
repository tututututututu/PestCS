package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/24.
 * 国标中的人口对应的检查数量指标
 */
@Table(name = "T_GB2011")
public class GuoBiaoSortUnitBean implements Parcelable {
    @Column(name = "sortID", isId = true)
    private String sortID;
    @Column(name = "Item")
    private String Item;
    @Column(name = "ClassName")
    private String ClassName;
    @Column(name = "SortNo")
    private int SortNo;
    @Column(name = "P200Unit")
    private int P200Unit;
    @Column(name = "P200Room")
    private int P200Room;
    @Column(name = "P100Unit")
    private int P100Unit;
    @Column(name = "P100Room")
    private int P100Room;
    @Column(name = "P50Unit")
    private int P50Unit;
    @Column(name = "P50Room")
    private int P50Room;
    @Column(name = "P10Unit")
    private int P10Unit;
    @Column(name = "P10Unit")
    private int P10Room;
    @Column(name = "P01Unit")
    private int P01Unit;
    @Column(name = "P01Room")
    private int P01Room;

    public GuoBiaoSortUnitBean() {
    }

    public GuoBiaoSortUnitBean(String sortID, String item, String className, int sortNo, int p200Unit, int
            p200Room, int p100Unit, int p100Room, int p50Unit, int p50Room, int p10Unit, int p10Room, int p01Unit, int
                                       p01Room) {
        this.sortID = sortID;
        Item = item;
        ClassName = className;
        SortNo = sortNo;
        P200Unit = p200Unit;
        P200Room = p200Room;
        P100Unit = p100Unit;
        P100Room = p100Room;
        P50Unit = p50Unit;
        P50Room = p50Room;
        P10Unit = p10Unit;
        P10Room = p10Room;
        P01Unit = p01Unit;
        P01Room = p01Room;
    }

    public String getSortID() {
        return sortID;
    }

    public void setSortID(String sortID) {
        this.sortID = sortID;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int sortNo) {
        SortNo = sortNo;
    }

    public int getP200Unit() {
        return P200Unit;
    }

    public void setP200Unit(int p200Unit) {
        P200Unit = p200Unit;
    }

    public int getP200Room() {
        return P200Room;
    }

    public void setP200Room(int p200Room) {
        P200Room = p200Room;
    }

    public int getP100Unit() {
        return P100Unit;
    }

    public void setP100Unit(int p100Unit) {
        P100Unit = p100Unit;
    }

    public int getP100Room() {
        return P100Room;
    }

    public void setP100Room(int p100Room) {
        P100Room = p100Room;
    }

    public int getP50Unit() {
        return P50Unit;
    }

    public void setP50Unit(int p50Unit) {
        P50Unit = p50Unit;
    }

    public int getP50Room() {
        return P50Room;
    }

    public void setP50Room(int p50Room) {
        P50Room = p50Room;
    }

    public int getP10Unit() {
        return P10Unit;
    }

    public void setP10Unit(int p10Unit) {
        P10Unit = p10Unit;
    }

    public int getP10Room() {
        return P10Room;
    }

    public void setP10Room(int p10Room) {
        P10Room = p10Room;
    }

    public int getP01Unit() {
        return P01Unit;
    }

    public void setP01Unit(int p01Unit) {
        P01Unit = p01Unit;
    }

    public int getP01Room() {
        return P01Room;
    }

    public void setP01Room(int p01Room) {
        P01Room = p01Room;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sortID);
        dest.writeString(this.Item);
        dest.writeString(this.ClassName);
        dest.writeInt(this.SortNo);
        dest.writeInt(this.P200Unit);
        dest.writeInt(this.P200Room);
        dest.writeInt(this.P100Unit);
        dest.writeInt(this.P100Room);
        dest.writeInt(this.P50Unit);
        dest.writeInt(this.P50Room);
        dest.writeInt(this.P10Unit);
        dest.writeInt(this.P10Room);
        dest.writeInt(this.P01Unit);
        dest.writeInt(this.P01Room);
    }

    protected GuoBiaoSortUnitBean(Parcel in) {
        this.sortID = in.readString();
        this.Item = in.readString();
        this.ClassName = in.readString();
        this.SortNo = in.readInt();
        this.P200Unit = in.readInt();
        this.P200Room = in.readInt();
        this.P100Unit = in.readInt();
        this.P100Room = in.readInt();
        this.P50Unit = in.readInt();
        this.P50Room = in.readInt();
        this.P10Unit = in.readInt();
        this.P10Room = in.readInt();
        this.P01Unit = in.readInt();
        this.P01Room = in.readInt();
    }

    public static final Parcelable.Creator<GuoBiaoSortUnitBean> CREATOR = new Parcelable.Creator<GuoBiaoSortUnitBean>
            () {
        @Override
        public GuoBiaoSortUnitBean createFromParcel(Parcel source) {
            return new GuoBiaoSortUnitBean(source);
        }

        @Override
        public GuoBiaoSortUnitBean[] newArray(int size) {
            return new GuoBiaoSortUnitBean[size];
        }
    };

    @Override
    public String toString() {
        return "GuoBiaoSortUnitBean{" +
                ", sortID='" + sortID + '\'' +
                ", Item='" + Item + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", SortNo=" + SortNo +
                ", P200Unit=" + P200Unit +
                ", P200Room=" + P200Room +
                ", P100Unit=" + P100Unit +
                ", P100Room=" + P100Room +
                ", P50Unit=" + P50Unit +
                ", P50Room=" + P50Room +
                ", P10Unit=" + P10Unit +
                ", P10Room=" + P10Room +
                ", P01Unit=" + P01Unit +
                ", P01Room=" + P01Room +
                '}';
    }
}
