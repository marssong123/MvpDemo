package com.ssjj.androidmvpdemo.ui;

import android.util.Log;

/**
 * Created by songyu on 2017/11/15.
 */

public class Demo {
    private static final String TAG = "MainActivity" ;
    private  int a=1;
    public  void  print2(){
        Log.e(TAG, "print2: 开始");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "print2: 结束");

        new Thread(new Runnable() {
            @Override
            public void run() {
                 a=2;
            }
        }).start();

    }

}
