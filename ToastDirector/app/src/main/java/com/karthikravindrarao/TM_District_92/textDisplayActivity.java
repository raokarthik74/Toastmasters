package com.karthikravindrarao.TM_District_92;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;

public class textDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);
        Intent intentOfText = getIntent();
        ((TextView) findViewById(R.id.aboutConferenceText)).setMovementMethod(new ScrollingMovementMethod());
        ((TextView) findViewById(R.id.aboutConferenceText)).setText(intentOfText.getStringExtra(MainActivity.textForDisplay));
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
