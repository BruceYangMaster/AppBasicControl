package com.scxh.android1503.dataparser.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class PullBookParser implements BookParser {

	@Override
	public List<Book> parse(InputStream is) throws XmlPullParserException, IOException {
		ArrayList<Book> books = null;
		Book book = null;
		
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(is, "UTF-8");
		int type = pullParser.getEventType();
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_DOCUMENT:
				books = new ArrayList<Book>();
				break;
			case XmlPullParser.START_TAG:
				if (pullParser.getName().equals("book")) {
					book = new Book();
				} else if (pullParser.getName().equals("id")) {
					type = pullParser.next();
					String id = pullParser.getText();
					book.setId(Integer.parseInt(id));
				} else if (pullParser.getName().equals("name")) {
					type = pullParser.next();
					String name = pullParser.getText();
					book.setName(name);
				} else if (pullParser.getName().equals("price")) {
					type = pullParser.next();
					String price = pullParser.getText();
					book.setPrice(Double.parseDouble(price));
				}
				break;
			case XmlPullParser.END_TAG:
				if (pullParser.getName().equals("book")) {
					books.add(book);
					book = null;
				}
				break;
			}
			type = pullParser.next();

		}
		return books;
	}

	@Override
	public String serialize(List<Book> books) throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		serializer.setOutput(writer);
		
		serializer.startDocument("UTF-8", true);
		serializer.startTag("", "books");
		
		for(Book b : books){
			serializer.startTag("", "book");
			
			serializer.startTag("", "id");
			serializer.text(String.valueOf(b.getId()));
			serializer.endTag("", "id");
			
			serializer.startTag("", "name");
			serializer.text(b.getName());
			serializer.endTag("", "name");
			
			serializer.startTag("", "price");
			serializer.text(String.valueOf(b.getPrice()));
			serializer.endTag("", "price");

			serializer.endTag("", "book");
		}
		
		serializer.endTag("", "books");
		serializer.endDocument();
		
		String xmlStr = writer.toString();
		
		return xmlStr;
	}

}
