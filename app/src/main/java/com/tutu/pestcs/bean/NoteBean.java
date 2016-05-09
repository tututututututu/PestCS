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

    public NoteBean() {
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
        dest.writeString(this.note);
        dest.writeString(this.UnitCode);
    }

    protected NoteBean(Parcel in) {

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
