package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 47066 on 2016/6/16.
 */
@Table(name = "T_Picture")
public class PhotoBean implements Parcelable {
    @Column(name = "UnitCode")
    private String UnitCode;
    @Column(name = "PictureName", isId = true)
    private String PictureName;
    @Column(name = "TaskCode")
    private String TaskCode;

    public PhotoBean() {
    }

    public PhotoBean(String unitCode, String pictureName, String taskCode) {
        UnitCode = unitCode;
        PictureName = pictureName;
        TaskCode = taskCode;
    }

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    public String getPictureName() {
        return PictureName;
    }

    public void setPictureName(String pictureName) {
        PictureName = pictureName;
    }

    public String getTaskCode() {
        return TaskCode;
    }

    public void setTaskCode(String taskCode) {
        TaskCode = taskCode;
    }

    @Override
    public String toString() {
        return "PhotoBean{" +
                "UnitCode='" + UnitCode + '\'' +
                ", PictureName='" + PictureName + '\'' +
                ", TaskCode='" + TaskCode + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UnitCode);
        dest.writeString(this.PictureName);
        dest.writeString(this.TaskCode);
    }

    protected PhotoBean(Parcel in) {
        this.UnitCode = in.readString();
        this.PictureName = in.readString();
        this.TaskCode = in.readString();
    }

    public static final Parcelable.Creator<PhotoBean> CREATOR = new Parcelable.Creator<PhotoBean>() {
        @Override
        public PhotoBean createFromParcel(Parcel source) {
            return new PhotoBean(source);
        }

        @Override
        public PhotoBean[] newArray(int size) {
            return new PhotoBean[size];
        }
    };
}
