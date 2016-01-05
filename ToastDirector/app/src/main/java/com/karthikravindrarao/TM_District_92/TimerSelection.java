package com.karthikravindrarao.TM_District_92;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TimerSelection extends AppCompatActivity {

    public final static String greenTimer = "com.district92.toastmasters.greenTime";
    public final static String TimerTitle = "com.district92.toastmasters.timerTitle";
    static TimerSelection timerSelection;
    Intent intentFromSelectProjectActivity;
    Intent intentForAdvancedProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timerSelection = this;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer_selection);
        setTitle("Select Timer");
        intentFromSelectProjectActivity = new Intent(this, speechTimer.class);
        intentForAdvancedProjects = new Intent(this, AdvancedSpeechesTimerSetting.class);
        final String[] listOfProjects = {"Project 1", "Project 2 - 9", "Project 10", "Topics", "Evaluation", "Advanced Speeches", "View Report"};
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
                    case 6:
                        finish();
                        break;
                }

            }


        });
    }

    public static TimerSelection getInstance () {
        return timerSelection;
    }
    
    public void callTheTimer (int green, String title) {
        intentFromSelectProjectActivity.putExtra(greenTimer, green);
        intentFromSelectProjectActivity.putExtra(TimerTitle, title);
        startActivity(intentFromSelectProjectActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_selection, menu);
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
