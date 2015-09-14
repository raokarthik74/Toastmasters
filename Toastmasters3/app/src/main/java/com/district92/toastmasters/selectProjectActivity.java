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


public class selectProjectActivity extends ActionBarActivity {

    public final static String greenTimer = "com.district92.toastmasters.greenTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Select Project");
        super.onCreate(savedInstanceState);
        final Intent intentFromSelectProjectActivity = new Intent(this, Timer.class);
        setContentView(R.layout.activity_select_project);
            ListView selectProjectListView;
            String[] listOfProjects = {"Ice-Breaker", "Project 2 - 9", "Project 10", "Table Topics", "Evaluation", "Advanced Projects"};

                ListView projectSelectListView = (ListView) findViewById(R.id.timerSelectionListView);
                ArrayAdapter<String> clubStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfProjects);
            projectSelectListView.setAdapter(clubStringArrayAdapter);
            projectSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionOfProject, long id) {
                    int greenTime;
                    switch (positionOfProject) {
                        case 0 : greenTime=4; break;
                        case 2: greenTime=8; break;
                        case 3: greenTime=1; break;
                        case 4: greenTime=2; break;
                        default: greenTime=5; break;
                    }
                    intentFromSelectProjectActivity.putExtra(greenTimer, greenTime);
                    startActivity(intentFromSelectProjectActivity);
                }
            });

            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
