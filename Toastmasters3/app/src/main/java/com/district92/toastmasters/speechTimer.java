package com.district92.toastmasters;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class speechTimer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Speech Timer");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_timer);
    }
    public void startChronometer(final View view) {
        ((Chronometer) findViewById(R.id.chronometer)).start();
        Intent intent = getIntent();
        final long greenValue = intent.getIntExtra(timerSelectionActivity.greenTimer, 0);
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setText("");
        ((Chronometer) findViewById(R.id.chronometer)).setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                long elapsedTimeInMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
                long elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                if (greenValue == 1 || greenValue == 2) {
                    if ((elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60)) || (elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60) + 30) || (elapsedTimeInMinutes == greenValue + 1 && elapsedTimeInSeconds == (greenValue * 60) + 60)) {
                        Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        timerVibrator.vibrate(500);
                    }
                    if (elapsedTimeInMinutes == greenValue) {

                    }
                    else if (elapsedTimeInMinutes == greenValue) {

                    }
                    else {

                    }
                } else {
                    if ((elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60)) || (elapsedTimeInMinutes == greenValue + 1 && elapsedTimeInSeconds == (greenValue * 60) + 30) || (elapsedTimeInMinutes == greenValue + 2 && elapsedTimeInSeconds == (greenValue * 60) + 60)) {
                        Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        timerVibrator.vibrate(500);
                    }
                }
            }
        });
    }

    public void stopChronometer(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).stop();
    }

    public void resetChronometer(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speech_chronometer, menu);
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
