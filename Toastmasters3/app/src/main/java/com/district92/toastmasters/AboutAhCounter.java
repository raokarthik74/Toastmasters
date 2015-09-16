package com.district92.toastmasters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutAhCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_ah_counter);
        TextView aboutAhCounterTextView = (TextView) findViewById(R.id.aboutAhCounterDetailId);
        setTitle("About Ah Counter");
        aboutAhCounterTextView.setMovementMethod(new ScrollingMovementMethod());
        aboutAhCounterTextView.setText("Introduction\n" +
                "\n" +
                "The Ah-Counter role is an excellent opportunity to practice your listening skills. It also helps speakers notice their unintentional utterance of crutch words or filler sounds/words during their speech.\n" +
                "\n" +
                "Responsibilities\n" +
                "\n" +
                "To count the number of \n" +
                "\n" +
                "1. crutch words \n" +
                "\n" +
                "2. filler words \n" +
                "\n" +
                "3. unwanted pauses and \n" +
                "\n" +
                "4. gestures \n" +
                "\n" +
                "Prior to the meeting\n" +
                "\n" +
                "Prepare a brief explanation of the duties of the Ah-counter for the benefit of the guests\n" +
                "\n" +
                "During the meeting \n" +
                "\n" +
                "Explaining your role\n" +
                "\n" +
                "When called upon by the toastmaster, an Ah counter needs to explain his/her role to the audience. Sample script: “My role as the Ah-Counter is to count the number of crutch words such as `Ah', `Er', `Em's filler words such as `You Know', `I Mean', `well', `but', `so' etc., unwanted pauses and gestures you use when you are speaking on the stage. These are fillers that would affect your effectiveness in delivering your speech. At the end of the meeting I will present my report. Back to you, Toastmaster of the Evening.”\n" +
                "\n" +
                "Counting\n" +
                "\n" +
                "Count and note the number of unnecessary sound and repetitive words that speakers utter during their speech.(Ex: “Er” , “Em” , “Ah” , “You know” , “I mean”). Jot down the total number of unnecessary sounds for \n" +
                "\n" +
                "1. Toastmaster of the Evening (TME) \n" +
                "\n" +
                "2. Timer (Role Introduction + Report) \n" +
                "\n" +
                "3. Language Evaluator/Grammarian (Role Introduction + Word of the Day + Report) \n" +
                "\n" +
                "4. Table Topics Master \n" +
                "\n" +
                "5. Table Topics Speakers \n" +
                "\n" +
                "6. Prepared Speech Speakers \n" +
                "\n" +
                "7. Speech Evaluators \n" +
                "\n" +
                "8. General Evaluators \n" +
                "\n" +
                "After the meeting: Reporting\n" +
                "\n" +
                "At the end of the meeting, read out the report by mentioning the total number of unnecessary sounds a speaker made and specifically pointing out one or two prominently used filler words. Also, include advice on how to minimise the use of filler sounds and words, such as to slow down and pause.\n" +
                "\n" +
                "How to succeed as Ah-Counter\n" +
                "\n" +
                "Every speaker WILL have one or the other filler. Make sure to make note of it. It will go a long way in helping the speaker become a better communicator. For you, the role taker, it will be a good opportunity to sharpen your listening skills. By making note of the fillers, you will in turn become aware of the fillers that you might use. It will help you in your private life and career as an impeccable speaker (whose speech precludes fillers.)\n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_ah_counter, menu);
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
