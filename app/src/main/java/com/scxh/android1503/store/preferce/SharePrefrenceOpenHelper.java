package com.scxh.android1503.store.preferce;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePrefrenceOpenHelper {
	private static final String PREFRENCE_NAME = "com.scxh.android.PREFERENCE_STUDENT";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String AGE = "age";
	private static final String SCORE = "score";
	private SharedPreferences mSharedPreferences;

	private static SharePrefrenceOpenHelper sHelper;

	public static SharePrefrenceOpenHelper getInstance(Context context) {
		if (sHelper == null) {
			sHelper = new SharePrefrenceOpenHelper(context);
		}
		return sHelper;
	}

	private SharePrefrenceOpenHelper(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREFRENCE_NAME,context.MODE_PRIVATE);
	}

	/**
	 * 添加学生对象
	 * 
	 * @param student
	 */
	public void addStudent(Student student) {
		Editor editor = mSharedPreferences.edit();
		editor.putInt(ID, student.getId());
		editor.putString(NAME, student.getName());
		editor.putInt(AGE, student.getAge());
		editor.putString(SCORE, student.getScore());
		editor.commit();
	}

	/**
	 * if 返回 null表示 查找的学生不存在.
	 * 
	 * @param name
	 * @return
	 */
	public Student findStudentByName(String name) {
		String studentName = mSharedPreferences.getString(NAME, "");
		if (studentName.equals(name)) {
			int id = mSharedPreferences.getInt(ID, 0);
			int age = mSharedPreferences.getInt(AGE, 0);
			String score = mSharedPreferences.getString(SCORE, "0");

			Student student = new Student();
			student.setId(id);
			student.setName(studentName);
			student.setAge(age);
			student.setScore(score);
			return student;
		} else {
			return null;
		}

	}

	/**
	 * 清空所有信息
	 */
	public void clearAll() {
		mSharedPreferences.edit().clear().commit();
	}
}
