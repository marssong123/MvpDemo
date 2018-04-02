package com.ssjj.androidmvpdemo.now;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicActivity extends AppCompatActivity {


    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.waveView)
    RateWaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private void foo1() {

    }

    private void foo2() {

    }

    private void foo3() {

    }

    private void foo4() {

    }

    private void foo5() {

    }

    private void foo6() {

    }


}
