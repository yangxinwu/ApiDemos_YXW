package com.example.android.apis.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/8.
 */
public class AlarmService_Service  extends Service{
    NotificationManager mNM;

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        showNotification();

        Thread thr = new Thread(null , mTask , "AlarmService_Service");
        thr.start();
    }

    private void showNotification() {

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, AlarmService.class), 0);
        Notification.Builder builder = new Notification.Builder(AlarmService_Service.this);
        builder.setContentIntent(contentIntent);
        builder.setContentTitle("yxw");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        builder.setOngoing(true);
        builder.setContentText("y123");
        builder.setSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "5"));
        builder.setVibrate(new long[]{2000, 1000, 4000}); //需要真机测试
        Notification notification = builder.build();
        // notification.flags =Notification.FLAG_ONGOING_EVENT;
        mNM.notify(0, notification);


    }

    @Override
    public void onDestroy() {
        // Cancel the notification -- we use the same ID that we had used to start it
        mNM.cancel(R.string.alarm_service_started);

        // Tell the user we stopped.
        Toast.makeText(this, R.string.alarm_service_finished, Toast.LENGTH_SHORT).show();
    }

    Runnable mTask = new Runnable() {
        @Override
        public void run() {
            long endTime = System.currentTimeMillis() + 15*1000;

            synchronized (mBinder) {

                try {
                    mBinder.wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            AlarmService_Service.this.stopSelf();

        }
    };


    private final IBinder mBinder = new Binder(){

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



}
