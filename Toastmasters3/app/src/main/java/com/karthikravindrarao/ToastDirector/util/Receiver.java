package com.karthikravindrarao.ToastDirector.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;
import com.karthikravindrarao.ToastDirector.MainActivity;
import com.karthikravindrarao.ToastDirector.PushNotificationActivity;
import com.karthikravindrarao.ToastDirector.WebactivityForParse;
import com.karthikravindrarao.ToastDirector.entities.NotificationPreparer;

import com.google.gson.Gson;
import com.parse.ParsePushBroadcastReceiver;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Karthik on 9/22/15.
 */
public class Receiver extends ParsePushBroadcastReceiver {


    @Override
    public void onPushReceive (Context context, Intent intent) {
        super.onPushReceive(context, intent);
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String jsonData = extras.getString("com.parse.Data");
                JSONObject json;
                json = new JSONObject(jsonData);
                String url =  json.getString("url");
                String title = json.getString("title");
                String alert = json.getString("alert");
                String dateAndTime = json.getString("dateAndTime");
                int pushIdFromNotification = json.getInt("pushId");
                int pushId = context.getSharedPreferences("PUSHID", Context.MODE_PRIVATE).getInt("pushid", 0);
                if (pushId == pushIdFromNotification) {
                    PushNotificationActivity.getInstance().finish();
                }
                SharedPreferences message = context.getSharedPreferences("message", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = message.edit();
                Set<String> messageSet = message.getStringSet("messageSet", new HashSet<String>());
                Gson gson = new Gson();
                NotificationPreparer notificationPreparer = new NotificationPreparer();
                notificationPreparer.setAlert(alert);
                notificationPreparer.setTitle(title);
                notificationPreparer.setUrl(url);
                notificationPreparer.setDateAndTime(dateAndTime);
                messageSet.add(gson.toJson(notificationPreparer));
                editor.remove("messageSet");
                editor.apply();
                editor.putStringSet("messageSet", messageSet);
                editor.apply();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MainActivity.getInstance().recreate();
    }

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Intent i = new Intent(context, WebactivityForParse.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
