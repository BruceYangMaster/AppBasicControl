package com.scxh.android1503.store.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataColumns {
	public abstract class TearchTable implements BaseColumns{
		public static final String TABLE_NAME = "tearch";
		public static final String COLUMN_NAME_NAME = "name"; //姓名
		public static final String COLUMN_NAME_SEX = "sex";   //性别
//		public static final String COLUMN_NAME_ID = "_id";    //id
	}
	
	public abstract class UserTable implements BaseColumns{
		public static final String TABLE_NAME = "user";
		public static final String COLUMN_NAME_NAME = "name"; //姓名
		public static final String COLUMN_NAME_PASSWORD = "password";   //密码
//		public static final String COLUMN_NAME_ID = "_id";    //id
	}
	
	public interface SMS extends BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://sms");

		public static final String FILTER = "!imichat";

		public static final String TYPE = "type";

		public static final String THREAD_ID = "thread_id";

		public static final String ADDRESS = "address";

		public static final String PERSON_ID = "person";

		public static final String DATE = "date";

		public static final String READ = "read";

		public static final String BODY = "body";

		public static final String PROTOCOL = "protocol";

	}
}
