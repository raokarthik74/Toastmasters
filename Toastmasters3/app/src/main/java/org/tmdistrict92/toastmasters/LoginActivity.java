package org.tmdistrict92.toastmasters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    String userId;
    String password;
    Intent intentToPush;
    AlertDialog wrongAuth;
    public final static String userID = "com.district92.toastmasters.userid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LOGIN");
        if(!isNetworkAvailable()) {
            TextView textView = new TextView(this);
            textView.setTextSize(25);
            textView.setText("Internet required \n Check internet connection and try again");
            textView.setGravity(Gravity.CENTER);
            setContentView(textView);
        }
        else
        setContentView(R.layout.activity_login);
    }

    public void checkAuth (View view) {

        intentToPush = new Intent(this, PushNotificationActivity.class);
        userId = ((EditText) findViewById(R.id.userIdForLogin)).getText().toString();
        password = ((EditText) findViewById(R.id.passwordForLogin)).getText().toString();
        wrongAuth = new AlertDialog.Builder(this).create();
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText("LOADING...");
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);
        ParseUser.logInInBackground(userId, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    intentToPush.putExtra(userID, userId);
                    startActivity(intentToPush);
                    finish();
                } else {
                    wrongAuth.setTitle("Error");
                    wrongAuth.setMessage("Check User ID and Password");
                    wrongAuth.setButton(AlertDialog.BUTTON_NEUTRAL, "Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((EditText) findViewById(R.id.userIdForLogin)).setText("");
                            ((EditText) findViewById(R.id.passwordForLogin)).setText("");
                            dialog.dismiss();
                        }
                    });
                    wrongAuth.show();
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.netRefreshForLogin:
                finish();
                startActivity(getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
