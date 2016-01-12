package com.scxh.android1503.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class SuperStar implements Subject {
	private List<Observer> list = new ArrayList<Observer>();
	private String message;

	@Override
	public void addObserver(Observer o) {
		if (o == null) {
			throw new IllegalArgumentException("The observer is null.");
		}
		synchronized (list) {
			if (list.contains(o)) {
				throw new IllegalStateException("Observer " + o
						+ " is already registered.");
			}
			list.add(o);
		}

	}

	@Override
	public void removeObserver(Observer o) {
		if (o == null) {
			throw new IllegalArgumentException("The observer is null.");
		}
		synchronized (list) {
			int index = list.indexOf(o);
			if (index == -1) {
				throw new IllegalStateException("Observer " + o
						+ " was not registered.");
			}
			list.remove(index);
		}
	}

	/**
	 * Remove all registered observers.
	 */
	public void unregisterAll() {
		synchronized (list) {
			list.clear();
		}
	}

	@Override
	public void notification() {
		for (Observer o : list) {
			o.update(message);
		}
	}

	public void createMessage(String msg) {
		message = msg;
	}

}
