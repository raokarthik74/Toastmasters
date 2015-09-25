package org.tmdistrict92.toastmasters.util;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * Created by Karthik on 9/22/15.
 */
public class ParseNotification extends Application{
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "D0o3CCt4nwYWbIgmb9HUes1RZD1tcBHpFZSoEDZQ", "26Y4hyhHOKFl501zkmX3XmzZCcZ0zIm8AH7IWVHz");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("DISTRICT-92");
    }

}
