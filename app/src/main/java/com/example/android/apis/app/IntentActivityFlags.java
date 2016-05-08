package com.example.android.apis.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/7.
 */
public class IntentActivityFlags extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity_flags);
// Watch for button clicks.
        Button button = (Button)findViewById(R.id.flag_activity_clear_task);
        button.setOnClickListener(mFlagActivityClearTaskListener);
        button = (Button)findViewById(R.id.flag_activity_clear_task_pi);
        button.setOnClickListener(mFlagActivityClearTaskPIListener);


    }
    //http://blog.csdn.net/rockcoding/article/details/8124426
    private Intent[] buildIntentToViewsLists(){

        Intent[] intents = new Intent[3];

        intents[0] = Intent.makeRestartActivityTask(new ComponentName(this,com.example.android.apis.ApiDemos.class));

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClass(IntentActivityFlags.this, com.example.android.apis.ApiDemos.class);
        intent.putExtra("com.example.android.apis.Path", "App");
        intents[1] = intent;

        intent = new Intent(Intent.ACTION_MAIN);
        intent.setClass(IntentActivityFlags.this, com.example.android.apis.ApiDemos.class);
        intent.putExtra("com.example.android.apis.Path","App/Activity");

        intents[2] = intent;

        return intents;
    }

    private View.OnClickListener mFlagActivityClearTaskListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivities(buildIntentToViewsLists());
        }
    };

    private View.OnClickListener mFlagActivityClearTaskPIListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //PendingIntent的用法   http://my.oschina.net/youranhongcha/blog/196933
            Context context = IntentActivityFlags.this;
            PendingIntent pi = PendingIntent.getActivities(context,0,buildIntentToViewsLists(),PendingIntent.FLAG_UPDATE_CURRENT);

            try {
                pi.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    };





}
