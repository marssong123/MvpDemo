package com.ssjj.androidmvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_record);
        setContentView(ViewStore.getInstance().getView(R.layout.activity_video_record ,VideoRecordActivity.this));
    }
}
