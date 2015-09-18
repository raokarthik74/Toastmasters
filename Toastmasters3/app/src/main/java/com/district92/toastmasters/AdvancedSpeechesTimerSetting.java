package com.district92.toastmasters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvancedSpeechesTimerSetting extends AppCompatActivity {

    String[] listOfNumbers = {"0.5",
            "1",
            "1.5",
            "2",
            "2.5",
            "3",
            "3.5",
            "4",
            "4.5",
            "5",
            "5.5",
            "6",
            "6.5",
            "7",
            "7.5",
            "8",
            "8.5",
            "9",
            "9.5",
            "10",
            "10.5",
            "11",
            "11.5",
            "12",
            "12.5",
            "13",
            "13.5",
            "14",
            "14.5",
            "15",
            "15.5",
            "16",
            "16.5",
            "17",
            "17.5",
            "18",
            "18.5",
            "19",
            "19.5",
            "20",
            "20.5",
            "21",
            "21.5",
            "22",
            "22.5",
            "23",
            "23.5",
            "24",
            "24.5",
            "25",
            "25.5",
            "26",
            "26.5",
            "27",
            "27.5",
            "28",
            "28.5",
            "29",
            "29.5",
            "30",
            "30.5",
            "31",
            "31.5",
            "32",
            "32.5",
            "33",
            "33.5",
            "34",
            "34.5",
            "35",
            "35.5",
            "36",
            "36.5",
            "37",
            "37.5",
            "38",
            "38.5",
            "39",
            "39.5",
            "40",
            "40.5",
            "41",
            "41.5",
            "42",
            "42.5",
            "43",
            "43.5",
            "44",
            "44.5",
            "45",
            "45.5",
            "46",
            "46.5",
            "47",
            "47.5",
            "48",
            "48.5",
            "49",
            "49.5",
            "50",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int minimumForAmber = 0, minimumForRed = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_speeches_timer_setting);
        setTitle("Select Time");
        final NumberPicker greenNumberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        final NumberPicker amberNumberPicker = (NumberPicker) findViewById(R.id.numberPicker2);
        final NumberPicker redNumberPicker = (NumberPicker) findViewById(R.id.numberPicker3);
        numberPickerSetup(greenNumberPicker, 0);
        greenNumberPicker.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                numberPickerSetup(amberNumberPicker, newVal);
                amberNumberPicker.setOnValueChangedListener(new NumberPicker.
                        OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int
                            oldVal, int newVal) {
                        numberPickerSetup(redNumberPicker, newVal);
                    }
                });
            }
        });
    }

    public void numberPickerSetup (NumberPicker currentNumberPicker, int setValue) {
                currentNumberPicker.setMaxValue(listOfNumbers.length - 1);
                currentNumberPicker.setMinValue(0);
        currentNumberPicker.setWrapSelectorWheel(false);
        currentNumberPicker.setValue(setValue + 2);
        currentNumberPicker.setDisplayedValues(listOfNumbers);
    }

    public void doneButton (View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_speeches_timer_setting, menu);
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
