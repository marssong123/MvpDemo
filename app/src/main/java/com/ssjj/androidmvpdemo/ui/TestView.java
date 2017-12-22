package com.ssjj.androidmvpdemo.ui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.ssjj.androidmvpdemo.R;

/**
 * @author songyu
 * @date 2017/11/30
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        Paint paint =new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        Point point =new Point();



    }








}
