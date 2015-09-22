package com.district92.toastmasters.util;

import android.content.Context;
import android.content.Intent;

import com.district92.toastmasters.MainActivity;
import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by Karthik on 9/22/15.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
