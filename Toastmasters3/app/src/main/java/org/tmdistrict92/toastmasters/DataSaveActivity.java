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

public class DataSaveActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        setTitle("Ah Counter Report");
        Intent intent = getIntent();
        ListView listView = (ListView) findViewById(R.id.dataSaveListView);
        SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
        Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
        ArrayList<String> listOfData = new ArrayList<String>(counterSet);
        ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
        listView.setAdapter(dataToBeDisplayed);
    }

    public void floatingActionButton (View view) {
        MainActivity.getInstance().recreate();
        finish();
    }
    @Override
    public void onBackPressed () {
        MainActivity.getInstance().recreate();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reportReset:
                SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = counter.edit();
                Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
                counterSet.clear();
                editor.putStringSet("counterSet", counterSet);
                editor.commit();
                finish();
                startActivity(getIntent());
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_save, menu);
        return true;
    }


}
