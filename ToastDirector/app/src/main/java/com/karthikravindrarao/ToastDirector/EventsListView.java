package com.karthikravindrarao.ToastDirector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventsListView extends AppCompatActivity {

    public final static String urlForEventsWeb = "com.karthikravindrarao.urlForEvetnsWeb";
    
    Intent intentToOpenEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list_view);
        setTitle("District Events");
        ListView listOfAwards = (ListView) findViewById(R.id.listViewOfEvents);
        String[] awardList = {"TLI","Judges Training Program", "Emergence", "Leadership Development Program"};
        ArrayAdapter<String> adaptorForAwards = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, awardList);
        listOfAwards.setAdapter(adaptorForAwards);
        intentToOpenEvents = new Intent(this, webView.class);
        listOfAwards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intentToOpenEvents.putExtra(urlForEventsWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/events/tli");
                        startActivity(intentToOpenEvents);
                        break;
                    case 1:
                        intentToOpenEvents.putExtra(urlForEventsWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/events/jtp");
                        startActivity(intentToOpenEvents);
                        break;
                    case 2:
                        intentToOpenEvents.putExtra(urlForEventsWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/events/emergence");
                        startActivity(intentToOpenEvents);
                        break;
                    case 3:
                        intentToOpenEvents.putExtra(urlForEventsWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/events/ldp");
                        startActivity(intentToOpenEvents);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events_list_view, menu);
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
