package com.district92.toastmasters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class timerSelectionActivity extends ActionBarActivity {

    Intent intentFromSelectProjectActivity;
    Intent intentForAdvancedProjects;

    public final static String greenTimer = "com.district92.toastmasters.greenTime";
    public final static String TimerTitle = "com.district92.toastmasters.timerTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Select Project");
        super.onCreate(savedInstanceState);
        intentFromSelectProjectActivity = new Intent(this, speechTimer.class);
        intentForAdvancedProjects = new Intent(this, AdvancedSpeechesTimerSetting.class);
        setContentView(R.layout.activity_timer_selection);
        ListView selectProjectListView;
        final String[] listOfProjects = {"Ice-Breaker", "Project 2 - 9", "Project 10", "Table Topics", "Evaluation", "Advanced Speeches"};

        ListView projectSelectListView = (ListView) findViewById(R.id.timerSelectionListView);
        ArrayAdapter<String> clubStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfProjects);
        projectSelectListView.setAdapter(clubStringArrayAdapter);
        projectSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionOfProject, long id) {
                int greenTime = 4;
                switch (positionOfProject) {
                    case 0:
                        greenTime = 4;
                        callTheTimer(greenTime, listOfProjects[0]);
                        break;
                    case 1:
                        greenTime = 5;
                        callTheTimer(greenTime, listOfProjects[1]);
                        break;
                    case 2:
                        greenTime = 8;
                        callTheTimer(greenTime, listOfProjects[2]);
                        break;
                    case 3:
                        greenTime = 1;
                        callTheTimer(greenTime, listOfProjects[3]);
                        break;
                    case 4:
                        greenTime = 2;
                        callTheTimer(greenTime, listOfProjects[4]);
                        break;
                    case 5:
                        startActivity(intentForAdvancedProjects);
                        break;

                }

                    }


        });

    }

    public void callTheTimer (int green, String title) {
        intentFromSelectProjectActivity.putExtra(greenTimer, green);
        intentFromSelectProjectActivity.putExtra(TimerTitle, title);
        startActivity(intentFromSelectProjectActivity);
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
}
