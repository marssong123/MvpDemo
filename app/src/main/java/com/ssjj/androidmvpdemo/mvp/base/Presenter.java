package com.ssjj.androidmvpdemo.mvp.base;
/**
 * Created by songmars on 16/8/26.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
