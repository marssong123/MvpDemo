package com.ssjj.androidmvpdemo.retrofit;
/**
 * Created by songmars on 16/8/26.
 */
import com.ssjj.androidmvpdemo.mvp.bean.HotWordsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://api-test.zhibo.me4399.com";


//    加载热词列表
    @GET("/service/chat/hotwords")
    Observable<HotWordsBean> loadHotWords(@Query("limit") int limit);



}
