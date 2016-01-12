package com.scxh.android1503.store.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.test.AndroidTestCase;

import com.scxh.android1503.R;
import com.scxh.android1503.store.preferce.Student;
import com.scxh.android1503.util.AsyncMemoryCacheImag;
import com.scxh.android1503.util.Logs;

public class FileAndroidCase extends AndroidTestCase {
	Context context;
	private FileOpenHelper mFileOpenHelper;
	AsyncMemoryCacheImag asyncMemoryCache;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = getContext();
		asyncMemoryCache = new AsyncMemoryCacheImag(getContext());
		mFileOpenHelper = FileOpenHelper.getInstance(context, "student_file");
	}

	public void testGetFilesDir() {
		// getFilesDir :/data/data/com.scxh.android1503/files
		File fileDir = context.getFilesDir();
		String dir = fileDir.getAbsolutePath();
		Logs.v("getFilesDir  :" + dir);
	}

	public void testGetCacheDir() {
		String dir = context.getCacheDir().getAbsolutePath();
		// getCacheDir :/data/data/com.scxh.android1503/cache
		Logs.v("getCacheDir :" + dir);

	}

	/**
	 * java 写内部存储文件方式
	 */
	public void writeFiletoFileDir() {
		// /data/data/com.scxh.android1503/files/myFile.txt
		String name = "myFile.txt";
		String content = "hello android!";

		try {
			File file = new File(context.getFilesDir(), name);
			FileOutputStream os = new FileOutputStream(file);
			os.write(content.getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Android 写内部存储文件方式
	 */
	public void writeFileToFileDirByAndroid() {
		try {
			FileOutputStream os = context.openFileOutput("myFile1.txt",
					context.MODE_APPEND);
			os.write("文件存储学习".getBytes());
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 外部存储公共区域 getExternalStoragePublicDirectory
	 */
	public void writeFileToExternalStoragePublic() {
		String dir = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES).getAbsolutePath();
		Logs.v("DIRECTORY_PICTURES  :" + dir);

		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.m8);

		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"m8.png");

		FileOutputStream os;
		try {
			os = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 外部存储私有区域 getExternalFilesDir getExternalCacheDir
	 * getExternalFilesDir（Environment.DIRECTORY_PICTURES）
	 * /mnt/sdcard/Android/data/com.scxh.android1503/files/Pictures
	 * getExternalCacheDir :/mnt/sdcard/Android/data/com.scxh.android1503/cache
	 */
	public void writeFileToExternalStoreagePrivate() {
		File fileDir = context
				.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		Logs.v("getExternalFilesDir DIRECTORY_PICTURES ：" + fileDir);

		File cacheDir = context.getExternalCacheDir();
		Logs.v("getExternalCacheDir :" + cacheDir);
	}

	/**
	 * 检查外部存储是否可写
	 * 
	 * @return
	 */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public void writeFileToExternal() throws IOException {
		if (isExternalStorageWritable()) {
			File fileDir = context
					.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
			FileOutputStream os = new FileOutputStream(new File(fileDir,
					"file1.txt"));
			os.write("你好外部存储".getBytes());
			os.close();

		} else {
			Logs.v("外部储不可用!");
		}
	}

	public void getSdcardDir() {
		File dir = Environment.getExternalStorageDirectory(); // /mnt/sdcard/
		Logs.v("sdcard :" + dir);
	}

	public void writeStudentToFile() {
		Student student = new Student();
		student.setId(10);
		student.setName("张三");
		student.setAge(18);
		student.setScore("97");

		mFileOpenHelper.writeStudent(student);
	}

	public void readStudentFromFile() {
		Student student = mFileOpenHelper.readStudent();
		if (student != null) {
			Logs.v("姓名  ：" + student.getName() + " ,  成绩 :"
					+ student.getScore());
		}
	}

	public void testScanFileList() {
		// /mnt/sdcard/
		File sdcardFile = Environment.getExternalStorageDirectory();
		scanFileList(sdcardFile);
	}

	/**
	 * 扫描Sdcard（外部存储）下所有文件
	 */
	public void scanFileList(File parentFile) {
		File[] listFile = parentFile.listFiles();

		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File file = listFile[i];
				if (file.isDirectory()) {
					scanFileList(file);
				} else {
					if (file.getName().endsWith("mp3")) {
						Logs.v("文件名 :" + file.getName());
						Logs.i("路径 :" + file.getAbsolutePath());
					}
				}
			}
		}

	}
	
	
	public void testWriteFile(){
		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.m3);
		String url = "http://www.scxh.com/m5.png";
		
		
		asyncMemoryCache.writeFile(url, bitmap);
	}
	
	public void testReadFile(){
		String url = "http://www.scxh.com/m5.png";
		Bitmap bitmap = asyncMemoryCache.readFile(url);
		
		Logs.v("bitmap :"+bitmap);
		
		asyncMemoryCache.writeFile("/readtest.png", bitmap);
	}

}
