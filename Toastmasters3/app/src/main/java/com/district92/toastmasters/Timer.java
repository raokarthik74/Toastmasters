package com.district92.toastmasters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class Timer extends ActionBarActivity {

    public boolean checkSingleDigit (long x) {
    if(x >= 0 && x<=9) {
        return true;
    }
        else {
        return false;
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final int greenTiming = intent.getIntExtra(selectProjectActivity.greenTimer, 0);
        setContentView(R.layout.activity_timer);
        final TextView timer = (TextView) findViewById(R.id.timerDisplay);
        final long endTime = TimeUnit.MINUTES.toMillis(greenTiming+2);
        new CountDownTimer(endTime, 1000) {

            public void onTick(long millisUntilFinished) {
                String minutes, seconds;
                long min = greenTiming+1-(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                long sec = 60-(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                if (sec == 60) {
                    sec = 00;
                }
                if (checkSingleDigit(min) && checkSingleDigit(sec)) {
                    timer.setText(String.format("0%d:0%d", min, sec));
                }
                if (checkSingleDigit(min) && !checkSingleDigit(sec)) {
                    timer.setText(String.format("0%d:%d", min, sec));
                }
                if (!checkSingleDigit(min) && checkSingleDigit(sec)) {
                    timer.setText(String.format("%d:0%d", min, sec));
                }
                
                if(min == greenTiming) {
                    Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    timerVibrator.vibrate(500);
                    }
                }

            public void onFinish() {
                timer.setText("done!");
            }

        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
