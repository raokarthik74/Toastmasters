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

public class ahCounterReportActivity extends AppCompatActivity {

    static ahCounterReportActivity ahcounterreportactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        ahcounterreportactivity = this;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Ah Counter Report");
        ListView listView = (ListView) findViewById(R.id.dataSaveListView);
        SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
        Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
        ArrayList<String> listOfData = new ArrayList<String>(counterSet);
        Collections.sort(listOfData);
        TextView noAhcounter = (TextView) findViewById(R.id.noAhCounter);
        if (listOfData.isEmpty()) {
            noAhcounter.setVisibility(View.VISIBLE);
        }
        else {
            noAhcounter.setVisibility(View.INVISIBLE);
        }
        ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
        listView.setAdapter(dataToBeDisplayed);
    }

    public static ahCounterReportActivity getInstance () {
        return ahcounterreportactivity;
    }

    public void floatingActionButton (View view) {
        Intent intentFromAhReport = new Intent(this, AhCounter.class);
        startActivity(intentFromAhReport);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ah_counter_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deleteAhCounterReport) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Warning !");
            alertDialog.setMessage("Are you sure you want to delete all the Ah Counter Reports ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
                            // Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
                            SharedPreferences.Editor counterEditor = counter.edit();
                            // counterSet.clear();
                            // counterEditor.putStringSet("counterSet", counterSet);
                            counterEditor.remove("counterSet");
                            counterEditor.apply();
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
