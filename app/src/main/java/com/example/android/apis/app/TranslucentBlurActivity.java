package com.example.android.apis.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/7.
 */
public class TranslucentBlurActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.translucent_background);
    }
}
