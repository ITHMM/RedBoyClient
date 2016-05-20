package com.itheima.redboyclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.itheima.redboyclient.R;

/**
 * Created by xiaoyan on 2016/4/3.
 * 欢迎界面，默认三秒后进入主界面，点击可立即进入无需等待
 */
public class WelcomeActivity extends Activity {

    private  Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount() + "");
                handler.sendEmptyMessageDelayed(0, 1000);
                 animation.reset();
                textView.startAnimation(animation);
            }
        }
    };

    private static final long TIME = 3000;

    private boolean isEnter = false;
    private Thread welcomeThread = new Thread(new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(TIME);
            enter();
        }
    });

    private int getCount() {
        count--;
        if (count == 0) {
            if (!isEnter) {
                isEnter = true;
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return count;
    }

    private void enter() {
        if (!isEnter) {
            isEnter = true;
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private TextView textView;
    private int count = 3;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_activity);
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        textView.startAnimation(animation);
        welcomeThread.start();
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        enter();
        return true;
    }

}
