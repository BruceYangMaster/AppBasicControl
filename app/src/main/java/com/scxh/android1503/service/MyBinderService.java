package com.scxh.android1503.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.scxh.android1503.util.Logs;

/**
 * 实现服务Service与组件Activity之间交互
 * 启动服务方式 使用 BindService
 * 
 * 实现步骤：
 * 1.服务类
 *   1>定义交互接口
 *   2>定义交互类继续Binder实现交互接口
 *   3>实现交互类 作为 onBinder方法返回值 ,返回给交互组件Activity。
 *
 */
public class MyBinderService extends Service {
	private int count;
	private ServerCountBinder countBinder = new ServerCountBinder();

	
	public interface IBCount {
		public void setCount(int count);

		public int getCount();
	}

	public class ServerCountBinder extends Binder implements IBCount {

		@Override
		public void setCount(int _count) {
			count = _count;
		}

		@Override
		public int getCount() {
			return count;
		}

	}

	@Override
	public void onCreate() {
		super.onCreate();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
					Logs.d("count :"+count);
				}
			}
		}).start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Logs.v("onBind >>>  countBinder :"+countBinder);
		return countBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
