package com.ssjj.androidmvpdemo.words;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by songyu on 2018/5/4.
 */

public class MyView extends Button {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean back = super.dispatchTouchEvent(event) ;
        Log.e("touch", "MyView dispatchTouchEvent: "+ back );
        return back;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean back = super.onTouchEvent(event) ;

        Log.e("touch", "MyView onTouchEvent: "+ back );
        return back;
    }
}
