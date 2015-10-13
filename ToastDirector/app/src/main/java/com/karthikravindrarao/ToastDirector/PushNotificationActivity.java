package com.karthikravindrarao.ToastDirector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.karthikravindrarao.ToastDirector.entities.PushDataInfo;

import com.google.gson.Gson;
import com.parse.ParsePush;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PushNotificationActivity extends AppCompatActivity {

    static PushNotificationActivity pushNotificationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Send Notifications");
        pushNotificationActivity = this;
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

    public static PushNotificationActivity getInstance () {
        return pushNotificationActivity;
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

        Button pushButton = (Button) findViewById(R.id.sendPushButton);

        final ProgressBar loading = (ProgressBar) findViewById(R.id.progressBarOfLoadingPush);
        loading.setVisibility(View.VISIBLE);
        if (isNetworkAvailable()) {
            try {
                pushButton.setEnabled(false);
                Random rand = new Random();
                int ramdom;
                Intent intentFromLogin = getIntent();
                String userID = intentFromLogin.getStringExtra(LoginActivity.userID);
                PushDataInfo pushDataInfo = new PushDataInfo();
                pushDataInfo.setTitle(userID + ":\t" + ((EditText) findViewById(R.id.notificationTitleEditText)).getText().toString());
                pushDataInfo.setUrl(((EditText) findViewById(R.id.notificationLinkUrlEditText)).getText().toString());
                pushDataInfo.setAlert(((EditText) findViewById(R.id.notificationMessageEditText)).getText().toString());
                ramdom = rand.nextInt((10000 - 1000) + 1) + 1000;
                pushDataInfo.setPushId(ramdom);
                getSharedPreferences("PUSHID", MODE_PRIVATE).edit().putInt("pushid", ramdom).apply();
                SimpleDateFormat currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy \t hh:mm:ss aa");
                pushDataInfo.setDateAndTime(currentDateAndTime.format(new Date()));
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(gson.toJson(pushDataInfo));
                ParsePush push = new ParsePush();
                ParsePush.subscribeInBackground(userID);
                push.setChannel(userID);
                push.setData(jsonObject);
                push.sendInBackground();
                final Intent intentToMainActivity = new Intent(this, MainActivity.class);
                loading.setVisibility(View.INVISIBLE);
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Your notification will be sent shortly !");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(intentToMainActivity);
                            }
                        });
                alertDialog.show();
            } catch (JSONException e) {

            }
        }
        else {
            pushButton.setEnabled(true);
            loading.setVisibility(View.INVISIBLE);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No Internet");
            alertDialog.setMessage("Check Network Connection and Try Again");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
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



