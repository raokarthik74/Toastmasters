package com.district92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import models.TimerReportModel;
import models.ahCounterReport;

public class TimerReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_report);
        setTitle("Timer Report");
        Intent intent = getIntent();
        ListView listView = (ListView) findViewById(R.id.timerReportListView);
        ArrayList<String> listOfData = new ArrayList<String>();
        listOfData = TimerReportModel.getDataFromTimerArray();
        ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
        listView.setAdapter(dataToBeDisplayed);
    }

    public void floatingActionButtonforTimer (View view) {
        Intent intentFromDataSave = new Intent(this, timerSelectionActivity.class);
        startActivity(intentFromDataSave);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.timerReset:
                TimerReportModel.resetArrayListOFTimer();
                finish();
                startActivity(getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_report, menu);
        return true;
    }


}
