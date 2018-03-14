package com.ssjj.androidmvpdemo;
/**
 * Created by songmars on 16/8/26.
 */

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ssjj.androidmvpdemo.mvp.base.MvpActivity;
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.presenter.MainPresenter;
import com.ssjj.androidmvpdemo.mvp.view.MainView;
import com.ssjj.androidmvpdemo.ndk.MyJni;
import com.ssjj.androidmvpdemo.now.MusicActivity;
import com.ssjj.androidmvpdemo.ui.MyPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    private static final String TAG = "MainActivity";
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;
    @Bind(R.id.btn_my)
    Button btnMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        //请求接口
        mvpPresenter.loadWords(10);

        final View result = LayoutInflater.from(this).inflate(R.layout.test, null, false);

        final MyPopWindow myPopWindow = new MyPopWindow(MainActivity.this);
        myPopWindow.setContentView(result);
        text1.setText(new MyJni().getString());
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPopWindow.showAsDropDown(view,-200,-200);
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);

                while (true){
                    Toast toast =new Toast(MainActivity.this);
                    toast.setView(new View(MainActivity.this));
                    toast.show();
                }


            }
        });


    }

    private void lock() {
        final Demo demo = new Demo();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 0; i < 3; i++) {
            Thread thread1 = new Thread(new Test1(demo, i, lock, condition));
            thread1.start();
        }
    }

    public static void main() {

    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void rxJavaMethod() {
        List<String> list = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onCompleted();
            }
        }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return file.getName().endsWith(".png");
            }
        }).map(new Func1<File, String>() {
            @Override
            public String call(File file) {
                return file.getName();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onCompleted: ");

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });


    }


    private class Test1 implements Runnable {

        Demo demo;
        int i;
        ReentrantLock lock;
        Condition condition;

        public Test1(Demo demo, int i, ReentrantLock lock, Condition condition) {
            this.demo = demo;
            this.i = i;
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            demo.print2(i, lock, condition);
        }
    }

    class Demo {

        void print2(int i, ReentrantLock lock, Condition condition) {
            lock.lock();

            Log.e(TAG, "print: 开始" + i);

            try {
                if (i == 1) {
                    condition.await();
                }
                if (i == 2) {
                    condition.signal();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Log.e(TAG, "print: 结束" + i);
                lock.unlock();
            }

        }
    }

    private ArrayList<File> getFile(String s) {
        ArrayList<File> files = new ArrayList<>();
        return files;

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

    public int findMin(int[] arr) {

        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max1 < arr[i]) {
                max1 = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (max2 < arr[i]) {
                if (arr[i] != max1) {
                    max2 = arr[i];
                }
            }
        }
        Log.d(TAG, "findMin1: " + max1);
        Log.d(TAG, "findMin2: " + max2);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != max1 && arr[i] != max2) {
                if (max1 >= max2) {
                    max2 += arr[i];
                } else {
                    max1 += arr[i];
                }
            }
        }

        return Math.abs(max1 - max2);

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
    }

    @Override
    public void hideLoading() {
    }


}
