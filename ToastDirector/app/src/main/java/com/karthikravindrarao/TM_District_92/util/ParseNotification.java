package com.karthikravindrarao.TM_District_92.util;

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
        Parse.initialize(this, "8TbpDkwFUDXKSsgA9CWsp5aN8d1uFEHc0WyBR8Aw", "9c0bLnaUaGH4aOIU7VjOKT3cZmN9ssfvI7hnz19y");
        ParsePush.subscribeInBackground("DISTRICT-92");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
