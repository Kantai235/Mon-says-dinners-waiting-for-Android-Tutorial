package com.example.kantai.androidexampledemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.ArrayList;
import java.util.List;

public class Ch06Activity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    /**
     * 注意：這邊需要創建「手勢資料庫」
     * 請參考 https://books.google.com.tw/books?id=0kxBCgAAQBAJ&pg=SA4-PA31&lpg=SA4-PA31&dq=手勢資料庫+android+studio&source=bl&ots=UdsGM8LyZf&sig=ViK0Qv3MBDXPxVIRLLnxRDIEcqs&hl=zh-TW&sa=X&ved=0ahUKEwiJ2KCo0sXPAhVGX5QKHQf-ANIQ6AEIHzAB#v=onepage&q=手勢資料庫%20android%20studio&f=falsehttps://books.google.com.tw/books?id=0kxBCgAAQBAJ&pg=SA4-PA31&lpg=SA4-PA31&dq=手勢資料庫+android+studio&source=bl&ots=UdsGM8LyZf&sig=ViK0Qv3MBDXPxVIRLLnxRDIEcqs&hl=zh-TW&sa=X&ved=0ahUKEwiJ2KCo0sXPAhVGX5QKHQf-ANIQ6AEIHzAB#v=onepage&q=手勢資料庫%20android%20studio&f=false
     * Page. 4-31 ~ 4-32
     */

    private TextView textView;
    private GestureLibrary gestureLibrary;
    private GestureOverlayView gestureOverlayView;
    private List<Integer> colorList;
    private int X_colorIndex = 0, Y_colorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch06);

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);

        //退出時使用
        getWindow().setExitTransition(explode);
        //第一次進入時使用
        getWindow().setEnterTransition(explode);
        //再次進入時使用
        getWindow().setReenterTransition(explode);

        // You should also override your application class with the following.
        TypefaceProvider.registerDefaultIconSets();

        this.textView = (TextView) findViewById(R.id.Ch06_textView);
        this.initColorList();

        this.gestureOverlayView = (GestureOverlayView) findViewById(R.id.Ch06_gestureOverlayView);
        this.gestureOverlayView.addOnGesturePerformedListener(this);
        this.gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);

        if (!this.gestureLibrary.load())
            Toast.makeText(Ch06Activity.this, "Gestures load is NOT!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Ch06Activity.this, "Gestures load is OK!", Toast.LENGTH_LONG).show();
    }

    private void initColorList() {
        this.colorList = new ArrayList<>();
        this.colorList.add(R.color.bootstrap_brand_danger);
        this.colorList.add(R.color.bootstrap_brand_info);
        this.colorList.add(R.color.bootstrap_brand_primary);
        this.colorList.add(R.color.bootstrap_brand_secondary_border);
        this.colorList.add(R.color.bootstrap_brand_secondary_text);
        this.colorList.add(R.color.bootstrap_brand_success);
        this.colorList.add(R.color.bootstrap_brand_warning);
    }

    private int indexPlus(int index) {
        if (index >= this.colorList.size()) {
            index = 0;
            return index;
        } else {
            index++;
            return index;
        }
    }

    private int indexBack(int index) {
        if (index == 0) {
            return this.colorList.size();
        } else {
            index--;
            return index;
        }
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList predictions = this.gestureLibrary.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = (Prediction) predictions.get(0);
            if (prediction.score > 1.0) {
                this.textView.setText(
                        "Activity Name : " + prediction.name + "\n" +
                                "Score : " + prediction.score + "\n"
                );
                switch (prediction.name) {
                    case "LeftToRight":
                        this.X_colorIndex = this.indexPlus(this.X_colorIndex);
                        this.gestureOverlayView.setBackgroundColor(colorList.get(0));
                        break;

                    case "RightToLeft":
                        this.X_colorIndex = this.indexBack(this.X_colorIndex);
                        this.gestureOverlayView.setBackgroundColor(colorList.get(this.X_colorIndex));
                        break;

                    case "TopToBottom":

                        break;

                    case "BottomToTop":

                        break;
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(Ch06Activity.this, MainActivity.class);
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
