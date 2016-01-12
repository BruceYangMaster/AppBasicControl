package com.scxh.android1503.pattern.observer;
/**
 * 被观察者，
 * 主题对象 
 *
 */
public interface Subject {
	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void notification();
}
