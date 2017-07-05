package com.ssjj.androidmvpdemo.ui;
/**
 * Created by songmars on 16/8/26.
 */
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.R;
import com.ssjj.androidmvpdemo.mvp.base.MvpActivity;
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.presenter.MainPresenter;
import com.ssjj.androidmvpdemo.mvp.view.MainView;
import com.ssjj.androidmvpdemo.ndk.MyJni;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.mProgressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //请求接口
        mvpPresenter.loadWords(10);
        text.setText(new MyJni().getString());
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(BaseBean bean) {
        //接口成功回调

        HotWordsBean hotWordsBean= (HotWordsBean) bean;
        String hotlist="";
        for(String hotword:hotWordsBean.getData()){
            hotlist=hotlist.concat(hotword+"\n");
        }
//        text.setText(hotlist);

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


}
