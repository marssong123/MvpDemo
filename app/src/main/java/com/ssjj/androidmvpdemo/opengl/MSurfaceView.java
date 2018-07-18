package com.ssjj.androidmvpdemo.opengl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Vector;

/**
 * Created by songyu on 2018/6/4.
 */

public class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Context mContext;
    private Paint mPaint;
    boolean isRunning = false;
    private int mWidth;
    private int mHeight;
    private Matrix mMatrix;


    private Vector<Heart> heartList = new Vector<>();


    public MSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);


    }

    public MSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        getHolder().addCallback(this);
        //设置背景透明
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        //----
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
        mMatrix = new Matrix();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;

        for (Heart heart : heartList) {
            heart.clearBitmap();
        }
    }

    @Override
    public void run() {

        while (isRunning) {
            Canvas canvas = null;
            try {
                canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    drawHeart(canvas);
                }

            } catch (Exception e) {

            } finally {
                if (canvas != null) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }


        }

    }

    public void addHeart(Context context, int type) {
        Heart heart = new Heart(context, type);
        initHeart(heart);
        heartList.add(heart);
    }

    private void initHeart(Heart heart) {
        heart.initStartAndEnd(mWidth, mHeight);
        heart.initControl(mWidth, mHeight);
        heart.initSpeed();

    }

    private void drawHeart(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);


        for (Heart heart : heartList) {
            mMatrix.setTranslate(0, 0);
            //位移到x,y
            mMatrix.postTranslate(heart.getX(), heart.getY());
            canvas.drawBitmap(heart.getBitmap(), mMatrix, mPaint);
            //计算时间
            if (heart.getT() < 1) {
                heart.setT(heart.getT() + heart.getSpeed());
                //计算下次画的时候，x，y坐标
                handleBezierXY(heart);
            } else {
                removeHeart(heart);
            }
        }
    }

    /**
     * 计算实时的点坐标
     *
     * @param heart
     */
    private void handleBezierXY(Heart heart) {
        //三阶贝塞尔曲线函数
        //x = (float) (Math.pow((1 - t), 3) * start.x + 3 * t * Math.pow((1 - t), 2) * control1.x + 3 * Math.pow(t, 2) * (1 - t) * control2.x + Math.pow(t, 3) * end.x);
        //y = (float) (Math.pow((1 - t), 3) * start.y + 3 * t * Math.pow((1 - t), 2) * control1.y + 3 * Math.pow(t, 2) * (1 - t) * control2.y + Math.pow(t, 3) * end.y);
        float x = (float) (Math.pow((1 - heart.getT()), 3) * heart.getStartX() +
                3 * heart.getT() * Math.pow((1 - heart.getT()), 2) * heart.getControl1X() +
                3 * Math.pow(heart.getT(), 2) * (1 - heart.getT()) * heart.getControl2X() +
                Math.pow(heart.getT(), 3) * heart.getEndX());

        float y = (float) (Math.pow((1 - heart.getT()), 3) * heart.getStartY() +
                3 * heart.getT() * Math.pow((1 - heart.getT()), 2) * heart.getControl1Y() +
                3 * Math.pow(heart.getT(), 2) * (1 - heart.getT()) * heart.getControl2Y() +
                Math.pow(heart.getT(), 3) * heart.getEndY());

        heart.setX(x);
        heart.setY(y);
    }


    private void removeHeart(Heart heart){

        heartList.remove(heart);
    }

}
