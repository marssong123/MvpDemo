/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ssjj.androidmvpdemo.opengl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ssjj.androidmvpdemo.R;
import com.ssjj.androidmvpdemo.util.RandomUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * There is not much here, but at the bottom, it setups immersive Mode, so that only the app
 * shows, and the use needs to swipe up from the bottom to get the navigation buttons to appear.
 */

public class SurfaceActivity extends Activity {

    @Bind(R.id.my_surface)
    MSurfaceView mySurface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface);
        ButterKnife.bind(this);

        mySurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SurfaceActivity.this, "heart", Toast.LENGTH_SHORT).show();
                int type = RandomUtils.getRandom(0, 6);
                mySurface.addHeart(SurfaceActivity.this, type);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10000 ; i++) {
                    try {
                        Thread.sleep(200);
                        Toast.makeText(SurfaceActivity.this, "heart", Toast.LENGTH_SHORT).show();
                        int type = RandomUtils.getRandom(0, 6);
                        mySurface.addHeart(SurfaceActivity.this, type);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}