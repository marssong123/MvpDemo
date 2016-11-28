package com.ssjj.androidmvpdemo.test;

/**
 * Created by songmars on 16/8/26.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.R;

public class Sample extends Activity {
    private TextView myText = null;
    private Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        myText = (TextView) findViewById(R.id.text1);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myText.setText("已点击");
            }
        });
    }

}
