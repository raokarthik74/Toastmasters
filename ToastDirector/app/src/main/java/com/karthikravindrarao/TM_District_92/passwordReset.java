package com.karthikravindrarao.TM_District_92;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class passwordReset extends AppCompatActivity {
    AlertDialog wrongAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Reset Password");
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_password_reset);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void checkResetButton (View view) {
        final String userId;
        final String password;
        final String newPassword;
        String reNewPassword;
        final Button resetButton = (Button) findViewById(R.id.resetPasswordButton);
        if (isNetworkAvailable()) {
            resetButton.setEnabled(false);
            userId = ((EditText) findViewById(R.id.resetUserIdEditText)).getText().toString();
            password = ((EditText) findViewById(R.id.resetCurrentPasswordEditText)).getText().toString();
            newPassword = ((EditText) findViewById(R.id.resetNewPasswordEditText)).getText().toString();
            reNewPassword = ((EditText) findViewById(R.id.resetReEnterNewPasswordEditText)).getText().toString();
            wrongAuth = new AlertDialog.Builder(this).create();
            final ProgressBar loading = (ProgressBar) findViewById(R.id.progressBarOfResetPassword);
            loading.setVisibility(View.VISIBLE);
            if (newPassword.equals(reNewPassword)) {
                resetButton.setEnabled(false);
                ParseUser.logInInBackground(userId, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            resetButton.setEnabled(false);
                            try {
                                if (password.equals(newPassword)) {
                                    resetButton.setEnabled(true);
                                    loading.setVisibility(View.INVISIBLE);
                                    wrongAuth.setTitle("Error");
                                    wrongAuth.setMessage("New Password cannot be same as old password");
                                    ((EditText) findViewById(R.id.resetNewPasswordEditText)).setText("");
                                    ((EditText) findViewById(R.id.resetReEnterNewPasswordEditText)).setText("");
                                    wrongAuth.setButton(AlertDialog.BUTTON_NEUTRAL, "Try Again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    wrongAuth.show();
                                }
                                else {
                                    resetButton.setEnabled(false);
                                    ParseUser user = ParseUser.logIn(userId, password);
                                    user.setPassword(newPassword);
                                    user.saveInBackground();
                                    loading.setVisibility(View.INVISIBLE);
                                    wrongAuth.setTitle("Success");
                                    wrongAuth.setMessage("Your password has been successfully changed");
                                    wrongAuth.setButton(AlertDialog.BUTTON_NEUTRAL, "LOGIN", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    wrongAuth.show();
                                }
                            } catch (ParseException ex) {

                            }
                        } else {
                            resetButton.setEnabled(true);
                            wrongAuth.setTitle("Error");
                            wrongAuth.setMessage("Check User ID and Current Password");
                            loading.setVisibility(View.INVISIBLE);
                            wrongAuth.setButton(AlertDialog.BUTTON_NEUTRAL, "Try Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((EditText) findViewById(R.id.resetUserIdEditText)).setText("");
                                    ((EditText) findViewById(R.id.resetCurrentPasswordEditText)).setText("");
                                    ((EditText) findViewById(R.id.resetNewPasswordEditText)).setText("");
                                    ((EditText) findViewById(R.id.resetReEnterNewPasswordEditText)).setText("");
                                    dialog.dismiss();
                                }
                            });
                            wrongAuth.show();
                        }
                    }
                });
            }
            else {
                resetButton.setEnabled(true);
                loading.setVisibility(View.INVISIBLE);
                wrongAuth.setTitle("Error");
                wrongAuth.setMessage("New password and re-enter new password does not match");
                wrongAuth.setButton(AlertDialog.BUTTON_NEUTRAL, "Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((EditText) findViewById(R.id.resetNewPasswordEditText)).setText("");
                        ((EditText) findViewById(R.id.resetReEnterNewPasswordEditText)).setText("");
                        dialog.dismiss();
                    }
                });
                wrongAuth.show();
            }
        }
        else {
            resetButton.setEnabled(true);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.resetPasswordEntries) {
            ((EditText) findViewById(R.id.resetUserIdEditText)).setText("");
            ((EditText) findViewById(R.id.resetCurrentPasswordEditText)).setText("");
            ((EditText) findViewById(R.id.resetNewPasswordEditText)).setText("");
            ((EditText) findViewById(R.id.resetReEnterNewPasswordEditText)).setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
