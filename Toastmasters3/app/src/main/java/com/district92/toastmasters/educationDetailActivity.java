package com.district92.toastmasters;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class educationDetailActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.positionOfSelectionOfObjectFromEducationFragment, 0);

            // "1 - The Ice Breaker", "2 - Organize Your Speech", "3 - Get To The Point", "4 - How To Say It", "5 - Your Body Speaks",
//            "6 - Vocal Variety", "7 - Research Your Topic", "8 - Get Comfortable With visual Aids", "9 - Persuade With Power", "10 - Inspire Your Audience",
//                    "AC - The Entertaining Speaker",
//                    "AC - Speaking to Inform",
//                    "AC - Public Relations",
//                    "AC - Facilitating Discussion",
//                    "AC - Specialty Speeches",
//                    "AC - Speeches by Management",
//                    "AC - The Professional Speaker",
//                    "AC - Technical Presentations",
//                    "AC - Persuasive Speaking",
//                    "AC - Communicating on Video",
//                    "AC - Storytelling" ,
//                    "AC - Interpretive Reading" ,
//                    "AC - Interpersonal Communication" ,
//                    "AC - Special Occasion Speeches",
//                    "AC - Humorously Speaking"
                setTitle("The Ice Breaker");
                TextView description = (TextView) findViewById(R.id.educationDescription);
                description.setText("For your first speech project, you will introduce yourself to your fellow club members and give them some information about your background, " +
                        "interests, and ambitions. Practice giving your speech to friends or family members, and strive to make eye contact with some of your audience. You may use " +
                        "notes during your speech if you wish. Read the entire project before preparing your talk. Time: 4 - 6 minutes Objectives: • To begin speaking before an audience. " +
                        "• To discover speaking skills you already have and skills that need some attention • To introduce yourself to your fellow club members." +
                        "For your first speech project, you will introduce yourself to your fellow club members and give them some information about your background, " +
                        "interests, and ambitions. Practice giving your speech to friends or family members, and strive to make eye contact with some of your audience. You may use " +
                                "notes during your speech if you wish. Read the entire project before preparing your talk. Time: 4 - 6 minutes Objectives: • To begin speaking before an audience. " +
                                "• To discover speaking skills you already have and skills that need some attention • To introduce yourself to your fellow club members." +
                        "For your first speech project, you will introduce yourself to your fellow club members and give them some information about your background, " +
                                "interests, and ambitions. Practice giving your speech to friends or family members, and strive to make eye contact with some of your audience. You may use " +
                                "notes during your speech if you wish. Read the entire project before preparing your talk. Time: 4 - 6 minutes Objectives: • To begin speaking before an audience. " +
                                "• To discover speaking skills you already have and skills that need some attention • To introduce yourself to your fellow club members.");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
