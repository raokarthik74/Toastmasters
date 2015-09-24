package com.district92.toastmasters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParsePush;

import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_push_notification, menu);
        return true;
    }

    public void pushNotification (View view) {
        try {
            JSONObject data = new JSONObject("{\"alert\": \"Few Days Remaining\",\"pagetitle\": \"Hello\",\"title\": \"Jamboree\",\"url\": \"https://www.google.co.in\"}");
            ParsePush push = new ParsePush();
            push.setChannel("DISTRICT-92");
            push.setData(data);
            push.sendInBackground();
        }
        catch (JSONException e) {

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
