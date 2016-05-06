package com.example.android.apis.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.apis.R;

/**
 * Created by Cerie on 16/5/6.
 */
public class ActionBarSettingsActionProviderActivity  extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.action_bar_settings_action_provider, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, R.string.action_bar_settings_action_provider_no_handling,
                Toast.LENGTH_SHORT).show();
        return false;
    }

    public  class SettingActionProvider extends ActionProvider {
        private  final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        private Context mContext;

        public SettingActionProvider(Context context, Context mContext) {
            super(context);
            this.mContext = mContext;
        }

        @Override
        public View onCreateActionView() {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.action_bar_settings_action_provider,null);
            ImageButton button = (ImageButton) view.findViewById(R.id.button);
            // Attach a click listener for launching the system settings.
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(sSettingsIntent);
                }
            });
            return view;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean onPerformDefaultAction() {
            // This is called if the host menu item placed in the overflow menu of the
            // action bar is clicked and the host activity did not handle the click.
            mContext.startActivity(sSettingsIntent);
            return true;
        }
    }


}
