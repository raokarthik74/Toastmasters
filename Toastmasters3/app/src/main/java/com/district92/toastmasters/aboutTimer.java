package com.district92.toastmasters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class aboutTimer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_timer);
        setTitle("About Timer");
        TextView aboutTimer = (TextView) findViewById(R.id.aboutTimer);
        aboutTimer.setMovementMethod(new ScrollingMovementMethod());
        aboutTimer.setText("Introduction \n" +
                "\n" +
                "Expressing a thought within a specific time is an important skill all toastmasters should practice. As a Timer, you are responsible for monitoring time for each meeting segment and for each speaker. You will also operate the timing signal to indicate to each speaker how long he/she has been talking. Serving as a Timer is an excellent opportunity to practice both – giving instructions and time management – something we do every day.\n" +
                "\n" +
                "Responsibility of the Timer \n" +
                "\n" +
                "As the Timer of the meeting, you help the meeting participants to be aware of the timing of their functions. You help keep the meeting on schedule.\n" +
                "\n" +
                "A Timer has to time, record, signal, and report on the time used by\n" +
                "\n" +
                "• Speakers, \n" +
                "\n" +
                "• Table topic speakers, \n" +
                "\n" +
                "• Individual speech evaluators, and \n" +
                "\n" +
                "• The General evaluator. \n" +
                "\n" +
                "Prior to the meeting\n" +
                "\n" +
                "1. Contact the Toastmaster (MOC) and General Evaluator(GE) and confirm which members are the scheduled program participants \n" +
                "\n" +
                "2. Contact the individual speakers for any special timing requirements they may have \n" +
                "\n" +
                "3. Get timing cards and Timer's report form from the Sergeant-at-Arms. \n" +
                "\n" +
                "4. Make sure you have a functioning stopwatch. \n" +
                "\n" +
                "5. Sit where everybody can see the signaling device (Timer card) easily. \n" +
                "\n" +
                "During the meeting \n" +
                "\n" +
                "When you are asked to explain the timing procedures, stand up, address the Toastmaster/General Evaluator and announce the applicable timing rules. Here is a typical Timer's speech:\n" +
                "\n" +
                "“As the Timer, I am responsible for keeping track of time. For prepared speeches with a time of 5 to 7 minutes, the green card comes on at 5 minutes, the amber card comes on at 6 minutes, and the red card comes on at 7 minutes. The speaker has 30 seconds to wrap it up. Table Topics responses are for 1 to 2 minutes. The green card comes on at 1 minute, the amber card comes on at 1½ minutes, and the red card comes on at 2 minutes. Speech evaluations are for 2 to 3 minutes. The green card comes on at 2 minutes, the amber card comes on at 2½ minutes, and the red card comes on at 3 minutes. I will give my report when called upon to do so. “\n" +
                "\n" +
                "You can add one or two lines at the beginning, and modify the speech to your convenience, but ensure that you do not speak over time. \n" +
                "\n" +
                "• Keep track of the times of Prepared Speakers, Table Topics Speakers and Evaluators. \n" +
                "\n" +
                "• Record the start time and end time of each segment of the meeting. \n" +
                "\n" +
                "• Begin timing with the speaker's or evaluator's first definite verbal or non-verbal communication with the audience. This usually will be the first word uttered, but would include any other communication such as sound effects or a staged act by another person. \n" +
                "\n" +
                "• Signal each program participant according to the timing rules. \n" +
                "\n" +
                "• Advanced speeches for experienced Toastmasters vary in time. Speakers who need a different amount of time than the above time limits should let the Timer know in advance of the meeting. If the Timer is not sure of requirements, ask speakers before the meeting about their time limits and exactly when they would like the color signal lights. \n" +
                "\n" +
                "• If a speaker goes over the grace period, wave the red card/clap until the speaker stops. \n" +
                "\n" +
                "• Record the time used by each table topics speaker, speaker, and evaluator. \n" +
                "\n" +
                "• When called on by the General Evaluator, give a report from your record. Your duty is to provide the timing report, not to give a speech or an evaluation. Your report should take no more than 1 minute. \n" +
                "\n" +
                "• If a speaker has exceeded time, DO NOT mention the name of the speaker who has exceeded the time. Just say the number of speakers who have exceeded time. For example, “One speaker has exceeded his/her time limit.” \n" +
                "\n" +
                "After the meeting \n" +
                "\n" +
                "• Return the timing device to the Sergeant-at-Arms. \n" +
                "\n" +
                "• Give the completed Timer's report to the Secretary. \n" +
                "\n" +
                "• Please put colored folders, and these directions in the bag for use next week \n" +
                "\n" +
                "How to succeed as Timer\n" +
                "\n" +
                "Arrive early at the meeting to take care of last-minute details and settle down at a good spot.\n" +
                "\n" +
                "If a speaker has gone overtime, first try to catch his/her attention and flick the red card again as a reminder. But do it gently and thoughtfully in a way that doesn't look annoying and distracting (this is important to follow especially for ice-breakers.) Use your judgment when applauding someone who speaks beyond the red card. This should be restricted to members who have ignored your signals, to conclude their presentation. For example, Non-Toastmasters guests should never be embarrassed. Remind all the speakers, especially ice-breakers and table topics participants that they should not finish under time as well.\n" +
                "\n" +
                "Take on this role and the new habits formed will serve you well in your private life and your career. People appreciate a speaker, friend or employee who is mindful of time frames and deadlines.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_timer, menu);
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
