package com.ssjj.androidmvpdemo.test;

import android.content.Intent;
import android.os.SystemClock;
import android.test.InstrumentationTestCase;
import android.widget.Button;
import android.widget.TextView;

import com.ssjj.androidmvpdemo.R;

/**
 * Created by songmars on 16/8/26.
 */
public class ExampleTest extends InstrumentationTestCase {

    private Sample sample = null;
    private Button button = null;
    private TextView text = null;
    @Override
    protected void setUp() throws Exception {
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setClassName("com.ssjj.androidmvpdemo", Sample.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sample = (Sample) getInstrumentation().startActivitySync(intent);
        text = (TextView) sample.findViewById(R.id.text1);
        button = (Button) sample.findViewById(R.id.button1);

    }

    @Override
    protected void tearDown()  {
        sample.finish();
        try {
            super.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * activity功能测试
    */
    public void testActivity() throws Exception {
        SystemClock.sleep(1500);
        getInstrumentation().runOnMainSync(new PerformClick(button));
        SystemClock.sleep(3000);
        assertEquals("已点击", text.getText().toString());
    }

    /*
     * 模拟按钮点击的接口
     */
    private class PerformClick implements Runnable {
        Button btn;
        public PerformClick(Button button) {
            btn = button;
        }

        public void run() {
            btn.performClick();
        }
    }


    public void testAdd() throws Exception {
        Calculator calculator=new Calculator();

        final int reality = calculator.add(2,3);
        final int expected = 5;

        assertEquals(expected, reality);
    }


    public void testMulpily() throws Exception {
        Calculator calculator=new Calculator();

        final int reality = calculator.mulpily(2,3);
        final int expected = 5;

        assertEquals(expected, reality);
    }





}
