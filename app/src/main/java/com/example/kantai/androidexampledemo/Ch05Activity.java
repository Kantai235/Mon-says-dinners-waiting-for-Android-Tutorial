package com.example.kantai.androidexampledemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class Ch05Activity extends AppCompatActivity {

    private float touchX;
    private float touchY;
    private int tvWidth = ActionBar.LayoutParams.WRAP_CONTENT;
    private int tvHeight = ActionBar.LayoutParams.WRAP_CONTENT;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch05);

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);

        //退出時使用
        getWindow().setExitTransition(explode);
        //第一次進入時使用
        getWindow().setEnterTransition(explode);
        //再次進入時使用
        getWindow().setReenterTransition(explode);

        // You should also override your application class with the following.
        TypefaceProvider.registerDefaultIconSets();

        this.textView = (TextView) findViewById(R.id.Ch05_textView);
    }

    // 利用 MotionEvent 處理觸控程序
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.touchX = event.getX();       // 觸控的 X 軸位置
        this.touchY = event.getY() - 50;  // 觸控的 Y 軸位置

        // 判斷觸控動作
        switch (event.getAction()) {
            // 按下
            case MotionEvent.ACTION_DOWN:
                this.textView.setText(
                        "偵測到點擊動作(ACTION_DOWN)\n" +
                        "X Point : " + touchX + "\n" +
                        "Y Point : " + touchY + "\n"
                );
                break;

            // 拖曳移動
            case MotionEvent.ACTION_MOVE:
                textView.setText(
                        "偵測到拖曳移動動作(ACTION_MOVE)\n" +
                        "X Point : " + touchX + "\n" +
                        "Y Point : " + touchY + "\n"
                );
                break;

            // 放開
            case MotionEvent.ACTION_UP:
                textView.setText(
                        "偵測到放開動作(ACTION_UP)\n" +
                        "X Point : " + touchX + "\n" +
                        "Y Point : " + touchY + "\n"
                );
                break;
        }

        // TODO Auto-generated method stub
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(Ch05Activity.this, MainActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            overridePendingTransition(R.transition.zoomin, R.transition.zoomout);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
