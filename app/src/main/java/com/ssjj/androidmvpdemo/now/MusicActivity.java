package com.ssjj.androidmvpdemo.now;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicActivity extends AppCompatActivity {


    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.mProgressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.btn_01)
    Button btn01;
    @Bind(R.id.btn_02)
    Button btn02;
    @Bind(R.id.btn_03)
    Button btn03;
    @Bind(R.id.btn_04)
    Button btn04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
   private void newTest(){

   }
   private void newTest1(){

   }

   private void newTest2(){

   }




}
