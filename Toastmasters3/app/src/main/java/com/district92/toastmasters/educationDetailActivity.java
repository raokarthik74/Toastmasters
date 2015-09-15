package com.district92.toastmasters;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import models.EducationModel;


public class educationDetailActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.positionOfSelectionOfObjectFromEducationFragment, 0);
        setTitle(intent.getStringExtra(MainActivity.titleOfEducationDetailPage));
                TextView description = (TextView) findViewById(R.id.educationDescription);
                description.setMovementMethod(new ScrollingMovementMethod());
        EducationModel dataModel = new EducationModel();
                description.setText(dataModel.allTheDataForEducationDetail(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_education_detail, menu);
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
