package com.scxh.android1503.parameter;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
	private String name;
	private String number;
	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(number);
		dest.writeInt(age);
	}
	
	/**
	 * 必须用 public static final 修饰符  
	 * 对象必须用 CREATOR 
	 */
	public static final Creator<Student> CREATOR = new Creator<Student>() {

		@Override
		public Student createFromParcel(Parcel source) {
			String name = source.readString();
			String number = source.readString();
			int age = source.readInt();
			
			Student student = new Student();
			student.setName(name);
			student.setNumber(number);
			student.setAge(age);
			
			return student;
		}

		@Override
		public Student[] newArray(int size) {
			return new Student[size];
		}
		
	};
	
	
}
