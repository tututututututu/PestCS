package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 47066 on 2016/5/1.
 */
public class NoteBean implements Parcelable {
    private int id;
    private String note;
    private String UnitCode;

    public NoteBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", note='" + note + '\'' +
                ", UnitCode='" + UnitCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.note);
        dest.writeString(this.UnitCode);
    }

    protected NoteBean(Parcel in) {
        this.id = in.readInt();
        this.note = in.readString();
        this.UnitCode = in.readString();
    }

    public static final Parcelable.Creator<NoteBean> CREATOR = new Parcelable.Creator<NoteBean>() {
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
