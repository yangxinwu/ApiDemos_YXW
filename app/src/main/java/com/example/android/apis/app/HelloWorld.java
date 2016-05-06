package com.example.android.apis.app;

import android.app.Activity;
import android.os.Bundle;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/5.
 */
public class HelloWorld extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_demos);
    }
}
