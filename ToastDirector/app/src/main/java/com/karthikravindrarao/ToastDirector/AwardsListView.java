package com.karthikravindrarao.ToastDirector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AwardsListView extends AppCompatActivity {

    public final static String urlForWeb = "com.karthikravindrarao.urlForWeb";
    Intent intentToOpenAwards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("District Awards");
        setContentView(R.layout.activity_awards_list_view);
        ListView listOfAwards = (ListView) findViewById(R.id.listViewOfAwards);
        String[] awardList = {"Best Club News Letter","Best Club Social Media Presence", "Best Club Website", "Linkers Award", "Super Club Award", "Triple Crown Award"
        , "DTM Award", "Fast Five", "Spirit of 92", "Golden Gavel", "Best Toastmaster"};
        ArrayAdapter<String> adaptorForAwards = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, awardList);
        listOfAwards.setAdapter(adaptorForAwards);
        intentToOpenAwards = new Intent(this, webView.class);
        listOfAwards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pr-awards/best-club-newsletter");
                        startActivity(intentToOpenAwards);
                        break;
                    case 1:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pr-awards/best-social-media-presence");
                        startActivity(intentToOpenAwards);
                        break;
                    case 2:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pr-awards/best-club-website");
                        startActivity(intentToOpenAwards);
                        break;
                    case 3:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/cgd-awards/linkers");
                        startActivity(intentToOpenAwards);
                        break;
                    case 4:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/cgd-awards/super-club");
                        startActivity(intentToOpenAwards);
                        break;
                    case 5:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pqd-awards/triple-crown-award");
                        startActivity(intentToOpenAwards);
                        break;
                    case 6:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pqd-awards/dtm-award");
                        startActivity(intentToOpenAwards);
                        break;
                    case 7:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pqd-awards/fast-five");
                        startActivity(intentToOpenAwards);
                        break;
                    case 8:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pqd-awards/spirit-of-92");
                        startActivity(intentToOpenAwards);
                        break;
                    case 9:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/pqd-awards/golden-gavel-golden-gavel-plus");
                        startActivity(intentToOpenAwards);
                        break;
                    case 10:
                        intentToOpenAwards.putExtra(urlForWeb, "http://www.tmdistrict92.org/tmdistrict92/index.php/awards/dd-awards/best-toastmaster-2014-2015");
                        startActivity(intentToOpenAwards);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_awards_list_view, menu);
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
