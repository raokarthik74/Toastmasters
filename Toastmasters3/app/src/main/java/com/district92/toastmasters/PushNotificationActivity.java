package com.district92.toastmasters;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.district92.toastmasters.entities.PushDataInfo;
import com.district92.toastmasters.util.DatePickerFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public void selectDate(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void pushNotification (View view) {
        try {
            PushDataInfo pushDataInfo = new PushDataInfo();
            pushDataInfo.setTitle(((EditText) findViewById(R.id.notificationTitleEditText)).getText().toString());
            pushDataInfo.setUrl(((EditText) findViewById(R.id.notificationLinkUrlEditText)).getText().toString());
            pushDataInfo.setAlert(((EditText) findViewById(R.id.notificationMessageEditText)).getText().toString());
            //JSONObject data = new JSONObject("{\"alert\": \"The Mets scored!\",\"title\": \"Whats up !\",\"url\": \"www.google.com\"}");

            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(gson.toJson(pushDataInfo));

            ParsePush push = new ParsePush();
            push.setChannel("DISTRICT-92");

            push.setData(jsonObject);
            push.sendInBackground();
        }
        catch (JSONException e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}


