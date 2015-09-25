package org.tmdistrict92.toastmasters.util;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import org.tmdistrict92.toastmasters.WebactivityForParse;
import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by Karthik on 9/22/15.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    public void onPushOpen(Context context, Intent intent) {

        Intent i = new Intent(context, WebactivityForParse.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
