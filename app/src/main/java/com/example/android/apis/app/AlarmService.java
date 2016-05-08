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

/**
 * Created by Cerie on 16/5/8.
 */
public class AlarmService extends Activity {
    private PendingIntent mAlarmSender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAlarmSender = PendingIntent.getService(AlarmService.this , 0 , new Intent(AlarmService.this,AlarmService_Service.class) , 0);
        setContentView(R.layout.alarm_service);
        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.start_alarm);
        button.setOnClickListener(mStartAlarmListener);
        button = (Button)findViewById(R.id.stop_alarm);
        button.setOnClickListener(mStopAlarmListener);
    }

    private View.OnClickListener mStartAlarmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            long firstTime = SystemClock.elapsedRealtime();

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP , firstTime , 30*1000 , mAlarmSender);


            // Tell the user about what we did.
            Toast.makeText(AlarmService.this, R.string.repeating_scheduled,
                    Toast.LENGTH_LONG).show();


        }
    };

    private View.OnClickListener mStopAlarmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.cancel(mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(AlarmService.this, R.string.repeating_unscheduled,
                    Toast.LENGTH_LONG).show();

        }
    };
}
