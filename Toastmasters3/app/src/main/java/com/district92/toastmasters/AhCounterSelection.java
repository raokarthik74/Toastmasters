package com.district92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AhCounterSelection extends AppCompatActivity {

    Intent intentToStartAhCounter = new Intent(this, AhCounterActivity.class);
    Intent intentToKnowAboutAhCounter = new Intent(this, AboutAhCounter.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ah_counter_selection);
        setTitle("Ah Counter");
        ListView ahCounterListView = (ListView) findViewById(R.id.ahCounterSelectionIdListView);
        String[] listOfAhCounterOptions = {"Ah Counter", "About Ah Counter"};
        ArrayAdapter<String> arrayAdaptorForAhCounterSelection = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfAhCounterOptions);
        ahCounterListView.setAdapter(arrayAdaptorForAhCounterSelection);
        ahCounterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(intentToStartAhCounter);
                        break;
                    default:
                        startActivity(intentToKnowAboutAhCounter);
                        break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ah_counter_selection, menu);
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
