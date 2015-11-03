package com.karthikravindrarao.ToastDirector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class timerReportActivity extends AppCompatActivity {

    static timerReportActivity timerreportactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Timer Report");
        timerreportactivity = this;
        setContentView(R.layout.activity_timer_report);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ListView listView = (ListView) findViewById(R.id.timerReportListView);
                SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
                Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
                ArrayList<String> listOfData = new ArrayList<String>(timerSet);
                Collections.sort(listOfData);
                TextView noTimer = (TextView) findViewById(R.id.noTimer);
                if (listOfData.isEmpty()) {
                    noTimer.setVisibility(View.VISIBLE);
                }
                else {
                    noTimer.setVisibility(View.INVISIBLE);
                }
                ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
                listView.setAdapter(dataToBeDisplayed);
    }

    public static timerReportActivity getInstance () {
        return timerreportactivity;
    }

    public void floatingActionButtonforTimer (View view) {
        Intent intentFromTimer = new Intent(this, TimerSelection.class);
        startActivity(intentFromTimer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deleteTimerReport) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Warning !");
            alertDialog.setMessage("Are you sure you want to delete all the Timer Reports ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
                            //Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
                            SharedPreferences.Editor editor = timer.edit();
                            //timerSet.clear();
                            //editor.putStringSet("timerSet", timerSet);
                            editor.remove("timerSet");
                            editor.apply();
                            recreate();
                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }
            );
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
