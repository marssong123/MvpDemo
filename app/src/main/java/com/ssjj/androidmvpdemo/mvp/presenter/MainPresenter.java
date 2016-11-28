package com.ssjj.androidmvpdemo.mvp.presenter;
/**
 * Created by songmars on 16/8/26.
 */
import com.ssjj.androidmvpdemo.mvp.base.BasePresenter;
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;
import com.ssjj.androidmvpdemo.mvp.view.MainView;
import com.ssjj.androidmvpdemo.rxjava.ApiCallback;
import com.ssjj.androidmvpdemo.rxjava.SubscriberCallBack;

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void loadWords(int limit) {
        addSubscription(apiStores.loadHotWords(limit), new SubscriberCallBack<>(new ApiCallback<HotWordsBean>() {
            @Override
            public void onSuccess(HotWordsBean bean) {
                mvpView.getDataSuccess(bean);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail(msg);
            }

            @Override
            public void onCompleted() {
                mvpView.hideLoading();

            }
        }));
    }



}
