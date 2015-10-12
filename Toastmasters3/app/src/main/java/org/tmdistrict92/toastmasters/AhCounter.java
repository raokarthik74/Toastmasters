package org.tmdistrict92.toastmasters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AhCounter extends AppCompatActivity {


    Button crutchButton;
    Button fillerButton;
    Button pauseButton;
    int crutch = 0, filler = 0, pause = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ah Counter");
        setContentView(R.layout.activity_ah_counter);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void crutchIncrement(View view) {
        ++crutch;
        crutchButton = (Button) findViewById(R.id.crutchWordId);
        crutchButton.setText(" " + crutch);
    }

    public void fillerIncrement(View view) {
        ++filler;
        fillerButton = (Button) findViewById(R.id.fillerIncrementId);
        fillerButton.setText(" " + filler);
    }

    public void pauseIncrement(View view) {
        ++pause;
        pauseButton = (Button) findViewById(R.id.pauseIncrementId);
        pauseButton.setText(" " + pause);
    }

    public void ahCounterSaveButton(View view) {
        EditText nameFromActivity = (EditText) findViewById(R.id.enterName);
        String name = nameFromActivity.getText().toString();
        SharedPreferences counter = getSharedPreferences("counter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = counter.edit();
        Set<String> counterSet = counter.getStringSet("counterSet", new HashSet<String>());
        SimpleDateFormat currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy \t hh:mm:ss aa");
        counterSet.add(currentDateAndTime.format(new Date()) +"\n"+ name + " \n Crutch Words:" + crutch + " \n Filler Words:" + filler + "\n Unwanted Pauses:" + pause);
        editor.remove("counterSet");
        editor.apply();
        editor.putStringSet("counterSet", counterSet);
        editor.apply();
        MainActivity.getInstance().recreate();
        finish();
    }

    public void goToReport (View view) {
        MainActivity.getInstance().recreate();
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
        if (id == R.id.resetAhCounter) {
            crutch = filler = pause = 0;
            ((EditText) findViewById(R.id.enterName)).setText("");
            crutchButton = (Button) findViewById(R.id.crutchWordId);
            fillerButton = (Button) findViewById(R.id.fillerIncrementId);
            pauseButton = (Button) findViewById(R.id.pauseIncrementId);
            crutchButton.setText("0");
            fillerButton.setText("0");
            pauseButton.setText("0");
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
