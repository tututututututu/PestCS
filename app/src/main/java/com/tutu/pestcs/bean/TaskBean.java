package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/12.
 */
@Table(name = "T_Task")
public class TaskBean implements Parcelable {
    @Column(name = "TaskCode", isId = true)
    String TaskCode;
    @Column(name = "CityName")
    String CityName;
    @Column(name = "AreaCode")
    String AreaCode;
    @Column(name = "Population")
    int Population;
    @Column(name = "ExpertName")
    String ExpertName;
    @Column(name = "ExpertCode")
    String ExpertCode;
    @Column(name = "Groups")
    int Groups;
    @Column(name = "StartDate")
    String StartDate;
    @Column(name = "Shu")
    boolean Shu;
    @Column(name = "Wen")
    boolean Wen;
    @Column(name = "Ying")
    boolean Ying;
    @Column(name = "Zhang")
    boolean Zhang;
    @Column(name = "IsCurrent")
    boolean IsCurrent;

    public TaskBean() {
    }

    public TaskBean(String taskCode, String cityName, String areaCode, int population, String expertName,
                    String
                            expertCode, int groups, String startDate, boolean shu, boolean wen, boolean ying, boolean
                            zhang, boolean
                            isCurrent) {
        this.TaskCode = taskCode;
        this.CityName = cityName;
        this.AreaCode = areaCode;
        this.Population = population;
        this.ExpertName = expertName;
        this.ExpertCode = expertCode;
        this.Groups = groups;
        this.StartDate = startDate;
        this.Shu = shu;
        this.Wen = wen;
        this.Ying = ying;
        this.Zhang = zhang;
        this.IsCurrent = isCurrent;
    }

    public String getTaskCode() {
        return TaskCode;
    }

    public void setTaskCode(String taskCode) {
        TaskCode = taskCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public String getExpertName() {
        return ExpertName;
    }

    public void setExpertName(String expertName) {
        ExpertName = expertName;
    }

    public String getExpertCode() {
        return ExpertCode;
    }

    public void setExpertCode(String expertCode) {
        ExpertCode = expertCode;
    }

    public int getGroups() {
        return Groups;
    }

    public void setGroups(int groups) {
        Groups = groups;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public boolean isShu() {
        return Shu;
    }

    public void setShu(boolean shu) {
        Shu = shu;
    }

    public boolean isWen() {
        return Wen;
    }

    public void setWen(boolean wen) {
        Wen = wen;
    }

    public boolean isYing() {
        return Ying;
    }

    public void setYing(boolean ying) {
        Ying = ying;
    }

    public boolean isZhang() {
        return Zhang;
    }

    public void setZhang(boolean zhang) {
        Zhang = zhang;
    }

    public boolean isCurrent() {
        return IsCurrent;
    }

    public void setCurrent(boolean current) {
        IsCurrent = current;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "TaskCode='" + TaskCode + '\'' +
                ", CityName='" + CityName + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", Population=" + Population +
                ", ExpertName='" + ExpertName + '\'' +
                ", ExpertCode='" + ExpertCode + '\'' +
                ", Groups=" + Groups +
                ", StartDate='" + StartDate + '\'' +
                ", Shu=" + Shu +
                ", Wen=" + Wen +
                ", Ying=" + Ying +
                ", Zhang=" + Zhang +
                ", IsCurrent=" + IsCurrent +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TaskCode);
        dest.writeString(this.CityName);
        dest.writeString(this.AreaCode);
        dest.writeInt(this.Population);
        dest.writeString(this.ExpertName);
        dest.writeString(this.ExpertCode);
        dest.writeInt(this.Groups);
        dest.writeString(this.StartDate);
        dest.writeByte(Shu ? (byte) 1 : (byte) 0);
        dest.writeByte(Wen ? (byte) 1 : (byte) 0);
        dest.writeByte(Ying ? (byte) 1 : (byte) 0);
        dest.writeByte(Zhang ? (byte) 1 : (byte) 0);
        dest.writeByte(IsCurrent ? (byte) 1 : (byte) 0);
    }

    protected TaskBean(Parcel in) {
        this.TaskCode = in.readString();
        this.CityName = in.readString();
        this.AreaCode = in.readString();
        this.Population = in.readInt();
        this.ExpertName = in.readString();
        this.ExpertCode = in.readString();
        this.Groups = in.readInt();
        this.StartDate = in.readString();
        this.Shu = in.readByte() != 0;
        this.Wen = in.readByte() != 0;
        this.Ying = in.readByte() != 0;
        this.Zhang = in.readByte() != 0;
        this.IsCurrent = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TaskBean> CREATOR = new Parcelable.Creator<TaskBean>() {
        @Override
        public TaskBean createFromParcel(Parcel source) {
            return new TaskBean(source);
        }

        @Override
        public TaskBean[] newArray(int size) {
            return new TaskBean[size];
        }
    };
}
