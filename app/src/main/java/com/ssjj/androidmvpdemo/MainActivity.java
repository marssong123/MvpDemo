package com.ssjj.androidmvpdemo;
/**
 * Created by songmars on 16/8/26.
 */

import android.content.Intent;
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

    public static void main() {

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
        Toast.makeText(mActivity, "develop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    private void god1(){}

    private void god2(){}




}
