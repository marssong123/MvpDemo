package com.ssjj.androidmvpdemo;

import android.app.Application;


/**
 * Created by songyu on 2018/4/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);

    }
}
