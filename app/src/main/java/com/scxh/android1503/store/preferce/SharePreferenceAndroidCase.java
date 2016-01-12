package com.scxh.android1503.store.preferce;

import com.scxh.android1503.util.Logs;

import android.test.AndroidTestCase;

public class SharePreferenceAndroidCase extends AndroidTestCase {
	private SharePrefrenceOpenHelper mSharePrefrenceOpenHelper;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mSharePrefrenceOpenHelper = SharePrefrenceOpenHelper.getInstance(getContext());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddStudent() {
		Student student = new Student();
		student.setId(10);
		student.setName("张三");
		student.setAge(18);
		student.setScore("97");

		mSharePrefrenceOpenHelper.addStudent(student);
	}

	public void testFindStudentByName() {
		Student student = mSharePrefrenceOpenHelper.findStudentByName("李四");
		if (student != null) {
			Logs.v("<<<<<<<<<<  学生信息 >>>>>>>>> ");
			Logs.d("ID  姓名     年龄     成绩   ");
			Logs.d(student.getId() + "   " + student.getName() + "     "
					+ student.getAge() + "     " + student.getScore());
		}else{
			Logs.v("查找学生不存在!");
		}
	}
}
