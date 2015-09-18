package com.district92.toastmasters;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import models.EducationModel;
import models.ahCounterReport;


public class AhCounterActivity extends ActionBarActivity {

    public final static String dataToDisplay = "com.district92.toastmasters.datatodisplay";
   // ArrayList<String> dataForDisplay = new ArrayList<String>();

    int crutch=0, filler=0, pause=0;

    Button crutchButton;
    Button fillerButton;
    Button pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ah_counter);
        setTitle("Ah Counter");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        RelativeLayout parent = (RelativeLayout) findViewById(R.id.parentRelativeLayoutofAhCounter);
        Snackbar.make(parent, "Tap on the numbers to count", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ah_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void crutchIncrement (View view) {
        ++crutch;
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        crutchButton.setText(" "+ crutch);
    }
    public void fillerIncrement (View view) {
        ++filler;
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        fillerButton.setText(" "+ filler);
    }
    public void pauseIncrement (View view) {
        ++pause;
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        pauseButton.setText(" "+ pause);
    }
    public void ahCounterResetButton (View view) {
        crutch=filler=pause=0;
        ((EditText) findViewById(R.id.enterName)).setText("");
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        crutchButton.setText("0");
        fillerButton.setText("0");
        pauseButton.setText("0");
    }

    public void ahCounterSaveButton (View view) {
        EditText nameFromActivity = (EditText) findViewById(R.id.enterName);
        String name = nameFromActivity.getText().toString();
        ahCounterReport.setDataToArray(name + " \n Crutch Words:" + crutch + " \n Filler Words:" + filler + "\n Unwanted Pauses:"+ pause);
       // dataForDisplay.add(name);
        Intent forDataSaveActivity = new Intent(this, DataSaveActivity.class);
       // forDataSaveActivity.putStringArrayListExtra(dataToDisplay, dataForDisplay);
        startActivity(forDataSaveActivity);
    }
}
