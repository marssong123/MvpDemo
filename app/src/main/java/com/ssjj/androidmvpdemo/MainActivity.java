package com.ssjj.androidmvpdemo;
/**
 * Created by songmars on 16/8/26.
 */

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ssjj.androidmvpdemo.mvp.base.MvpActivity;
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.presenter.MainPresenter;
import com.ssjj.androidmvpdemo.mvp.view.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    }

    public static void main() {

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
