package org.tmdistrict92.toastmasters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimerReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_report);
        setTitle("Timer Report");
        Intent intent = getIntent();
        ListView listView = (ListView) findViewById(R.id.timerReportListView);
        SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
        Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
        ArrayList<String> listOfData = new ArrayList<String>(timerSet);
        ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
        listView.setAdapter(dataToBeDisplayed);
    }

    public void floatingActionButtonforTimer (View view) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.timerReset:
                SharedPreferences timer = getSharedPreferences("timer", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = timer.edit();
                Set<String> timerSet = timer.getStringSet("timerSet", new HashSet<String>());
                timerSet.clear();
                editor.putStringSet("timerSet", timerSet);
                editor.commit();
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
