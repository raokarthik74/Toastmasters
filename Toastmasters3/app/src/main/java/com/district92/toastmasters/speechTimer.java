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
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import models.TimerReportModel;


public class speechTimer extends ActionBarActivity {

    long elapsedTimeInSeconds;
    long endTimeInSeconds;
    long timeWhenStopped;
    boolean isPaused;
    Intent intent;
    long greenValue;
    boolean isAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPaused = false;
        intent = getIntent();
        isAdvanced = intent.getBooleanExtra(AdvancedSpeechesTimerSetting.isAdvanced, false);
        greenValue = intent.getIntExtra(timerSelectionActivity.greenTimer, 0);
        if(isAdvanced) {
            setTitle(intent.getStringExtra(AdvancedSpeechesTimerSetting.timerTitle));
        }
        else {
            setTitle(intent.getStringExtra(timerSelectionActivity.TimerTitle));
        }
        greenValue = TimeUnit.MINUTES.toSeconds(greenValue);
        setContentView(R.layout.activity_speech_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void startChronometer(final View view) {
        if (isPaused) {
            ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }
        else {
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setText("Dont forget to flash the cards !");

        if (isAdvanced) {
            ((Chronometer) findViewById(R.id.chronometer)).setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                    advancedTimerExecution();
                }
            });
        }
        else if (greenValue == 60 || greenValue == 120) {
            ((Chronometer) findViewById(R.id.chronometer)).setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                    greenValue1or2Execution();
                }
            });
        }
        else  {
            ((Chronometer) findViewById(R.id.chronometer)).setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                    normalTimerExecution();
                }
            });
        }
    }


    public void advancedTimerExecution () {
        long greenValue = intent.getLongExtra(AdvancedSpeechesTimerSetting.greenValue, 0);
        long amberValue = intent.getLongExtra(AdvancedSpeechesTimerSetting.amberValue, 0);
        long redValue = intent.getLongExtra(AdvancedSpeechesTimerSetting.redValue, 0);
        if (elapsedTimeInSeconds == greenValue || elapsedTimeInSeconds == amberValue || elapsedTimeInSeconds == redValue) {
            Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            timerVibrator.vibrate(500);
        }
        if (elapsedTimeInSeconds > greenValue && elapsedTimeInSeconds < amberValue) {
            turnGreen();
        }
        if (elapsedTimeInSeconds > amberValue && elapsedTimeInSeconds < redValue) {
            turnAmber();
        }
        if (elapsedTimeInSeconds > redValue) {
            turnRed();
        }
        if (elapsedTimeInSeconds > redValue+30) {
            timeEnded(redValue+30, elapsedTimeInSeconds);
        }
    }

    public void greenValue1or2Execution () {
        endTimeInSeconds = greenValue + 90;
        if ( elapsedTimeInSeconds == greenValue  || elapsedTimeInSeconds == greenValue + 30 || elapsedTimeInSeconds == greenValue + 60 || elapsedTimeInSeconds == endTimeInSeconds) {
            Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            timerVibrator.vibrate(500);
        }
        if (elapsedTimeInSeconds > greenValue && elapsedTimeInSeconds < greenValue+30) {
            turnGreen();
        }
        if (elapsedTimeInSeconds > greenValue  + 30 && elapsedTimeInSeconds < greenValue  + 60) {
            turnAmber();
        }
        if (elapsedTimeInSeconds > greenValue  + 60) {
            turnRed();
        }
        if (elapsedTimeInSeconds > endTimeInSeconds) {
            timeEnded(endTimeInSeconds, elapsedTimeInSeconds);
        }
    }

    public void normalTimerExecution () {
        endTimeInSeconds = greenValue  + 150;
        if (elapsedTimeInSeconds == greenValue  || elapsedTimeInSeconds == greenValue + 60 || elapsedTimeInSeconds == greenValue + 120 || elapsedTimeInSeconds == endTimeInSeconds) {
            Vibrator timerVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            timerVibrator.vibrate(500);
        }
        if (elapsedTimeInSeconds > greenValue && elapsedTimeInSeconds < greenValue+60) {
            turnGreen();
        }
        if (elapsedTimeInSeconds > greenValue  + 60 && elapsedTimeInSeconds < greenValue  + 120) {
            turnAmber();
        }
        if (elapsedTimeInSeconds > greenValue  + 120) {
            turnRed();
        }
        if (elapsedTimeInSeconds > endTimeInSeconds) {
            timeEnded(endTimeInSeconds, elapsedTimeInSeconds);
        }
    }




    public void stopChronometer(View view) {
        isPaused = true;
        timeWhenStopped = ((Chronometer) findViewById(R.id.chronometer)).getBase() - SystemClock.elapsedRealtime();
        ((Chronometer) findViewById(R.id.chronometer)).stop();
    }

    public void resetChronometer(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        ((EditText) findViewById(R.id.enterNameForTimer)).setText("");
        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
        View rootView = timerRelativeLayout.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.icons));
        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.secondary_text));
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setTextColor(getResources().getColor(R.color.secondary_text));
        timeUpText.setText("Dont forget to flash the cards !");
    }

    public void turnGreen () {
        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
        View rootView = timerRelativeLayout.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.green));
    }
    public void turnAmber () {
        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.primary_text));
        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
        View rootView = timerRelativeLayout.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.amber));
    }
    public void turnRed () {
        ((Chronometer) findViewById(R.id.chronometer)).setTextColor(getResources().getColor(R.color.icons));
        RelativeLayout timerRelativeLayout = (RelativeLayout) findViewById(R.id.TimerRelativeLayout);
        View rootView = timerRelativeLayout.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.red));
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setTextColor(getResources().getColor(R.color.icons));
    }
    public void timeEnded (long end, long elapsed) {
        TextView timeUpText = (TextView) findViewById(R.id.getcurrent);
        timeUpText.setTextColor(getResources().getColor(R.color.icons));
        long exceededTime = (elapsed - end);
        timeUpText.setText("Time Up ! \n Time Exceeded by \n" + TimeUnit.SECONDS.toMinutes(exceededTime) + ":" + exceededTime % 60);
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

    public void timerReportCallingMethod (View view) {
        EditText nameFromActivity = (EditText) findViewById(R.id.enterNameForTimer);
        String name = nameFromActivity.getText().toString();
        long minutes=0, seconds=0;
        minutes = TimeUnit.SECONDS.toMinutes(elapsedTimeInSeconds);
        seconds = elapsedTimeInSeconds%60;
        if (elapsedTimeInSeconds > endTimeInSeconds) {
            long exceededTime = (elapsedTimeInSeconds - endTimeInSeconds);
            TimerReportModel.setDataToTimerArray("Name: " + name+"\n"+String.valueOf(minutes)+":"+String.valueOf(seconds)+"\n Time Exceeded By \n"+TimeUnit.SECONDS.toMinutes(exceededTime) + ":" + exceededTime % 60);
        }
        else {
            TimerReportModel.setDataToTimerArray(name+"\n"+String.valueOf(minutes)+":"+String.valueOf(seconds));
        }
        Intent forDataSaveActivity = new Intent(this, TimerReport.class);
        startActivity(forDataSaveActivity);
    }
}
