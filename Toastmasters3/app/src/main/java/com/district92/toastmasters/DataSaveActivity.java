package com.district92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import models.ahCounterReport;

public class DataSaveActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        setTitle("Ah Counter Report");
        Intent intent = getIntent();
        ListView listView = (ListView) findViewById(R.id.dataSaveListView);
        ArrayList<String> listOfData = new ArrayList<String>();
        listOfData = ahCounterReport.getDataFromArray();
        ArrayAdapter<String> dataToBeDisplayed = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfData);
        listView.setAdapter(dataToBeDisplayed);
    }

    public void floatingActionButton (View view) {
        Intent intentFromDataSave = new Intent(this, AhCounterActivity.class);
        startActivity(intentFromDataSave);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reportReset:
                ahCounterReport.resetArrayList();
                finish();
                startActivity(getIntent());
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
