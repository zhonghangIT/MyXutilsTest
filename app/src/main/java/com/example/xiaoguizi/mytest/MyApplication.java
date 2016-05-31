package com.example.xiaoguizi.mytest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by xiaoguizi on 16/5/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
