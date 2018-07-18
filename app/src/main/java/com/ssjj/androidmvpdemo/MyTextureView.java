package com.ssjj.androidmvpdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.TextureView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by songyu on 2018/6/25.
 */

public class MyTextureView extends TextureView {

    private Camera mCamera;


    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mCamera =Camera.open();
        this.setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                try {
                    mCamera.setPreviewTexture(surface);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null ;
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }


    public void take(){
        if(mCamera !=null){
            mCamera.takePicture(null,null,mPictureCallBack);
        }


    }

    Camera.PictureCallback mPictureCallBack =new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            mCamera.stopPreview();
            new PictureTask(data).save();
        }
    };

    private class PictureTask implements  Runnable{

        private byte[] buffer;

        public PictureTask(byte[] buffer) {
            this.buffer = buffer;
        }

        public void save(){
            new  Thread(this).start();
        }

        @Override
        public void run() {
            try {
                File file =new File(Environment.getExternalStorageDirectory(),"MY_TEST.png");

                file.createNewFile();
                FileOutputStream os = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(os);

                Bitmap bitmap = BitmapFactory.decodeByteArray(buffer,0,buffer.length);
                bitmap.compress(Bitmap.CompressFormat.PNG,100 ,bos);

                bos.flush();
                bos.close();
                os.close();

                mCamera.startPreview();




            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
