package com.example.kantai.androidexampledemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // You should also override your application class with the following.
        TypefaceProvider.registerDefaultIconSets();
    }

    public void Main_Button_Click(View view) {
        //初始化Intent物件
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.Main_Button_for_Ch01:
                intent.setClass(MainActivity.this, Ch01Activity.class);
                break;
            case R.id.Main_Button_for_Ch02:
                intent.setClass(MainActivity.this, Ch02Activity.class);
                break;
            case R.id.Main_Button_for_Ch03:
                intent.setClass(MainActivity.this, Ch03Activity.class);
                break;
            case R.id.Main_Button_for_Ch04:
                intent.setClass(MainActivity.this, Ch04Activity.class);
                break;
            case R.id.Main_Button_for_Ch05:
                intent.setClass(MainActivity.this, Ch05Activity.class);
                break;
            case R.id.Main_Button_for_Ch06:
                intent.setClass(MainActivity.this, Ch06Activity.class);
                break;
            case R.id.Main_Button_for_Ch07:
                intent.setClass(MainActivity.this, Ch07Activity.class);
                break;
            case R.id.Main_Button_for_Ch08:
                intent.setClass(MainActivity.this, Ch08Activity.class);
                break;
            case R.id.Main_Button_for_Ch09:
                intent.setClass(MainActivity.this, Ch09Activity.class);
                break;
            case R.id.Main_Button_for_Ch10:
                intent.setClass(MainActivity.this, Ch10Activity.class);
                break;
            case R.id.Main_Button_for_Ch11:
                intent.setClass(MainActivity.this, Ch11Activity.class);
                break;
            case R.id.Main_Button_for_Ch12:
                intent.setClass(MainActivity.this, Ch12Activity.class);
                break;
            case R.id.Main_Button_for_Ch13:
                intent.setClass(MainActivity.this, Ch13Activity.class);
                break;
            default:
                intent.setClass(MainActivity.this, MainActivity.class);
                break;
        }

        //開啟Activity
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        //finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
