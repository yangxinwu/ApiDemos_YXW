package com.example.android.apis.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.apis.R;

import java.util.Calendar;

/**
 * Created by Cerie on 16/5/7.
 */
public class AlarmController extends Activity {
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_controller);

        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.one_shot);
        button.setOnClickListener(mOneShotListener);
        button = (Button)findViewById(R.id.start_repeating);
        button.setOnClickListener(mStartRepeatingListener);
        button = (Button)findViewById(R.id.stop_repeating);
        button.setOnClickListener(mStopRepeatingListener);
    }

    private View.OnClickListener mOneShotListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(AlarmController.this,OneShotAlarm.class);
            PendingIntent sender = PendingIntent.getBroadcast(AlarmController.this,0,intent,0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 30);

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),sender);

            if (mToast != null ){
                mToast.cancel();
            }

            mToast = Toast.makeText(AlarmController.this,R.string.one_shot_alarm,Toast.LENGTH_SHORT);
            mToast.show();

        }
    };

    private View.OnClickListener mStartRepeatingListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(AlarmController.this, RepeatingAlarm.class );
            PendingIntent sender = PendingIntent.getBroadcast(AlarmController.this,0,intent,0);
            long firstTime = SystemClock.elapsedRealtime();
            firstTime += 15*1000;
            /*https://hzj163.gitbooks.io/android-alarm/content/chapter1.html*/
            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP , firstTime ,15*1000 , sender);


            // Tell the user about what we did.
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(AlarmController.this, R.string.repeating_scheduled,
                    Toast.LENGTH_LONG);
            mToast.show();

        }
    };

    private View.OnClickListener mStopRepeatingListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(AlarmController.this ,RepeatingAlarm.class);
            PendingIntent sender = PendingIntent.getBroadcast(AlarmController.this , 0 ,intent ,0 );

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.cancel(sender);

            // Tell the user about what we did.RepeatingAlarm.java
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(AlarmController.this, R.string.repeating_unscheduled,
                    Toast.LENGTH_LONG);
            mToast.show();

        }
    };




}
