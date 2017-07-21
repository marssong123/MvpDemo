package com.ssjj.androidmvpdemo.ui;
/**
 * Created by songmars on 16/8/26.
 */

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        text.setText(new MyJni().getString());
    }

    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        Point[] points1 = new Point[100];
        for (int j = 1; j < points.length; j++) {
            for (int i = 0; i < points.length - 1; j++) {

                if ((points[i].x-origin.x) *(points[i].x-origin.x) + (points[i].y-origin.y)*(points[i].y-origin.y) < (points[i + 1].x-origin.x) *(points[i + 1].x-origin.x) + (points[i + 1].y-origin.y) *(points[i + 1].x-origin.y)) {
                    Point t = points[i + 1];
                    points[i + 1] = points[i];
                    points[i] = t;
                } else if ((points[i].x-origin.x) *(points[i].x-origin.x) + (points[i].y-origin.y)*(points[i].y-origin.y) == (points[i + 1].x-origin.x) *(points[i + 1].x-origin.x) + (points[i + 1].y-origin.y) *(points[i + 1].x-origin.y)) {
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
//        text.setText(hotlist);
        Toast.makeText(mActivity, "request success", Toast.LENGTH_SHORT).show();
        Toast.makeText(mActivity, "merge to master", Toast.LENGTH_SHORT).show();

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
