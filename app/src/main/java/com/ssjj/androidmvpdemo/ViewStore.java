package com.ssjj.androidmvpdemo;

import android.content.Context;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ssjj.androidmvpdemo.words.NamedThread;


/**
 * @author wangfang on 2018/4/4.
 * @description
 */
public class ViewStore {

    private static final String TAG = "ViewStore";

    private SparseArray<View> mStoreView = new SparseArray<>(10);

    private ViewStore() {}

    private static ViewStore sInstance;

    private final Object mLock = new Object();

    private static final long KEEP_TIME = 10 * 60 * 1000;

    private static final int MSG_INFLATE = 1;
    private static final int MSG_DESTROY = 2;
    private Context mContext ;

    private NamedThread mViewStoreThread = new NamedThread("ViewStore") {
        @Override
        public void handleMessage(Message msg) {
            int id;
            switch (msg.what) {
                case MSG_INFLATE:
                    id = (int) msg.obj;
                    synchronized (mLock) {
                        long startTime = System.currentTimeMillis();
                        LayoutInflater inflater = LayoutInflater.from(mContext.getApplicationContext());
                        View view = inflater.inflate(id, null);
                        mStoreView.put(id, view);
                        Log.e(TAG, "inflate : " + view + " cost : " + (System.currentTimeMillis() - startTime));
                    }
                    break;
                case MSG_DESTROY:
                    id = (int) msg.obj;
                    synchronized (mLock) {
                        Log.e(TAG, "remove : " + id);
                        mStoreView.remove(id);
                    }
                    break;
            }
        }
    };



    public static ViewStore getInstance() {
        if (sInstance == null) {
            synchronized (ViewStore.class) {
                if (sInstance == null) {
                    sInstance = new ViewStore();
                }
            }
        }
        return sInstance;
    }

    public void inflate(@LayoutRes int id , Context context) {
        mContext = context ;
        if (mStoreView.indexOfKey(id) != -1) {
            Log.e(TAG, "No need inflate");
            return;
        }
        mViewStoreThread.sendMessage(MSG_INFLATE, id);
    }

    public View getView(@LayoutRes int id ,Context context) {
        mContext = context ;
        mViewStoreThread.removeMessages(MSG_DESTROY, id);
        synchronized (mLock) {
            View view = mStoreView.get(id);
            if (view == null) {
                Log.e(TAG, "getView need inflate");
                LayoutInflater inflater = LayoutInflater.from(mContext.getApplicationContext());
                view = inflater.inflate(id, null);
                mStoreView.put(id, view);
            } else {
                Log.e(TAG, "getView NO need inflate");
                aloneView(view);
            }
            Log.e(TAG, "getView : " + id + " " + view);
            return view;
        }
    }

    private void aloneView(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            Log.e(TAG, "aloneView");
            ((ViewGroup) parent).removeView(view);
        }
    }

    public void destroyAndRebuild(@LayoutRes int id) {
        View view = mStoreView.get(id);
        if (view != null) {
            aloneView(view);
            mStoreView.remove(id);
            mViewStoreThread.sendMessage(MSG_INFLATE, id);
            mViewStoreThread.sendMessage(MSG_DESTROY, id, KEEP_TIME);
        }
    }

    public void destroy(@LayoutRes int id) {
        View view = mStoreView.get(id);
        if (view != null) {
            aloneView(view);
            mViewStoreThread.sendMessage(MSG_DESTROY, id, KEEP_TIME);
        }
    }

}
