package com.ssjj.androidmvpdemo.rxjava;
/**
 * Created by songmars on 16/8/26.
 */


public interface ApiCallback<T> {

    void onSuccess(T bean);

    void onFailure(int code, String msg);

    void onCompleted();

}
