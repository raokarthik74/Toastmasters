package com.district92.toastmasters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AhCounterActivity extends ActionBarActivity {

    int crutch=0, filler=0, pause=0;

    Button crutchButton;
    Button fillerButton;
    Button pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ah_counter);
        setTitle("Ah Counter");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ah_counter, menu);
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
    public void crutchIncrement (View view) {
        ++crutch;
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        crutchButton.setText(" "+ crutch);
    }
    public void fillerIncrement (View view) {
        ++filler;
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        fillerButton.setText(" "+ filler);
    }
    public void pauseIncrement (View view) {
        ++pause;
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        pauseButton.setText(" "+ pause);
    }
    public void ahCounterResetButton (View view) {
        crutch=filler=pause=0;
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        crutchButton.setText("0");
        fillerButton.setText("0");
        pauseButton.setText("0");
    }
}
