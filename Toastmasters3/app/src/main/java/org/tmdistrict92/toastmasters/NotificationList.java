package org.tmdistrict92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.tmdistrict92.toastmasters.entities.PushDataInfo;

import models.AllNotifications;

public class NotificationList extends AppCompatActivity {

    public final static String urlForNotification = "com.district92.toastmasters.urlnotification";

    Intent intentForWebViewFromNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Notification History");
        intentForWebViewFromNotifications = new Intent(this, WebViewForNotification.class);
        setContentView(R.layout.activity_notification_list);
        ListView listView = (ListView) findViewById(R.id.listViewOfNotifications);
        ArrayAdapter<String> adaptorForNotifications = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNotifications.getDataFromNotificaitons());
        listView.setAdapter(adaptorForNotifications);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentForWebViewFromNotifications.putExtra(urlForNotification, AllNotifications.getUrlsForNotifications(position));
                startActivity(intentForWebViewFromNotifications);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.resetNotifications) {
            AllNotifications.resetNotoficationList();
            AllNotifications.resetUrlsForNotifications();
            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
