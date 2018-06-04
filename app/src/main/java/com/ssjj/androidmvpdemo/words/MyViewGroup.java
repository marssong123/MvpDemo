package com.ssjj.androidmvpdemo.words;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by songyu on 2018/5/4.
 */

public class MyViewGroup extends LinearLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean back = super.dispatchTouchEvent(event);
        Log.e("touch", "MyViewGroup dispatchTouchEvent: " + back);
        return back;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean back = super.onInterceptTouchEvent(ev);

        Log.e("touch", "MyViewGroup dispatchTouchEvent: " + back);
        return back;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean back = super.onTouchEvent(event);

        Log.e("touch", "MyViewGroup onTouchEvent: " + back);
        return back;
    }
}
