package com.karthikravindrarao.ToastDirector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseInstallation;
import com.parse.ParsePush;

public class ChooseNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Notifications");
        setContentView(R.layout.activity_choose_notifications);
    }

    public void noNotification (View view) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.remove("channels");
        ParsePush.subscribeInBackground("DISTRICT-92");
        finish();
    }

    public void yesNotification (View view) {
        ParsePush.subscribeInBackground("CORONATION-2016");
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
