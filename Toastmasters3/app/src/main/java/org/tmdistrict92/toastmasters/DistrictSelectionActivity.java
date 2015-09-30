package org.tmdistrict92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.GetCallback;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import models.spinnerClass;

public class DistrictSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select Your District");
        setContentView(R.layout.activity_district_selection);
        addItemsToSpinner();
    }

    public void addItemsToSpinner () {
        Spinner divisionSpinner = (Spinner) findViewById(R.id.spinnerForDivisionSelection);
        ArrayAdapter<String> divisionArrayAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerClass.divisionData());
        divisionArrayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divisionSpinner.setAdapter(divisionArrayAdaptor);
        Spinner areaSpinner = (Spinner) findViewById(R.id.spinnerForAreaSelection);
        ArrayAdapter<String> areaSpinnerAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerClass.areaData());
        areaSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaSpinnerAdaptor);
        Spinner clubSpinner = (Spinner) findViewById(R.id.spinnerForClubSelection);
        ArrayAdapter<String> clubSpinnerAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerClass.clubData());
        clubSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clubSpinner.setAdapter(clubSpinnerAdaptor);
    }

    public void districtDoneButton (View view) {
       // String districtValue = ((Spinner) findViewById(R.id.spinnerForDistrictSelection)).getSelectedItem().toString();
        String divisionValue = ((Spinner) findViewById(R.id.spinnerForDivisionSelection)).getSelectedItem().toString();
        String areaValue = ((Spinner) findViewById(R.id.spinnerForAreaSelection)).getSelectedItem().toString();
        String clubValue = ((Spinner) findViewById(R.id.spinnerForClubSelection)).getSelectedItem().toString();
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.remove("channels");
        ParsePush.subscribeInBackground("DISTRICT-92");
            ParsePush.subscribeInBackground(divisionValue);
            ParsePush.subscribeInBackground(areaValue);
            ParsePush.subscribeInBackground(clubValue);
        Intent intentBackToMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentBackToMainActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_district_selection, menu);
        return true;
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
