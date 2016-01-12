package com.scxh.android1503.store.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.os.Environment;

import com.scxh.android1503.store.preferce.Student;

public class FileOpenHelper {
	private File rootFile;

	private static FileOpenHelper sHelper;

	public static FileOpenHelper getInstance(Context context, String fileName) {
		if (sHelper == null) {
			sHelper = new FileOpenHelper(context, fileName);
		}
		return sHelper;
	}

	private FileOpenHelper(Context context, String fileName) {
		File rootFileDir = context
				.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		rootFile = new File(rootFileDir, fileName);
	}

	/**
	 * 写学生对象到文件
	 * 
	 * @param student
	 */
	public void writeStudent(Student student) {
		OutputStream os = null;
		DataOutputStream ds = null;
		try {
			os = new FileOutputStream(rootFile);
			ds = new DataOutputStream(os);

			ds.writeInt(student.getId());
			ds.writeUTF(student.getName());
			ds.writeInt(student.getAge());
			ds.writeUTF(student.getScore());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 从文件中读取学生对象
	 * 
	 * @return
	 */
	public Student readStudent() {
		InputStream is = null;
		DataInputStream ds = null;
		Student student = null;
		try {
			is = new FileInputStream(rootFile);
			ds = new DataInputStream(is);

			student = new Student();
			student.setId(ds.readInt());
			student.setName(ds.readUTF());
			student.setAge(ds.readInt());
			student.setScore(ds.readUTF());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return student;
	}
}
