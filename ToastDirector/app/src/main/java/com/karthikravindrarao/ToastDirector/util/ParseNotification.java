package com.karthikravindrarao.ToastDirector.util;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Karthik on 9/22/15.
 */
public class ParseNotification extends Application{
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ljsPLiJkLxXc3pQkJ0yhTw5ZO7BWdMJ5WpCpJ8Tr", "eex0SpUx13oTpqtQ8wZvxpdMnoeaO3Ed2zU0nXXW");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
