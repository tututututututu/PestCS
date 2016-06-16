package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 47066 on 2016/5/1.
 */
@Table(name = "T_Note")
public class NoteBean implements Parcelable {
    @Column(name = "note")
    private String note;
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

    public NoteBean() {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "note='" + note + '\'' +
                ", UnitCode='" + UnitCode + '\'' +
                ", uniType='" + uniType + '\'' +
                ", TaskCode='" + TaskCode + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", UnitClassID='" + UnitClassID + '\'' +
                ", IsKeyUnit=" + IsKeyUnit +
                ", ExpertCode='" + ExpertCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.note);
        dest.writeString(this.UnitCode);
        dest.writeString(this.uniType);
        dest.writeString(this.TaskCode);
        dest.writeString(this.AreaCode);
        dest.writeString(this.UnitClassID);
        dest.writeByte(this.IsKeyUnit ? (byte) 1 : (byte) 0);
        dest.writeString(this.ExpertCode);
    }

    protected NoteBean(Parcel in) {
        this.note = in.readString();
        this.UnitCode = in.readString();
        this.uniType = in.readString();
        this.TaskCode = in.readString();
        this.AreaCode = in.readString();
        this.UnitClassID = in.readString();
        this.IsKeyUnit = in.readByte() != 0;
        this.ExpertCode = in.readString();
    }

    public static final Creator<NoteBean> CREATOR = new Creator<NoteBean>() {
        @Override
        public NoteBean createFromParcel(Parcel source) {
            return new NoteBean(source);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
        }
    };
}
