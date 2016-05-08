package com.example.android.apis.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/7.
 */
public class Intents extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);

        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.get_music);
        button.setOnClickListener(mGetMusicListener);
    }

    private View.OnClickListener mGetMusicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivity(Intent.createChooser(intent,"请选择"));
        }
    };
}
