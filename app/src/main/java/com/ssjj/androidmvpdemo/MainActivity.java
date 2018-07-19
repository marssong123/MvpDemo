package com.ssjj.androidmvpdemo;
/**
 * Created by songmars on 16/8/26.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ssjj.androidmvpdemo.anrmonitor.AnrWatchDog;
import com.ssjj.androidmvpdemo.mvp.base.MvpActivity;
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.presenter.MainPresenter;
import com.ssjj.androidmvpdemo.mvp.view.MainView;
import com.ssjj.androidmvpdemo.words.MyView;
import com.ssjj.androidmvpdemo.words.MyViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    private static final String TAG = "VideoRecordActivity";
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.btn_my)
    MyView btnMy;
    @Bind(R.id.btn_my1)
    Button btnMy1;
    @Bind(R.id.btn_my2)
    Button btnMy2;
    @Bind(R.id.btn_my3)
    Button btnMy3;
    @Bind(R.id.btn_my4)
    Button btnMy4;
    @Bind(R.id.rl_root)
    MyViewGroup rlRoot;
    @Bind(R.id.btn_my5)
    Button btnMy5;
    @Bind(R.id.my_simple_view)
    SimpleDraweeView mySimpleView;


    private Object lock = new Object();

    AnrWatchDog anrWatchDog;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        handler = new Handler();
        anrWatchDog = new AnrWatchDog.Builder().timeout(30000).ignoreDebugger(true).anrListener(new AnrWatchDog.AnrListener() {
            @Override
            public void onAnrHappened(String stackTraceInfo) {
                Log.w(TAG, "anr warning:" + stackTraceInfo);
            }
        }).build();
        anrWatchDog.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 3000);
            }
        }, 3000);

        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoRecordActivity.class);
                startActivity(intent);
//

            }
        });

        btnMy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (lock) {
                            MainPresenter mainPresenter = new MainPresenter(MainActivity.this);
                            mainPresenter.loadWords(10);
                            Log.e(TAG, "run: loadWords");

                        }
                    }
                });

            }
        });

        Log.e(TAG, "A onCreate: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "A onStart: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "A onPause: ");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "A onStop: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "A onResume: ");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "A onDestroy: ");
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        @Override
        public void dispatchMessage(Message msg) {
            Log.i(TAG, "dispatchMessage: " + msg.arg1);
            super.dispatchMessage(msg);
        }
    };


    public void show(String msg) {
        Log.e(TAG, "run: 3");

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean back = super.dispatchTouchEvent(event);
        Log.e("touch", "MainActivity dispatchTouchEvent: " + back);
        return back;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean back = super.onTouchEvent(event);
        Log.e("touch", "MainActivity onTouchEvent: " + back);
        return back;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(BaseBean bean) {
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
        Toast.makeText(mActivity, "develop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

}
