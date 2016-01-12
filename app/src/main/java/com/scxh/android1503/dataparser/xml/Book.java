package com.scxh.android1503.dataparser.xml;

public class Book {
	private int id;        //编号
	private String name;   //书名
	private double price;  //价格
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
