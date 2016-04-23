package com.tutu.pestcs.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tutu on 16/4/14.
 */
@Table(name = "T_User")
public class User implements Parcelable {
	@Column(name = "id", isId = true)
	int id;

	@Column(name = "UserName")
	String UserName;
	@Column(name = "PassWord")
	String PassWord;
	/**
	 * 用户类型 0管理员  1普通用户
	 */
	@Column(name = "UserGrade")
	String UserGrade;

	public User(int id, String userName, String passWord, String userGrade) {
		this.id = id;
		UserName = userName;
		PassWord = passWord;
		UserGrade = userGrade;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getUserGrade() {
		return UserGrade;
	}

	public void setUserGrade(String userGrade) {
		UserGrade = userGrade;
	}

	@Override
	public String toString() {
		return "User{" +
			"id='" + id + '\'' +
			", UserName='" + UserName + '\'' +
			", PassWord='" + PassWord + '\'' +
			", UserGrade='" + UserGrade + '\'' +
			'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.UserName);
		dest.writeString(this.PassWord);
		dest.writeString(this.UserGrade);
	}

	protected User(Parcel in) {
		this.id = in.readInt();
		this.UserName = in.readString();
		this.PassWord = in.readString();
		this.UserGrade = in.readString();
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}

