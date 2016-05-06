package com.example.android.apis.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.apis.R;

public class Animation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.fade_animation);
        button.setOnClickListener(mFadeListener);
        button = (Button)findViewById(R.id.zoom_animation);
        button.setOnClickListener(mZoomListener);
    }

    private View.OnClickListener mFadeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Animation.this, HelloWorld.class));
            //activity 切换时的动画123
            overridePendingTransition(R.anim.fade,R.anim.hold);
        }
    };

    private View.OnClickListener mZoomListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Animation.this, HelloWorld.class));
            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_out);
        }
    };



}
