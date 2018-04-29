package com.ssjj.androidmvpdemo.words;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * @author wangfang on 2017/12/6.
 * description 便捷创建其他线程，主要提供removeMessages的方法
 */

public abstract class NamedThread {
    private HandlerThread mThread;
    private Handler mHandler;
    private static int sIndex = 0;

    public NamedThread(String name) {
        mThread = new HandlerThread(name + "_" + sIndex++);
        mThread.start();
        mHandler = new Handler(mThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                NamedThread.this.handleMessage(msg);
            }
        };
    }

    public abstract void handleMessage(Message msg);

    public void removeMessages(int what) {
        mHandler.removeMessages(what);
    }

    public void removeMessages(int what, Object obj) {
        mHandler.removeMessages(what, obj);
    }

    public void sendMessage(int what, Object obj) {
        mHandler.sendMessage(mHandler.obtainMessage(what, obj));
    }

    public void sendEmptyMessage(int what) {
        mHandler.sendEmptyMessage(what);
    }

    public void sendMessage(int what, Object obj, long delay) {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(what, obj), delay);
    }

    public void destroy() {
        mHandler.removeCallbacksAndMessages(null);
        mThread.quit();
    }
}
