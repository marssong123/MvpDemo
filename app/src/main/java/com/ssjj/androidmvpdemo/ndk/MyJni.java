package com.ssjj.androidmvpdemo.ndk;

import android.util.Log;

/**
 * Created by songmars on 2017/7/5.
 */

public class MyJni {
private static final String TAG = "MyJni" ;
    static {
        System.loadLibrary("MyLibrary");
        Log.d(TAG, "static initializer1: ");
        Log.d(TAG, "static initializer2: ");
    }

    public  native String getString();
}
