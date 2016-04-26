package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/24.
 */
@Table(name = "T_CheckUnit")
public class CheakInsertBean implements Parcelable {
	@Column(name = "id",isId = true)
	private int id;
	/**
	 * 十八位数字,由当前时间2016 03 04 20 30 +检查人员code 2222
	 */
	@Column(name = "UnitCode",property = "UNIQUE")
	private String UnitCode;//unique 检查单位代码
	@Column(name = "TaskCode")
	private String TaskCode;//任务代码
	@Column(name = "AreaCode") //地区代码 0715
	private String AreaCode;
	@Column(name = "UnitClassID")
	private int UnitClassID;//单位分类代码
	@Column(name = "NamePlace")
	private String NamePlace;//地点
	@Column(name = "IsKeyUnit")
	private boolean IsKeyUnit;//是否是重点单位
	@Column(name = "ExpertCode")
	private String ExpertCode; //检查人员代码
	@Column(name = "ChkDateTime")
	private long ChkDateTime; //检查时间
	@Column(name = "Note")
	private String Note;//400 备注

	public CheakInsertBean(int id,String unitCode, String taskCode, String areaCode, int unitClassID, String namePlace,
	                       boolean isKeyUnit, String expertCode, long chkDateTime, String note) {
		this.id = id;
		UnitCode = unitCode;
		TaskCode = taskCode;
		AreaCode = areaCode;
		UnitClassID = unitClassID;
		NamePlace = namePlace;
		IsKeyUnit = isKeyUnit;
		ExpertCode = expertCode;
		ChkDateTime = chkDateTime;
		Note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CheakInsertBean() {
	}

	public String getUnitCode() {
		return UnitCode;
	}

	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
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

	public int getUnitClassID() {
		return UnitClassID;
	}

	public void setUnitClassID(int unitClassID) {
		UnitClassID = unitClassID;
	}

	public String getNamePlace() {
		return NamePlace;
	}

	public void setNamePlace(String namePlace) {
		NamePlace = namePlace;
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

	public long getChkDateTime() {
		return ChkDateTime;
	}

	public void setChkDateTime(long chkDateTime) {
		ChkDateTime = chkDateTime;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.UnitCode);
		dest.writeString(this.TaskCode);
		dest.writeString(this.AreaCode);
		dest.writeInt(this.UnitClassID);
		dest.writeString(this.NamePlace);
		dest.writeByte(IsKeyUnit ? (byte) 1 : (byte) 0);
		dest.writeString(this.ExpertCode);
		dest.writeLong(this.ChkDateTime);
		dest.writeString(this.Note);
	}

	protected CheakInsertBean(Parcel in) {
		this.UnitCode = in.readString();
		this.TaskCode = in.readString();
		this.AreaCode = in.readString();
		this.UnitClassID = in.readInt();
		this.NamePlace = in.readString();
		this.IsKeyUnit = in.readByte() != 0;
		this.ExpertCode = in.readString();
		this.ChkDateTime = in.readLong();
		this.Note = in.readString();
	}

	public static final Parcelable.Creator<CheakInsertBean> CREATOR = new Parcelable.Creator<CheakInsertBean>() {
		@Override
		public CheakInsertBean createFromParcel(Parcel source) {
			return new CheakInsertBean(source);
		}

		@Override
		public CheakInsertBean[] newArray(int size) {
			return new CheakInsertBean[size];
		}
	};
}
