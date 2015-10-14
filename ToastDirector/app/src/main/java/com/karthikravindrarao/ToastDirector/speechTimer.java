package com.karthikravindrarao.ToastDirector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class speechTimer extends ActionBarActivity {

    long elapsedTimeInSeconds;
    long endTimeInSeconds;
    long timeWhenStopped;
    boolean isPaused;
    Intent intent;
    long greenValue;
    boolean isAdvanced;
    boolean isExceeded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timeWhenStopped = 0;
        super.onCreate(savedInstanceState);
        isPaused = false;
        isExceeded = false;
        intent = getIntent();
        isAdvanced = intent.getBooleanExtra(AdvancedSpeechesTimerSetting.isAdvanced, false);
        greenValue = intent.getIntExtra(TimerSelection.greenTimer, 0);
        if(isAdvanced) {
            setTitle(intent.getStringExtra(AdvancedSpeechesTimerSetting.timerTitle));
        }
        else {
            setTitle(intent.getStringExtra(TimerSelection.TimerTitle));
        }
        greenValue = TimeUnit.MINUTES.toSeconds(greenValue);
        setContentView(R.layout.activity_speech_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void startChronometer(final View view) {
            ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            ((Chronometer) findViewById(R.id.chronometer)).start();
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
        if (elapsedTimeInSeconds == greenValue || elapsedTimeInSeconds == amberValue || elapsedTimeInSeconds == redValue || elapsedTimeInSeconds == redValue+30) {
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
        isExceeded = true;
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
        if (id == R.id.resetTimer) {
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
            return true;
            }
        else
        return super.onOptionsItemSelected(item);
        }

    public void timerReportCallingMethod (View view) {
        EditText nameFromActivity = (EditText) findViewById(R.id.enterNameForTimer);
        String name = nameFromActivity.getText().toString();
        long minutes=0, seconds=0;
        minutes = TimeUnit.SECONDS.toMinutes(elapsedTimeInSeconds);
        String type;
        seconds = elapsedTimeInSeconds%60;
        if(isAdvanced) {
            type = intent.getStringExtra(AdvancedSpeechesTimerSetting.timerTitle);
        }
        else {
            type = intent.getStringExtra(TimerSelection.TimerTitle);
        }
        if (isExceeded) {
            long exceededTime = (elapsedTimeInSeconds - endTimeInSeconds);
            SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = timer.edit();
            Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
            SimpleDateFormat currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy \t kk:mm:ss");
            timerSet.add(currentDateAndTime.format(new Date()) + "\n" + name + "-\t" + String.valueOf(minutes) + ":" + String.valueOf(seconds) +
                    "\nType:\t"+type+"\nTime Exceeded By:\t" + TimeUnit.SECONDS.toMinutes(exceededTime) + ":" + exceededTime % 60);
            editor.remove("timerSet");
            editor.apply();
            editor.putStringSet("timerSet", timerSet);
            editor.apply();
        }
        else {
            SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = timer.edit();
            Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
            SimpleDateFormat currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy \t kk:mm:ss");
            timerSet.add(currentDateAndTime.format(new Date()) + "\n" +name+"-\t"+String.valueOf(minutes)+":"+String.valueOf(seconds)+"\nType:\t"+type);
            editor.remove("timerSet");
            editor.apply();
            editor.putStringSet("timerSet", timerSet);
            editor.apply();
        }
        MainActivity.getInstance().recreate();
        TimerSelection.getInstance().finish();
        finish();
    }
}
