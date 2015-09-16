package com.district92.toastmasters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class aboutToastmastersActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About Toastmasters");
        setContentView(R.layout.activity_about_toastmasters);
        TextView aboutText = (TextView) findViewById(R.id.aboutToastmasters);
        aboutText.setMovementMethod(new ScrollingMovementMethod());
        aboutText.setText("Toastmasters International is a world leader in communication and leadership development. Our membership is more than 332,000 members. Members improve their speaking and leadership skills by attending one of the 15,400 clubs in 135 countries that make up our global network of meeting locations.\n" +
                "\n" +
                "The world needs leaders. Leaders head families, coach teams, run businesses and mentor others. These leaders must not only accomplish, they must communicate. By regularly giving speeches, gaining feedback, leading teams and guiding others to achieve their goals in a supportive atmosphere, leaders emerge from the Toastmasters program. Every Toastmasters journey begins with a single speech. During their journey, they learn to tell their stories. They listen and answer. They plan and lead. They give feedback—and accept it. Through our community of learners, they find their path to leadership.\n" +
                "\n" +
                "Toastmasters International Mission\n" +
                "\nWe empower individuals to become more effective communicators and leaders.\n" +
                "\n" +
                "District Mission\n" +
                "\nWe build new clubs and support all clubs in achieving excellence.\n" +
                "\n" +
                "Club Mission\n" +
                "\nWe provide a supportive and positive learning experience in which members are empowered to develop communication and leadership skills, resulting in greater self-confidence and personal growth.\n" +
                "\n" +
                "Toastmasters International Values\n" +
                "\nIntegrity\n" +
                "Respect\n" +
                "Service\n" +
                "Excellence\n" +
                "\nToastmasters International Envisioned Future\n" +
                "\nTo be the first-choice provider of dynamic, high-value, experiential communication and leadership skills development.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_toastmasters, menu);
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
