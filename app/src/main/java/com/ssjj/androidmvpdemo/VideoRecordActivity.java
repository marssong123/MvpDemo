package com.ssjj.androidmvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoRecordActivity extends AppCompatActivity {
    private static final String TAG = "VideoRecordActivity";
    @Bind(R.id.my_texture_view)
    MyTextureView myTextureView;
    @Bind(R.id.btn_take)
    Button btnTake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        ButterKnife.bind(this);
//        setContentView(ViewStore.getInstance().getView(R.layout.activity_video_record ,VideoRecordActivity.this));
        Log.e(TAG, "B onCreate: ");
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTextureView.take();
            }
        });
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
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "B onResume: ");

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
