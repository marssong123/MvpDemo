package com.ssjj.androidmvpdemo;
/**
 * Created by songmars on 16/8/26.
 */

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.mvp.base.MvpActivity;
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.presenter.MainPresenter;
import com.ssjj.androidmvpdemo.mvp.view.MainView;
import com.ssjj.androidmvpdemo.ndk.MyJni;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, View.OnClickListener {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.mProgressBar)
    ProgressBar mProgressBar;
    ExecutorService executorService1;
    ExecutorService executorService2;
    ExecutorService executorService3;
    ExecutorService executorService4;
    private static final String TAG = "MainActivity";
    //    private static  final int count = Runtime.getRuntime().availableProcessors()*3 +2 ;
    private static final int count = 3;
    @Bind(R.id.btn_01)
    Button btn01;
    @Bind(R.id.btn_02)
    Button btn02;
    @Bind(R.id.btn_03)
    Button btn03;
    @Bind(R.id.btn_04)
    Button btn04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //请求接口
        mvpPresenter.loadWords(10);
        text.setText(new MyJni().getString());
        exeThreadPool();
    }


    private void exeThreadPool() {
        executorService1 = Executors.newSingleThreadExecutor();
        executorService2 = Executors.newCachedThreadPool();
        executorService3 = Executors.newFixedThreadPool(count);
        executorService4 = Executors.newScheduledThreadPool(count);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);


    }


    private void exeService(ExecutorService executorService) {

        for (int i = 0; i < 10; i++) {
            final int index = i;

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "Thread : " + Thread.currentThread().getId() + "aliveCount :" + Thread.activeCount() + "i:" + index);
                }
            });
        }

    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(BaseBean bean) {
        //接口成功回调

        HotWordsBean hotWordsBean = (HotWordsBean) bean;
        String hotlist = "";
        for (String hotword : hotWordsBean.getData()) {
            hotlist = hotlist.concat(hotword + "\n");
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);

    }

    @Override
    public void getVideoFail(String msg) {

    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }


    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        Point[] points1 = new Point[100];
        for (int j = 1; j < points.length; j++) {
            for (int i = 0; i < points.length - 1; j++) {

                if ((points[i].x - origin.x) * (points[i].x - origin.x) + (points[i].y - origin.y) * (points[i].y - origin.y) < (points[i + 1].x - origin.x) * (points[i + 1].x - origin.x) + (points[i + 1].y - origin.y) * (points[i + 1].x - origin.y)) {
                    Point t = points[i + 1];
                    points[i + 1] = points[i];
                    points[i] = t;
                } else if ((points[i].x - origin.x) * (points[i].x - origin.x) + (points[i].y - origin.y) * (points[i].y - origin.y) == (points[i + 1].x - origin.x) * (points[i + 1].x - origin.x) + (points[i + 1].y - origin.y) * (points[i + 1].x - origin.y)) {
                    if (points[i].x < points[i + 1].x) {
                        Point t = points[i + 1];
                        points[i + 1] = points[i];
                        points[i] = t;
                    } else if (points[i].x == points[i + 1].x) {
                        if (points[i].y < points[i + 1].y) {
                            Point t = points[i + 1];
                            points[i + 1] = points[i];
                            points[i] = t;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < k; i++) {
            points1[i] = points[i];
        }
        return points1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                exeService(executorService1);
                break;
            case R.id.btn_02:
                exeService(executorService2);

                break;
            case R.id.btn_03:
                exeService(executorService3);

                break;
            case R.id.btn_04:

                exeService(executorService4);

                break;
        }
    }




}
