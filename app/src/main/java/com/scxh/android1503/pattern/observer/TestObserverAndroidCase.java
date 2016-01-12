package com.scxh.android1503.pattern.observer;

import android.test.AndroidTestCase;

public class TestObserverAndroidCase extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testObserver() {
		Observer zhanshang = new ZhangshanObserver();
		Observer lishi = new LishiObserver();
		Observer wanger = new WangerObserver();
		
		SuperStar star = new SuperStar();
		
		star.addObserver(zhanshang);
		star.addObserver(lishi);
		star.addObserver(wanger);
		//star.addObserver(wanger);
		
		star.removeObserver(lishi);
		
		star.createMessage("明天我结婚，大家来捧场 ");
		
		star.notification();
	}
}
