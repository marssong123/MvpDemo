package com.ssjj.androidmvpdemo.words;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ssjj.androidmvpdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OpenGLActivity extends AppCompatActivity {


    @Bind(R.id.gl_view)
    FGLView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        ButterKnife.bind(this);
        glView.setShape(TriangleColorFull.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }
}
