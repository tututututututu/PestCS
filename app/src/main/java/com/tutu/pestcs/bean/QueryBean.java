package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 47066 on 2016/5/2.
 */
public class QueryBean implements Parcelable {
    //查询类型 是鼠 蚊 蝇 蟑螂中的一种
    private int queryType = -1;
    //单位类型"01"--"18"
    private String unitType = "";
    /**
     * 是否是重点单位
     * 0:不限 1.是  2.不是
     */
    private int isKeyUnit = -1;

    /**
     * 1.选择的选项一 2.选择的选项2 3.选择的是选项3
     */
    private int condition1 = 3;

    /**
     * 1.选择的选项二 2.选择的选项2 3.选择的是选项3
     */
    private int condition2 = 3;

    /**
     * 1.选择的选项三 2.选择的选项2 3.选择的是选项3
     */
    private int condition3 = 3;

    public QueryBean() {
    }

    @Override
    public String toString() {
        return "QueryBean{" +
                "queryType=" + queryType +
                ", unitType='" + unitType + '\'' +
                ", isKeyUnit=" + isKeyUnit +
                ", condition1=" + condition1 +
                ", condition2=" + condition2 +
                ", condition3=" + condition3 +
                '}';
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public int getIsKeyUnit() {
        return isKeyUnit;
    }

    public void setIsKeyUnit(int isKeyUnit) {
        this.isKeyUnit = isKeyUnit;
    }

    public int getCondition1() {
        return condition1;
    }

    public void setCondition1(int condition1) {
        this.condition1 = condition1;
    }

    public int getCondition2() {
        return condition2;
    }

    public void setCondition2(int condition2) {
        this.condition2 = condition2;
    }

    public int getCondition3() {
        return condition3;
    }

    public void setCondition3(int condition3) {
        this.condition3 = condition3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.queryType);
        dest.writeString(this.unitType);
        dest.writeInt(this.isKeyUnit);
        dest.writeInt(this.condition1);
        dest.writeInt(this.condition2);
        dest.writeInt(this.condition3);
    }

    protected QueryBean(Parcel in) {
        this.queryType = in.readInt();
        this.unitType = in.readString();
        this.isKeyUnit = in.readInt();
        this.condition1 = in.readInt();
        this.condition2 = in.readInt();
        this.condition3 = in.readInt();
    }

    public static final Parcelable.Creator<QueryBean> CREATOR = new Parcelable.Creator<QueryBean>() {
        @Override
        public QueryBean createFromParcel(Parcel source) {
            return new QueryBean(source);
        }

        @Override
        public QueryBean[] newArray(int size) {
            return new QueryBean[size];
        }
    };
}
