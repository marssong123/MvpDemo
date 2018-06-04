package com.ssjj.androidmvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class VideoRecordActivity extends AppCompatActivity {
    private static final String TAG = "VideoRecordActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_record);
        setContentView(ViewStore.getInstance().getView(R.layout.activity_video_record ,VideoRecordActivity.this));
        Log.e(TAG, "B onCreate: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "B onStart: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "B onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "B onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "B onDestroy: ");

    }
}
