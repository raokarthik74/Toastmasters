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
import android.widget.RelativeLayout;
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
        final TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setText("Dont forget to flash the cards !");
        ((Chronometer) findViewById(R.id.chronometer)).setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                long elapsedTimeInMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
                long elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                if (greenValue == 1 || greenValue == 2) {
                    long endTimeInSeconds = ((greenValue*60)+120);
                    if ((elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60)) || (elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60) + 30) || (elapsedTimeInMinutes == greenValue + 1 && elapsedTimeInSeconds == (greenValue * 60) + 60) ||elapsedTimeInSeconds == endTimeInSeconds) {
                        Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        timerVibrator.vibrate(500);
                    }
                    if (elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds < (greenValue*60)+30) {
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    if (elapsedTimeInSeconds > (greenValue*60)+30 && elapsedTimeInSeconds < (greenValue*60)+60) {
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.amber));
                    }
                    if (elapsedTimeInMinutes == greenValue + 1){
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.icons));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    if (elapsedTimeInSeconds > endTimeInSeconds) {
                        timeUpText.setTextColor(getResources().getColor(R.color.icons));
                        long exceededTime = (elapsedTimeInSeconds - endTimeInSeconds);
                        timeUpText.setText("Time Up ! \n Time Exceeded by \n" + TimeUnit.SECONDS.toMinutes(exceededTime) + ":" + exceededTime%60 );
                    }
                } else {
                    long endTimeInSeconds2 = ((greenValue*60)+180);
                    if ((elapsedTimeInMinutes == greenValue && elapsedTimeInSeconds == (greenValue * 60)) || (elapsedTimeInMinutes == greenValue + 1 && elapsedTimeInSeconds == (greenValue * 60) + 30) || (elapsedTimeInMinutes == greenValue + 2 && elapsedTimeInSeconds == (greenValue * 60) + 60) ||elapsedTimeInSeconds == endTimeInSeconds2) {
                        Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        timerVibrator.vibrate(500);
                    }
                    if (elapsedTimeInMinutes == greenValue && elapsedTimeInMinutes < greenValue+1) {
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    if (elapsedTimeInMinutes > greenValue+1 && elapsedTimeInMinutes < greenValue+2) {
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.amber));
                    }
                    if (elapsedTimeInMinutes == greenValue + 2){
                        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.icons));
                        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
                        View rootView = timerRelativeLayout.getRootView();
                        rootView.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    if (elapsedTimeInSeconds > endTimeInSeconds2 ) {
                        timeUpText.setTextColor(getResources().getColor(R.color.icons));
                        long exceededTime = (elapsedTimeInSeconds - endTimeInSeconds2);
                        timeUpText.setText("Time Up ! \n Time Exceeded by \n" + TimeUnit.SECONDS.toMinutes(exceededTime) + ":" + exceededTime % 60);
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
        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
        View rootView = timerRelativeLayout.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.icons));
        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.secondary_text));
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setTextColor(getResources().getColor(R.color.secondary_text));
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
