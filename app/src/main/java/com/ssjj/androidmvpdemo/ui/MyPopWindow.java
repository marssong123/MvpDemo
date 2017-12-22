package com.ssjj.androidmvpdemo.ui;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.ssjj.androidmvpdemo.R;

/**
 * Created by songyu on 2017/11/17.
 */

public class MyPopWindow extends PopupWindow {

    private Context mContext;
    private float mShowAlpha = 0.88f;
    private View root;

    public MyPopWindow(Context context) {
        super(context);
        mContext = context;
        initBasePopupWindow();
    }


    /**
     * 初始化BasePopupWindow的一些信息
     */
    private void initBasePopupWindow() {
//        setAnimationStyle(R.style.popwin_anim_style);
//        setAnimationStyle(android.R.style.Animation_Dialog);
//        setAnimationStyle(android.R.style.Animation_InputMethod);
        setHeight(200);
        setWidth(200);
        setOutsideTouchable(true);  //默认设置outside点击无响应
        setFocusable(true);
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);

    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        setOutsideTouchable(isOutsideTouchable());
    }


    @Override
    public void setContentView(View contentView) {
        if (contentView != null) {
            root = contentView;
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            super.setContentView(contentView);
            addKeyListener(contentView);
        }
    }

    public Context getContext() {
        return mContext;
    }


    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        AnimatorInflater.loadAnimator(mContext,R.animator.animator_record_button_show).start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        AnimatorInflater.loadAnimator(mContext,R.animator.animator_record_button_hide).start();
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     */
    private ValueAnimator showAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, mShowAlpha);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                setWindowBackgroundAlpha(alpha);
            }
        });
        animator.setDuration(360);
        return animator;
    }



    /**
     * 窗口隐藏，窗口背景透明度渐变动画
     */
    private ValueAnimator dismissAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(mShowAlpha, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                setWindowBackgroundAlpha(alpha);
            }
        });
        animator.setDuration(320);


        return animator;
    }

    /**
     * 为窗体添加outside点击事件
     */
    private void addKeyListener(View contentView) {
        if (contentView != null) {
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);
            contentView.setOnKeyListener(new View.OnKeyListener() {

                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            dismiss();
                            return true;
                        default:
                            break;
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 控制窗口背景的不透明度
     */
    private void setWindowBackgroundAlpha(float alpha) {
        Window window = ((Activity) getContext()).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        window.setAttributes(layoutParams);
    }
}
