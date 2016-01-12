package com.scxh.android.fragment;

import android.test.AndroidTestCase;

import com.scxh.android1503.dataparser.xml.Book;
import com.scxh.android1503.dataparser.xml.BookParser;
import com.scxh.android1503.dataparser.xml.PullBookParser;
import com.scxh.android1503.util.Logs;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XmlParserAndroidTest extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * xml解析成java对象
	 * 
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public void xmlparserToJavaObject() throws IOException,XmlPullParserException {
		InputStream is = getContext().getAssets().open("book.xml");
		
		BookParser parser = new PullBookParser();
		ArrayList<Book> books = (ArrayList<Book>) parser.parse(is);
		
		for (Book b : books) {
			Logs.v("name:" + b.getName());
		}

	}
	
	public void javaObjectParserToXml() throws IllegalArgumentException, IllegalStateException, IOException{
		ArrayList<Book> books = new ArrayList<Book>();
		Book book = new Book();
		book.setId(2001);
		book.setName("java基础");
		book.setPrice(32);
		books.add(book);
		
		book = new Book();
		book.setId(2002);
		book.setName("Anroid界面学习");
		book.setPrice(32);
		books.add(book);

		book = new Book();
		book.setId(2003);
		book.setName("动画学习");
		book.setPrice(12);
		books.add(book);
		
		BookParser parser = new PullBookParser();
		String xmlStr = parser.serialize(books);
		
		Logs.v("xml :"+xmlStr);
	}
}
