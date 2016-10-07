package com.example.kantai.androidexampledemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class Ch03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch03);

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);

        //退出時使用
        getWindow().setExitTransition(explode);
        //第一次進入時使用
        getWindow().setEnterTransition(explode);
        //再次進入時使用
        getWindow().setReenterTransition(explode);

        // You should also override your application class with the following.
        TypefaceProvider.registerDefaultIconSets();
    }


    public void Ch03_Button_Click(View view) {
        ImageView imageViewIdList[] = {
                (ImageView) findViewById(R.id.Ch03_imageView_Main),
                (ImageView) findViewById(R.id.Ch03_imageView_Bottom01),
                (ImageView) findViewById(R.id.Ch03_imageView_Bottom02),
                (ImageView) findViewById(R.id.Ch03_imageView_Bottom03)
        };
        for (ImageView imageId : imageViewIdList) {
            imageId.setImageResource(R.mipmap.ic_launcher);
        }
    }
    
    public void Ch03_imageView_Click(View view) {
        ImageView imageView = (ImageView) findViewById(view.getId());
        imageView.setImageResource(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(Ch03Activity.this, MainActivity.class);
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
