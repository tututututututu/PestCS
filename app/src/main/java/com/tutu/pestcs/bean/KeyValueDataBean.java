package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 47066 on 2016/5/8.
 */
public class KeyValueDataBean implements Parcelable {
    private String key;
    private String value;

    public KeyValueDataBean() {
    }



    @Override
    public String toString() {
        return "KeyValueDataBean{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public KeyValueDataBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
    }

    protected KeyValueDataBean(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<KeyValueDataBean> CREATOR = new Parcelable.Creator<KeyValueDataBean>() {
        @Override
        public KeyValueDataBean createFromParcel(Parcel source) {
            return new KeyValueDataBean(source);
        }

        @Override
        public KeyValueDataBean[] newArray(int size) {
            return new KeyValueDataBean[size];
        }
    };
}
