package org.tmdistrict92.toastmasters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.tmdistrict92.toastmasters.entities.PushDataInfo;

import com.google.gson.Gson;
import com.parse.ParsePush;

import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Send Notifications");
        if(!isNetworkAvailable()) {
            TextView textView = new TextView(this);
            textView.setTextSize(25);
            textView.setText("Internet required \n Check internet connection and try again");
            textView.setGravity(Gravity.CENTER);
            setContentView(textView);
        }
        else
        setContentView(R.layout.activity_push_notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_push_notification, menu);
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void pushNotification (View view) {
            try {
                Intent intentFromLogin = getIntent();
                String userID = intentFromLogin.getStringExtra(LoginActivity.userID);
                PushDataInfo pushDataInfo = new PushDataInfo();
                pushDataInfo.setTitle("\n" + userID + ":\n" +  ((EditText) findViewById(R.id.notificationTitleEditText)).getText().toString());
                pushDataInfo.setUrl(((EditText) findViewById(R.id.notificationLinkUrlEditText)).getText().toString());
                pushDataInfo.setAlert(((EditText) findViewById(R.id.notificationMessageEditText)).getText().toString());
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(gson.toJson(pushDataInfo));
                ParsePush push = new ParsePush();
                push.setChannel(userID);
                push.setData(jsonObject);
                push.sendInBackground();
            } catch (JSONException e) {

            }
        Intent intentToReturn = new Intent(this, MainActivity.class);
        startActivity(intentToReturn);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.netRefreshForPush:
                finish();
                startActivity(getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



