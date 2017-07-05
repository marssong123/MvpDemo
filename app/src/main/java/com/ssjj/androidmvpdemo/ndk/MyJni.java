package com.ssjj.androidmvpdemo.ndk;

/**
 * Created by songmars on 2017/7/5.
 */

public class MyJni {

    static {
        System.loadLibrary("MyLibrary");
    }

    public  native String getString();
}
