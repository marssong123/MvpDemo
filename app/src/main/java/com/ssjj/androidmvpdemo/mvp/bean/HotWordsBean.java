package com.ssjj.androidmvpdemo.mvp.bean;

import java.util.List;

/**
 * Created by songmars on 16/7/4.
 */
public class HotWordsBean extends BaseBean{

    /**
     * code : 1
     * msg : 获取成功
     * data : ["求BGM","主播你够了","233333","瞬间爆炸","你是要上天"]
     */

    private int code;
    private String msg;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
