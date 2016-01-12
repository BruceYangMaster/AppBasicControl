package com.scxh.android1503;

import android.app.Application;

/**
 * 1.全局初始化操作
 * 2.定义的变量是全局变量，每个activity内都可以访问  (ScxhApp) getApplication();
 */
public class ScxhApp extends Application {
    public String appName = "android1503";

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
