package com.ssjj.androidmvpdemo.mvp.view;
/**
 * Created by songmars on 16/8/26.
 */
import com.ssjj.androidmvpdemo.mvp.bean.BaseBean;

/**
 * 处理业务的方法
 */
public interface MainView {

    void getDataSuccess(BaseBean bean);

    void getDataFail(String msg);


    void getVideoFail(String msg);

    void showLoading();

    void hideLoading();
}
