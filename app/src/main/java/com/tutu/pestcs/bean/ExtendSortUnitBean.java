package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/24.
 *扩展分类 共十八种 对应国标的十种 见文档06分组任务完成进度页统计对应检查单位类型表
 */
@Table(name = "T_UnitClass")
public class ExtendSortUnitBean implements Parcelable {
	//单位的分类id
	@Column(name = "unitID",isId = true)
	private String unitID; //
	//单位分类的名称
	@Column(name = "ClassName")
	private String ClassName;
	//是否是重点单位
	@Column(name = "IskeyClass")
	private boolean IskeyClass;

	public ExtendSortUnitBean() {
	}

	public ExtendSortUnitBean(String unitID, String className, boolean iskeyClass) {
		this.unitID = unitID;
		ClassName = className;
		IskeyClass = iskeyClass;
	}

	public String getUnitID() {
		return unitID;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public boolean iskeyClass() {
		return IskeyClass;
	}

	public void setIskeyClass(boolean iskeyClass) {
		IskeyClass = iskeyClass;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.unitID);
		dest.writeString(this.ClassName);
		dest.writeByte(IskeyClass ? (byte) 1 : (byte) 0);
	}

	protected ExtendSortUnitBean(Parcel in) {
		this.unitID = in.readString();
		this.ClassName = in.readString();
		this.IskeyClass = in.readByte() != 0;
	}

	public static final Creator<ExtendSortUnitBean> CREATOR = new Creator<ExtendSortUnitBean>() {
		@Override
		public ExtendSortUnitBean createFromParcel(Parcel source) {
			return new ExtendSortUnitBean(source);
		}

		@Override
		public ExtendSortUnitBean[] newArray(int size) {
			return new ExtendSortUnitBean[size];
		}
	};

	@Override
	public String toString() {
		return "ExtendSortUnitBean{" +
			", unitID='" + unitID + '\'' +
			", ClassName='" + ClassName + '\'' +
			", IskeyClass=" + IskeyClass +
			'}';
	}
}
