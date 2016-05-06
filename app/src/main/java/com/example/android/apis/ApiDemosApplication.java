package com.example.android.apis;

import android.app.Application;
import android.preference.PreferenceManager;

/**
 * Created by Cerie on 16/5/5.
 */
public class ApiDemosApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.default_values, false);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
