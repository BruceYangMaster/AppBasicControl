package com.scxh.android1503.dataparser.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

public interface BookParser {
	/**
	 * XML字符串流转对象
	 * @param is
	 * @return
	 */
	public List<Book> parse(InputStream is) throws XmlPullParserException , IOException;
	/**
	 * 对象转xml字符串
	 * @param books
	 * @return
	 */
	public String serialize(List<Book> books)throws IllegalArgumentException, IllegalStateException, IOException ;
}
