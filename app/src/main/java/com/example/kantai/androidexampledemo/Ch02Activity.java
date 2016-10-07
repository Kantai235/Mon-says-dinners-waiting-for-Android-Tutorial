package com.example.kantai.androidexampledemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.List;

public class Ch02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch02);

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

    public void Ch02_Button_Click(View view) {
        EditText editText_Username = (EditText) findViewById(R.id.Ch02_editText_Username);
        EditText editText_Password = (EditText) findViewById(R.id.Ch02_editText_Password);
        EditText editText_PhoneNumber = (EditText) findViewById(R.id.Ch02_editText_PhoneNumber);
        EditText editText_Age = (EditText) findViewById(R.id.Ch02_editText_Age);
        TextView textView = (TextView) findViewById(R.id.Ch02_textView);
        switch (view.getId()) {
            case R.id.Ch02_Button_Submit:
                textView.setText("");
                textView.setText(textView.getText() + "Username : " + editText_Username.getText() + "\n");
                textView.setText(textView.getText() + "Password : " + editText_Password.getText() + "\n");
                textView.setText(textView.getText() + "PhoneNumber : " + editText_PhoneNumber.getText() + "\n");
                textView.setText(textView.getText() + "Age : " + editText_Age.getText() + "\n");
                break;
            case R.id.Ch02_Button_Clear:
                textView.setText("");
                editText_Username.setText("");
                editText_Password.setText("");
                editText_PhoneNumber.setText("");
                editText_Age.setText("");
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(Ch02Activity.this, MainActivity.class);
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
